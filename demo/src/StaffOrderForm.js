import React from 'react';
import {
  Form,
  Input,
  Cascader,
  Select,
  Button,
  message,
} from 'antd';
import request from "request";
import { withCookies } from 'react-cookie';
import { API_ENDPOINT } from "./common";

const {Option} = Select;
const {TextArea} = Input;

const services = [
  {
    value: 'house-renovation',
    label: 'House Renovation',
    children: [
      {
        value: 'painting',
        label: 'Painting',
      },
      {
        value: 'furniture',
        label: 'Furniture',
      },
      {
        value: 'indoor-decoration',
        label: 'Indoor Decoration',
      },
    ],
  },
  {
    value: 'others',
    label: 'Others',
  },
];

class StaffOrderForm extends React.Component {

  constructor(props) {
    super(props);
    this.state = {order: {}};
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  componentDidMount() {
    const taskId = this.props.match.params.orderId;
    const token = this.props.cookies.get('token');
    request.get({
      url: `${API_ENDPOINT}/task`,
      headers: {
        'Authorization': `Bearer ${token}`
      },
      qs: {
        taskId
      },
      json: true
    }, (error, response, body) => {
      if (response && response.statusCode === 200) {
        const order = {
          id: body.taskId,
          ...JSON.parse(body.orderJson)
        };
        this.setState({order});
      }
    });
  }

  handleSubmit = e => {
    e.preventDefault();
    const taskId = this.props.match.params.orderId;
    const token = this.props.cookies.get('token');
    this.props.form.validateFieldsAndScroll((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
        request.post({
          url: `${API_ENDPOINT}/complete`,
          headers: {
            'Authorization': `Bearer ${token}`
          },
          body: {
            taskId,
            comment: values.comment
          },
          json: true
        }, (error, response, body) => {
          if (response && response.statusCode === 200) {
            message.info('Order processed successfully!');
            this.props.history.push("/staff/processing");
          }
        });
      }
    });
  };

  render() {
    const {getFieldDecorator} = this.props.form;

    const formItemLayout = {
      labelCol: {
        xs: {span: 24},
        sm: {span: 8},
      },
      wrapperCol: {
        xs: {span: 24},
        sm: {span: 16},
      },
    };
    const tailFormItemLayout = {
      wrapperCol: {
        xs: {
          span: 24,
          offset: 0,
        },
        sm: {
          span: 16,
          offset: 8,
        },
      },
    };

    return (
      <div>
        <h2>Processing Order ID: {this.props.match.params.orderId}</h2>

        <Form {...formItemLayout} onSubmit={this.handleSubmit}>
          <Form.Item label="Name">
            <Input value={this.state.order.name}/>
          </Form.Item>
          <Form.Item label="E-mail">
            <Input value={this.state.order.email}/>
          </Form.Item>
          <Form.Item label="Phone Number">
            <Input addonBefore={<Select style={{width: 70}} value={this.state.order.prefix}>
              <Option value="1">+1</Option>
              <Option value="86">+86</Option>
            </Select>} value={this.state.order.phone} style={{width: '100%'}}/>
          </Form.Item>
          <Form.Item label="Home Address">
            <Input value={this.state.order.address}/>
          </Form.Item>
          <Form.Item label="Requested Service">
            <Cascader options={services} value={this.state.order.service}/>
          </Form.Item>
          <Form.Item label="Comment">
            {getFieldDecorator('comment', {
              initialValue: this.state.order.comment,
            })(
              <TextArea rows={4} placeholder="Please input any additional comment on your order."/>,
            )}
          </Form.Item>
          <Form.Item {...tailFormItemLayout}>
            <Button type="primary" htmlType="submit">
              Finish Processing
            </Button>
          </Form.Item>
        </Form>
      </div>
    );
  }
}

const WrappedStaffOrderForm = Form.create({name: 'process'})(StaffOrderForm);
export default withCookies(WrappedStaffOrderForm);
