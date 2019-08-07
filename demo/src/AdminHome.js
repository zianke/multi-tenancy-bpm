import React from 'react';
import { Layout, Menu, Icon } from 'antd';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import './App.css';
import AdminCreateTenant from './AdminCreateTenant'
import AdminCreateUser from './AdminCreateUser'

const {Header, Sider, Content} = Layout;

class AdminHome extends React.Component {
  state = {
    collapsed: false,
  };

  toggle = () => {
    this.setState({
      collapsed: !this.state.collapsed,
    });
  };

  render() {
    return (
      <Layout style={{minHeight: '100vh'}}>
        <Router>
          <Sider width={250} trigger={null} collapsible collapsed={this.state.collapsed}>
            <div className="logo">VMWARE ACTIVITI</div>
            <Menu theme="dark" mode="inline">
              <Menu.Item key="1">
                <Icon type="bars"/>
                <Link to="/admin/tenant" style={{display: 'inline'}}><span>Create Tenant</span></Link>
              </Menu.Item>
              <Menu.Item key="2">
                <Icon type="bars"/>
                <Link to="/admin/user" style={{display: 'inline'}}><span>Create User</span></Link>
              </Menu.Item>
              <Menu.Item key="3">
                <Icon type="logout"/>
                <a href="/" style={{display: 'inline'}}><span>Logout</span></a>
              </Menu.Item>
            </Menu>
          </Sider>
          <Layout>
            <Header style={{background: '#fff', padding: 0}}>
              <Icon
                className="trigger"
                type={this.state.collapsed ? 'menu-unfold' : 'menu-fold'}
                onClick={this.toggle}
              />
            </Header>
            <Content
              style={{
                margin: '24px 16px',
                padding: 24,
                background: '#fff',
                minHeight: 280,
              }}
            >
              <div
                style={{
                  margin: 'auto',
                  maxWidth: 1000,
                }}>
              </div>
              <div style={{background: '#fff', minHeight: 360, overflow: 'hidden'}}>
                <Route exact path="/admin/tenant" component={AdminCreateTenant}/>
                <Route exact path="/admin/user" component={AdminCreateUser}/>
              </div>
            </Content>
          </Layout>
        </Router>
      </Layout>
    );
  }
}

export default AdminHome;