import React from 'react';
import { Form, Icon, Input, Button, Checkbox, Radio, Cascader, message } from 'antd';
import request from "request";
import { withCookies } from 'react-cookie';
import { API_ENDPOINT } from "./common";

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

class LoginForm extends React.Component {
  state = {
    identity: "customer",
  };

  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        request.post({
          url: `${API_ENDPOINT}/login`,
          body: {
            'id': values.username,
            'password': values.password
          },
          json: true
        }, (error, response, body) => {
          if (response && response.statusCode === 200) {
            const {cookies} = this.props;
            cookies.set('id', body.id);
            cookies.set('token', body.token);
            if (this.state.identity === "customer") {
              this.props.history.push("/customer");
            } else if (this.state.identity === "staff") {
              this.props.history.push("/staff");
            }
          } else {
            message.info('Incorrect username or password!');
            const {cookies} = this.props;
            cookies.remove('id', {path: '/'});
            cookies.remove('token', {path: '/'});
          }
        });
      }
    });
  };

  handleIdentityChange = (e) => {
    this.setState({identity: e.target.value});
  };

  render() {
    const {getFieldDecorator} = this.props.form;
    return (
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
          {getFieldDecorator('remember', {
            valuePropName: 'checked',
            initialValue: true,
          })(
            <Checkbox>Remember me</Checkbox>
          )}
          <a className="login-form-forgot" href="/">Forgot password</a>
          <Button type="primary" htmlType="submit" className="login-form-button">
            Log in
          </Button>
          Or <a href="/">register now!</a>
        </FormItem>
      </Form>
    );
  }
}

const WrappedLoginForm = Form.create()(LoginForm);
export default withCookies(WrappedLoginForm);