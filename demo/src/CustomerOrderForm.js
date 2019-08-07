import React from 'react';
import request from 'request';
import { API_ENDPOINT } from './common';
import {
  Form,
  Input,
  Cascader,
  Select,
  Button,
  message,
} from 'antd';

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

class CustomerOrderForm extends React.Component {
  state = {
    confirmDirty: false,
    autoCompleteResult: [],
  };

  handleSubmit = e => {
    e.preventDefault();

    this.props.form.validateFieldsAndScroll((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);

        request.post({
          url: `${API_ENDPOINT}/start`,
          headers: {
            'Authorization': 'Basic YWRtaW46YWRtaW4='
          },
          body: {
            "tenantId": values.tenantId,
            "orderJson": JSON.stringify(values)
          },
          json: true
        }, (error, response, body) => {
          if (response && response.statusCode === 200) {
            message.info('Order submitted successfully!');
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
    const prefixSelector = getFieldDecorator('prefix', {
      initialValue: '86',
    })(
      <Select style={{width: 70}} initialValue="86">
        <Option value="1">+1</Option>
        <Option value="86">+86</Option>
      </Select>,
    );

    return (
      <Form {...formItemLayout} onSubmit={this.handleSubmit}>
        <Form.Item label="Name">
          {getFieldDecorator('name', {
            rules: [{required: true, message: 'Please input your name!', whitespace: true}],
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
                message: 'Please input your E-mail!',
              },
            ],
          })(<Input/>)}
        </Form.Item>
        <Form.Item label="Phone Number">
          {getFieldDecorator('phone', {
            rules: [{required: true, message: 'Please input your phone number!'}],
          })(<Input addonBefore={prefixSelector} style={{width: '100%'}}/>)}
        </Form.Item>
        <Form.Item label="Home Address">
          {getFieldDecorator('address', {
            rules: [{required: true, message: 'Please input your home address!', whitespace: true}],
          })(<Input/>)}
        </Form.Item>
        <Form.Item label="Requested Service">
          {getFieldDecorator('service', {
            rules: [
              {type: 'array', required: true, message: 'Please select the service you request!'},
            ],
          })(<Cascader options={services} placeholder="Please select the service you request."/>)}
        </Form.Item>
        <Form.Item label="Service Provider">
          {getFieldDecorator('tenantId', {
            initialValue: 'tubatu',
          })(
            <Select initialValue="tubatu">
              <Option value="tubatu">土巴兔公司</Option>
              <Option value="skshu">三棵树公司</Option>
            </Select>,
          )}
        </Form.Item>
        <Form.Item label="Comment">
          {getFieldDecorator('comment')(
            <TextArea rows={4} placeholder="Please input any additional comment on your order."/>,
          )}
        </Form.Item>
        <Form.Item {...tailFormItemLayout}>
          <Button type="primary" htmlType="submit">
            Submit Order
          </Button>
        </Form.Item>
      </Form>
    );
  }
}

const WrappedCustomerOrderForm = Form.create({name: 'order'})(CustomerOrderForm);
export default WrappedCustomerOrderForm;
