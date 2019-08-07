import React from 'react';
import { Button, Table } from "antd";
import { Link } from "react-router-dom";
import request from "request";
import { withCookies } from 'react-cookie';
import { API_ENDPOINT } from "./common";

class StaffProcessingOrders extends React.Component {

  constructor(props) {
    super(props);
    this.state = {dataSource: []};
  }

  componentDidMount() {
    const token = this.props.cookies.get('token');
    request.get({
      url: `${API_ENDPOINT}/processing`,
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

  render() {
    const dataSource = this.state.dataSource.map(item => ({
      ...item,
      key: item.id,
      service: item.service.join(),
      process: <Button><Link to={`/staff/processing/${item.id}`}
                             style={{display: 'inline'}}><span>Process</span></Link></Button>
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
        dataIndex: 'process',
        key: 'process',
      }
    ];

    return (
      <div>
        <h2>Processing Orders</h2>
        <Table dataSource={dataSource} columns={columns}/>
      </div>
    )
  }
}

export default withCookies(StaffProcessingOrders);
