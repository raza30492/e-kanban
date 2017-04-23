import React, {Component} from 'react';
import { connect } from 'react-redux';
import { localeData } from '../reducers/localization';
import {initialize}  from '../actions/misc';

import AppHeader from './AppHeader';
import Box from 'grommet/components/Box';
import Section from 'grommet/components/Section';
import Spinning from 'grommet/components/icons/Spinning';

class Dashboard extends Component {
  constructor () {
    super();
    this.state = {
      initializing: false
    };
  }

  componentWillMount () {
    console.log("componentWillMount");
    if (!this.props.misc.initialized) {
      this.setState({initializing: true});
      this.props.dispatch(initialize());
    }
    this.setState({localeData: localeData()});
  }

  componentWillReceiveProps (nextProps) {
    console.log("componentWillReceiveProps");
    if (!this.props.misc.initialized && nextProps.misc.initialized) {
      this.setState({initializing: false});
    }
  }


  render () {
    const {initializing} = this.state;

    if (initializing) {
      return (
        <Box pad={{vertical: 'large'}}>
          <Box align='center' alignSelf='center' pad={{vertical: 'large'}}>
            <Spinning /> Initializing Application ...
          </Box>
        </Box>
      );
    }

    return (
      <Box>
        <AppHeader />
        <Section direction="column" pad={{vertical: 'large', horizontal:'small'}}>
          <h1>Welcome to Inventory Control System Application</h1>

        </Section>
      </Box>
    );
  }
}

Dashboard.contextTypes = {
  router: React.PropTypes.object.isRequired
};

let select = (store) => {
  return { nav: store.nav, misc: store.misc};
};

export default connect(select)(Dashboard);
