import React from 'react';
import { Table, Button, message } from "antd";
import request from "request";
import { withCookies } from 'react-cookie';
import { API_ENDPOINT } from "./common";

class StaffPendingOrders extends React.Component {

  constructor(props) {
    super(props);
    this.state = {dataSource: []};
    this.handleAccept = this.handleAccept.bind(this);
  }

  componentDidMount() {
    const token = this.props.cookies.get('token');
    request.get({
      url: `${API_ENDPOINT}/pending`,
      headers: {
        'Authorization': `Bearer ${token}`
      },
      json: true
    }, (error, response, body) => {
      if (response && response.statusCode === 200) {
        const dataSource = body.map(item => ({
          id: item.taskId,
          ...JSON.parse(item.orderJson)
        }));
        this.setState({dataSource});
      }
    });
  }

  handleAccept(id) {
    const token = this.props.cookies.get('token');
    request.post({
      url: `${API_ENDPOINT}/claim`,
      headers: {
        'Authorization': `Bearer ${token}`
      },
      body: {
        'taskId': id,
      },
      json: true
    }, (error, response, body) => {
      if (response && response.statusCode === 200) {
        message.info(`Order ${id} accepted!`);
        this.setState({dataSource: this.state.dataSource.filter(item => item.id !== id)});
      }
    });
  };

  render() {
    const dataSource = this.state.dataSource.map(item => ({
      ...item,
      key: item.id,
      service: item.service.join(),
      accept: <Button onClick={() => {
        this.handleAccept(item.id);
      }}>Accept</Button>
    }));
    const columns = [
      {
        title: 'Name',
        dataIndex: 'name',
        key: 'name',
      },
      {
        title: 'E-mail',
        dataIndex: 'email',
        key: 'email',
      },
      {
        title: 'Phone Number',
        dataIndex: 'phone',
        key: 'phone',
      },
      {
        title: 'Home Address',
        dataIndex: 'address',
        key: 'address',
      },
      {
        title: 'Requested Service',
        dataIndex: 'service',
        key: 'service',
      },
      {
        title: 'Comment',
        dataIndex: 'comment',
        key: 'comment',
      },
      {
        title: '',
        dataIndex: 'accept',
        key: 'accept',
      }
    ];

    return (
      <div>
        <h2>Pending Orders</h2>
        <Table dataSource={dataSource} columns={columns}/>
      </div>
    )
  }
}

export default withCookies(StaffPendingOrders);
