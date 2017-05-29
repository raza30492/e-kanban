import React, { Component } from "react";

//components
import App from "grommet/components/App";
import Nav from './Nav';

export default class HelpApp extends Component {

  render () {
    return (
      <App centered={false}>
        <Nav />
        {this.props.children}
      </App>
    );
  }
}
