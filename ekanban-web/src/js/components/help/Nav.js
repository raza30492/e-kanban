import React, { Component } from 'react';
import { localeData } from '../../reducers/localization';

import Box from 'grommet/components/Box';
import Header from 'grommet/components/Header';
import Title from 'grommet/components/Title';
import Menu from 'grommet/components/Menu';
import Anchor from 'grommet/components/Anchor';



class Nav extends Component {

  constructor() {
    super();

    this.localeData = localeData();

    this.state = {
      adminPages: [
        {path: '/user', label: this.localeData.label_user},
        {path: '/report', label: this.localeData.label_report},
        {path: '/category', label: this.localeData.label_category},
        {path: '/subCategory', label: this.localeData.label_sub_category},
        {path: '/section', label: this.localeData.label_section},
        {path: '/supplier', label: this.localeData.label_supplier},
        {path: '/product', label: this.localeData.label_product}
      ],
      storePages: [
        {path: '/report', label: this.localeData.label_report},
        {path: '/inwardScan', label: this.localeData.label_inward_scan},
        {path: '/outwardScan', label: this.localeData.label_outward_scan},
        {path: '/awaitingOrder', label: this.localeData.label_awiating_order},
        {path: '/stock', label: this.localeData.label_stock},
        {path: '/generateBarcode', label: this.localeData.label_generate_barcode}
      ],
      purchasePages: [
        {path: '/report', label: this.localeData.label_report},
        {path: '/tracking', label: this.localeData.label_tracking},
        {path: '/followup', label: this.localeData.label_followup}
      ]
    };
  }

  _onClick () {
    delete localStorage.page;
    this.context.router.push('/');
  }
  
  render() {

    const {adminPages, storePages, purchasePages} = this.state;

    const role = localStorage.role;
    let pages = [];
    if (role == 'ADMIN') {
      pages = adminPages;
    } else if ( role == 'STORE') {
      pages = storePages;
    } else if (role == 'PURCHASE') {
      pages = purchasePages;
    } else {
      alert('You are not logged in. Login to view help content.');
    }
    let path = this.context.router.location.pathname;
    const navItems = pages.map((page,index) => {
      const cName = page.path == path ? 'active' : '';
      return (
        <Anchor key={index} path={page.path} label={page.label} className={cName}/>
      );
    });

    return (
      <Box>
        <Header fixed={true} float={false} size="large" colorIndex="neutral-1"  justify="between" pad={{horizontal: "medium"}}>
          <Title onClick={this._onClick.bind(this)}>
            {this.localeData.app_name_help}
          </Title>
          <Box flex={true}
            justify='end'
            direction='row'
            responsive={false}>
            <Menu responsive={true} inline={true} direction='row'>
              {navItems}
            </Menu>
          </Box>
        </Header>
      </Box>
    );
  }
}

Nav.contextTypes = {
  router: React.PropTypes.object.isRequired
};

export default Nav;
