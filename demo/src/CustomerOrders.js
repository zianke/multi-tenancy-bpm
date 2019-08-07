import React from 'react';
import { Button, Table } from "antd";
import request from "request";
import { withCookies } from 'react-cookie';
import { API_ENDPOINT } from "./common";
import { Link } from "react-router-dom";

class CustomerOrders extends React.Component {

  constructor(props) {
    super(props);
    this.state = {};
  }

  componentDidMount() {
    const token = this.props.cookies.get('token');
    const tenantIds = ['tubatu', 'skshu'];

    tenantIds.forEach(tenantId => {
      request.get({
        url: `${API_ENDPOINT}/tasks`,
        headers: {
          'Authorization': `Bearer ${token}`
        },
        qs: {
          'tenantId': tenantId
        },
        json: true
      }, (error, response, body) => {
        if (response && response.statusCode === 200) {
          const dataSource = body.map(item => ({
            id: item.taskId,
            ...JSON.parse(item.orderJson)
          }));
          this.setState({[tenantId]: dataSource});
        }
      });
    })
  }

  render() {
    const dataSources = Object();
    console.log(this.state);
    for (let tenantId in this.state) {
      console.log(tenantId);
      dataSources[tenantId] = this.state[tenantId].map(item => ({
        ...item,
        key: item.id,
        service: item.service.join(),
        details: <Button><a href={`/staff/processing/${item.id}`}
                               style={{display: 'inline'}}><span>Details</span></a></Button>
      })) || [];
    }

    console.log(dataSources);

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
        dataIndex: 'details',
        key: 'details',
      }
    ];

    return (
      <div>
        {Object.keys(dataSources).map(tenantId => (
          <div key={tenantId}>
            <h2>{tenantId} Orders</h2>
            <Table dataSource={dataSources[tenantId]} columns={columns}/>
          </div>
        ))}
      </div>

    )
  }
}

export default withCookies(CustomerOrders);
