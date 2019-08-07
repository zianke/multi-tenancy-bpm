import React from 'react';
import { Form, Table, Button, message, Input, Select } from "antd";

const {Option} = Select;
const FormItem = Form.Item;

class AdminCreateTenant extends React.Component {

  constructor(props) {
    super(props);
    this.state = {dataSource: []};
  }

  componentDidMount() {
    // TODO: Call API to get all tenants
    const dataSource = [
      {
        id: "1001",
        name: "土巴兔公司",
        email: "service@tubatu.com",
        prefix: "86",
        phone: "12345678",
        address: "Tubatu Rd",
      }];

    this.setState({dataSource});
  }

  handleSubmit = e => {
    e.preventDefault();
    this.props.form.validateFieldsAndScroll((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
        // TODO: Call API to create a tenant
        values.id = this.state.dataSource && this.state.dataSource.length ?
          parseInt(this.state.dataSource[this.state.dataSource.length - 1].id) + 1 : "1001";
        this.setState({dataSource: [...this.state.dataSource, values]});
        message.info('Tenant created successfully!');
      }
    });
  };

  handleDelete = id => {
    // TODO: Call API to delete a tenant
    message.info(`Tenant ${id} deleted!`);
    this.setState({dataSource: this.state.dataSource.filter(item => item.id !== id)});
  };

  render() {
    const {getFieldDecorator} = this.props.form;

    const prefixSelector = getFieldDecorator('prefix', {
      initialValue: '86',
    })(
      <Select style={{width: 70}} initialValue="86">
        <Option value="1">+1</Option>
        <Option value="86">+86</Option>
      </Select>,
    );

    const columns = [
      {
        title: 'Company Name',
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
        title: 'Company Address',
        dataIndex: 'address',
        key: 'address',
      },
      {
        title: '',
        dataIndex: 'delete',
        key: 'delete',
      }
    ];

    const dataSource = this.state.dataSource.map(item => ({
      ...item,
      delete: <Button onClick={() => {
        this.handleDelete(item.id);
      }}>Delete</Button>
    }));

    return (
      <div>
        <h2>Create Tenant</h2>
        <Form onSubmit={this.handleSubmit} className="login-form">
          <Form.Item label="Company Name">
            {getFieldDecorator('name', {
              rules: [{required: true, message: 'Please input the company name!', whitespace: true}],
            })(<Input/>)}
          </Form.Item>
          <Form.Item label="E-mail">
            {getFieldDecorator('email', {
              rules: [
                {
                  type: 'email',
                  message: 'The input is not valid E-mail!',
                },
                {
                  required: true,
                  message: 'Please input the E-mail!',
                },
              ],
            })(<Input/>)}
          </Form.Item>
          <Form.Item label="Phone Number">
            {getFieldDecorator('phone', {
              rules: [{required: true, message: 'Please input the phone number!'}],
            })(<Input addonBefore={prefixSelector} style={{width: '100%'}}/>)}
          </Form.Item>
          <Form.Item label="Home Address">
            {getFieldDecorator('address', {
              rules: [{required: true, message: 'Please input the company address!', whitespace: true}],
            })(<Input/>)}
          </Form.Item>
          <FormItem>
            <Button type="primary" htmlType="submit" className="login-form-button">
              Create Tenant
            </Button>
          </FormItem>
        </Form>
        <h2>Tenants</h2>
        <Table dataSource={dataSource} columns={columns}/>
      </div>
    )
  }
}

const WrappedAdminCreateTenant = Form.create()(AdminCreateTenant);

export default WrappedAdminCreateTenant;
