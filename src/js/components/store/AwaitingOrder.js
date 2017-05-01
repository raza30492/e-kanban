import React, { Component } from 'react';
import { localeData } from '../../reducers/localization';
import { connect } from 'react-redux';
import {initialize} from '../../actions/misc';
import moment from 'moment';
import {getHeaders} from  '../../utils/restUtil';
//import {updateInventory} from '../../actions/inventory';

import AppHeader from '../AppHeader';
import Box from 'grommet/components/Box';
import Button from 'grommet/components/Button';
import Section from 'grommet/components/Section';
import Spinning from 'grommet/components/icons/Spinning';
import Table from 'grommet/components/Table';
import TableHeader from 'grommet/components/TableHeader';
import TableRow from 'grommet/components/TableRow';
import Header from 'grommet/components/Header';
import HelpIcon from 'grommet/components/icons/base/Help';
import Search from 'grommet/components/Search';
import Title from 'grommet/components/Title';

class AwaitingOrder extends Component {
  
  constructor () {
    super();
    this.state = {
      initializing: false,
      searching: false,
      searchText: '',
      page: 1,
      inventory: []
    };
    this.localeData = localeData();
    this._getInventory = this._getInventory.bind(this);
    this._loadInventory = this._loadInventory.bind(this);
    this._renderAwaiting = this._renderAwaiting.bind(this);
  }

  componentWillMount () {
    console.log('componentWillMount');
    if (!this.props.misc.initialized) {
      this.setState({initializing: true});
      this.props.dispatch(initialize());
    }else{
      this._loadInventory(this.state.page);
    }
  }

  componentWillReceiveProps (nextProps) {
    if (sessionStorage.session == undefined) {
      this.context.router.push('/');
    }
    if (!this.props.misc.initialized && nextProps.misc.initialized) {
      this.setState({initializing: false});
      this._loadInventory(this.state.page);
    }
    if (this.props.inventory.toggleStatus != nextProps.inventory.toggleStatus) {
      this._loadInventory(this.state.page);
    }
  }

  _getInventory () {
    const {inventory: {inventory}, category: {products}} = this.props;
    const inv = inventory.map((i) => {
      const product = products.find(p => p.id == i.productId);
      return {...i, productName: product.name, prodId: product.productId};
    });
    return inv;
  }

  _loadInventory (page) {
    let inventory = this._getInventory();
    
    inventory = inventory.slice(0, 15*page);
    page = page + 1;
    this.setState({inventory, page});
  }

  _onMoreOrders () {
    const {page, searching} = this.state;
    if (!searching) {
      this._loadInventory(page);
    }
  }

  _onPrint (index) {
    const {inventory} = this.state;
    let invent = inventory.filter(inv => inv.binState.includes('ORDERED'));
    let inv = invent[index];
    let filename = inv.binId + '.pdf';
    const options = { method: 'post', headers: getHeaders()};

    fetch(inv.links[0].href, options)
    .then(function(response) {
      console.log(response);
      return response.blob();
    })
    .then(function(myBlob) {
      var downloadUrl = URL.createObjectURL(myBlob);
      var a = document.createElement("a");
      a.href = downloadUrl;
      a.download = filename;
      document.body.appendChild(a);
      a.click();
    })
    .catch((error)=>{
      console.log(error);
    });
  }

  _onSearch (event) {
    console.log('_onSearch');
    let value = event.target.value;
    if (value.length > 0) {
      let inv = this._getInventory();
      inv = inv.filter(i => i.productName.toLowerCase().includes(value.toLowerCase()) || i.binId.toLowerCase().includes(value.toLowerCase()) || i.prodId.toLowerCase().includes(value.toLowerCase()));
      this.setState({inventory: inv, searchText: value, searching: true});
    }else {
      this.setState({searchText: value, searching: false});
      this._loadInventory(1);
    }
  }

  _onHelpClick () {
    console.log('_onHelpClick');
  }

  _renderAwaiting () {
    let {inventory} = this.state;
 
    if (inventory.length > 0) {
      inventory = inventory.filter((inv) => inv.binState.includes('ORDERED'));
      let items = inventory.map((inv,i) => {
        return (
          <TableRow key={i}  >
            <td>{inv.binId}</td>
            <td>{inv.prodId}</td>
            <td>{inv.productName}</td>
            <td>{inv.binNo}</td>
            <td>{moment(new Date(inv.lastUpdated)).utcOffset('+05:30').format('DD/MM/YYYY, hh:mm A')}</td>
            <td><Button label='Print' onClick={this._onPrint.bind(this,i)} /></td>
          </TableRow>
        );
      });
      return (
        <Box pad={{horizontal: 'medium', vertical: 'medium'}}>
          <Table scrollable={true} onMore={this._onMoreOrders.bind(this)}>
            <TableHeader labels={['Bin Id','Product Id','Product','Bin Number','Ordered At','ACTION']} />

            <tbody>{items}</tbody>
          </Table>       
        </Box>  
      );
    } else {
      return (
        <Box size="medium" alignSelf="center" pad={{horizontal:'medium'}}>
          <h3>No Awaiting Orders available</h3>
        </Box>
      );
    } 
  }

  render() {
    const {initializing, searchText} = this.state;

    if (initializing) {
      return (
        <Box pad={{vertical: 'large'}}>
          <Box align='center' alignSelf='center' pad={{vertical: 'large'}}>
            <Spinning /> Initializing Application ...
          </Box>
        </Box>
      );
    }

    const awaiting = this._renderAwaiting();

    return (
      <Box>
        <AppHeader/>
        <Header size='large' pad={{ horizontal: 'medium' }}>
          <Title responsive={false}>
            <span>{this.localeData.label_awitingOrder}</span>
          </Title>
          <Search inline={true} fill={true} size='medium' placeHolder='Search'
            value={searchText} onDOMChange={this._onSearch.bind(this)} />
          <Button icon={<HelpIcon />} onClick={this._onHelpClick.bind(this)}/>
        </Header>
        <Section>
          <Box pad={{horizontal: 'medium', vertical: 'medium'}}>
            {awaiting}
          </Box>
        </Section>
      </Box>
    );
  }
}

AwaitingOrder.contextTypes = {
  router: React.PropTypes.object
};

let select = (store) => {
  return {misc: store.misc, category: store.category, inventory: store.inventory};
};

export default connect(select)(AwaitingOrder);