import React from 'react';
import { Form, Icon, Table, Button, message, Input, Radio, Cascader } from "antd";

const FormItem = Form.Item;

const companies = [
  {
    value: 'tubatu',
    label: '土巴兔公司',
  },
  {
    value: 'skshu',
    label: '三棵树公司',
  },
];

const groups = [
  {
    value: 'customer-service',
    label: 'Customer Service',
  },
  {
    value: 'engineer',
    label: 'Engineer',
  },
];

class AdminCreateUser extends React.Component {

  constructor(props) {
    super(props);
    this.state = {identity: "customer", dataSource: []};
  }

  componentDidMount() {
    // TODO: Call API to get all users
    const dataSource = [
      {
        id: "10001",
        username: "ywj",
        password: "123456",
        identity: "customer",
      }];

    this.setState({dataSource});
  }

  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
        // TODO: Call API to create a user
        values.identity = this.state.identity;
        values.id = this.state.dataSource && this.state.dataSource.length ?
          parseInt(this.state.dataSource[this.state.dataSource.length - 1].id) + 1 : "10001";
        this.setState({dataSource: [...this.state.dataSource, values]});
        message.info('User created successfully!');
      }
    });
  };

  handleIdentityChange = (e) => {
    this.setState({identity: e.target.value});
  };

  handleDelete = id => {
    // TODO: Call API to delete a user
    message.info(`User ${id} deleted!`);
    this.setState({dataSource: this.state.dataSource.filter(item => item.id !== id)});
  };

  render() {
    const {getFieldDecorator} = this.props.form;

    const customerColumns = [
      {
        title: 'User ID',
        dataIndex: 'id',
        key: 'id',
      },
      {
        title: 'Username',
        dataIndex: 'username',
        key: 'username',
      },
      {
        title: '',
        dataIndex: 'delete',
        key: 'delete',
      }
    ];

    const staffColumns = [
      {
        title: 'User ID',
        dataIndex: 'id',
        key: 'id',
      },
      {
        title: 'Username',
        dataIndex: 'username',
        key: 'username',
      },
      {
        title: 'Company',
        dataIndex: 'company',
        key: 'company',
      },
      {
        title: 'Group',
        dataIndex: 'group',
        key: 'group',
      },
      {
        title: '',
        dataIndex: 'delete',
        key: 'delete',
      }
    ];

    const customerDataSource = this.state.dataSource
      .filter(item => item.identity === "customer")
      .map(item => ({
        ...item,
        delete: <Button onClick={() => {
          this.handleDelete(item.id);
        }}>Delete</Button>
      }));

    const staffDataSource = this.state.dataSource
      .filter(item => item.identity === "staff")
      .map(item => ({
        ...item,
        delete: <Button onClick={() => {
          this.handleDelete(item.id);
        }}>Delete</Button>
      }));

    return (
      <div>
        <h2>Create User</h2>
        <Form onSubmit={this.handleSubmit} className="login-form">
          <div align="center">
            <Radio.Group defaultValue="customer" Value="a" buttonStyle="solid">
              <Radio.Button value="customer" onClick={this.handleIdentityChange}>Customer</Radio.Button>
              <Radio.Button value="staff" onClick={this.handleIdentityChange}>Staff</Radio.Button>
            </Radio.Group>
          </div>
          <br/>
          <FormItem>
            {getFieldDecorator('username', {
              rules: [{required: true, message: 'Please input your username!'}],
            })(
              <Input prefix={<Icon type="user" style={{fontSize: 13}}/>} placeholder="Username"/>
            )}
          </FormItem>
          <FormItem>
            {getFieldDecorator('password', {
              rules: [{required: true, message: 'Please input your Password!'}],
            })(
              <Input prefix={<Icon type="lock" style={{fontSize: 13}}/>} type="password" placeholder="Password"/>
            )}
          </FormItem>
          {this.state.identity === "staff" &&
          <div>
            <Form.Item>
              {getFieldDecorator('company', {
                rules: [
                  {type: 'array', required: true, message: 'Please select your company'},
                ],
              })(<Cascader options={companies} placeholder="Please select your company"/>)}
            </Form.Item>
            <Form.Item>
              {getFieldDecorator('group', {
                rules: [
                  {type: 'array', required: true, message: 'Please select your group'},
                ],
              })(<Cascader options={groups} placeholder="Please select your group"/>)}
            </Form.Item>
          </div>
          }
          <FormItem>
            <Button type="primary" htmlType="submit" className="login-form-button">
              Create User
            </Button>
          </FormItem>
        </Form>
        <h2>Customer Users</h2>
        <Table dataSource={customerDataSource} columns={customerColumns}/>
        <h2>Staff Users</h2>
        <Table dataSource={staffDataSource} columns={staffColumns}/>
      </div>
    )
  }
}

const WrappedAdminCreateUser = Form.create()(AdminCreateUser);

export default WrappedAdminCreateUser;
