import React, { Component } from 'react';

class Help extends Component {

  componentWillMount() {
    const helpUrl = window.baseUrl + "/help";
    window.open(helpUrl);
  }
  
  render() {
    return (
      <div>
        Online Help
      </div>
    );
  }
}

Help.contextTypes = {
  router: React.PropTypes.object.isRequired
};

export default Help;
