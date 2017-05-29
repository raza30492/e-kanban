import React, { Component } from 'react';

import Box from 'grommet/components/Box';
import Paragraph from 'grommet/components/Paragraph';
import Menu from 'grommet/components/Menu';
import Anchor from 'grommet/components/Anchor';
import Section from 'grommet/components/Section';
import Split from 'grommet/components/Split';

class Home extends Component {

  constructor() {
    super();
    this.state = {
      tabs: [
        {id: 'tab1', label: 'First Tab'},
        {id: 'tab2', label: 'Second Tab'},
        {id: 'tab3', label: 'Third Tab'}
      ],
      selectedTab: 'tab1'
    };
  }

  
  componentWillMount() {
    const page = localStorage.page;
    if (page != undefined) {
      this.context.router.push(page);
    }
  }
  

  _onClick (event) {
    this.setState({selectedTab: event.target.id});
  }

  render() {
    const {tabs, selectedTab} = this.state;

    const tabItems = tabs.map((tab,index) => {
      const cName = tab.id == selectedTab ? 'active' : '';
      return (
        <Anchor key={index} id={tab.id} label={tab.label}  className={cName} onClick={this._onClick.bind(this)} />
      );
    });

    let content;
    if (selectedTab == 'tab1') {
      content = (
        <Paragraph size='medium'>
          content of tab1 <br />
            Raised on hip-hop and foster care, defiant city kid Ricky
          gets a fresh start in the New Zealand countryside. He quickly finds himself
          at home with his new foster family: the loving Aunt Bella, the cantankerous
          Uncle Hec, and dog Tupac. When a tragedy strikes that threatens to ship
          Ricky to another home, both he and Hec go on the run in the bush. As a
          national manhunt ensues, the newly branded outlaws must face their
          options: go out in a blaze of glory or overcome their differences and
          survive as a family.
        </Paragraph>
      );
    } else if (selectedTab == 'tab2') {
      content = (
        <Paragraph size='medium'>
          content of tab2 <br />
            Raised on hip-hop and foster care, defiant city kid Ricky
          gets a fresh start in the New Zealand countryside. He quickly finds himself
          at home with his new foster family: the loving Aunt Bella, the cantankerous
          Uncle Hec, and dog Tupac. When a tragedy strikes that threatens to ship
          Ricky to another home, both he and Hec go on the run in the bush. As a
          national manhunt ensues, the newly branded outlaws must face their
          options: go out in a blaze of glory or overcome their differences and
          survive as a family.
        </Paragraph>
      );
    } else if (selectedTab == 'tab3') {
      content = (
        <Paragraph size='medium'>
          content of tab3 <br />
            Raised on hip-hop and foster care, defiant city kid Ricky
          gets a fresh start in the New Zealand countryside. He quickly finds himself
          at home with his new foster family: the loving Aunt Bella, the cantankerous
          Uncle Hec, and dog Tupac. When a tragedy strikes that threatens to ship
          Ricky to another home, both he and Hec go on the run in the bush. As a
          national manhunt ensues, the newly branded outlaws must face their
          options: go out in a blaze of glory or overcome their differences and
          survive as a family.
        </Paragraph>
      );
    }

    return (
      <Box full='horizontal' pad={{horizontal: 'large'}}>
        <Box alignSelf='center' pad={{vertical: 'large'}}>
          <Menu responsive={true} direction='row' size='large'>
            {tabItems}
          </Menu>
        </Box>
        <Section >
          <Split fixed={true} flex='left' priority='left' showOnResponsive='both'>
            <Box justify='center' align='center' pad='medium'>
              {content}
            </Box>
            <Box size='medium'  justify='center' align='center' pad='medium'>
              <Paragraph>
                Static Content <br />
                  Raised on hip-hop and foster care, defiant city kid Ricky
                gets a fresh start in the New Zealand countryside. He quickly finds himself
                at home with his new foster family: the loving Aunt Bella, the cantankerous
                Uncle Hec, and dog Tupac. When a tragedy strikes that threatens to ship
                Ricky to another home
              </Paragraph>
            </Box>
          </Split>
        </Section>
      </Box>
    );
  }
}

Home.contextTypes = {
  router: React.PropTypes.object.isRequired
};

export default Home;
