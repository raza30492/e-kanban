import React, { Component } from "react";
import { connect } from 'react-redux';

//components
import App from "grommet/components/App";
import Box from "grommet/components/Box";
import Split from 'grommet/components/Split';
import NavSidebar from "./NavSidebar";
import AppHeader from "./AppHeader";

class Main extends Component {
  render () {
    const { active } = this.props.nav;

    var pane1 = active ? <NavSidebar /> : null;
    var pane2 =  (
      <Box>
        <AppHeader />
        {this.props.children}
      </Box>
    );

    return (
      <App centered={false}>
        <Split flex="right">
          {pane1}
          {pane2}
        </Split>
      </App>
    );
  }
}

let select = (store) => {
  return {nav: store.nav};
};

export default connect(select)(Main);
