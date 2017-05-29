import '../scss/index.scss';

import React from 'react';
import ReactDOM from 'react-dom';
import { Router,hashHistory,Route,IndexRoute} from "react-router";

import HelpApp from './components/help/HelpApp';
import Home from './components/help/Home';

import Report from './components/help/ReportHelp';
import User from './components/help/UserHelp';
import Category from './components/help/CategoryHelp';
import SubCategory from './components/help/SubCategoryHelp';
import Section from './components/help/SectionHelp';
import Supplier from './components/help/SupplierHelp';
import Product from './components/help/ProductHelp';

import InwardScan from './components/help/InwardScanHelp';
import OutwardScan from './components/help/OutwardScanHelp';
import Stock from './components/help/StockHelp';
import AwaitingOrder from './components/help/AwaitingOrderHelp';
import GenrateBarcode from './components/help/GenerateBarcodeHelp';

import Tracking from './components/help/TrackingHelp';
import Followup from './components/help/FollowupHelp';


let element = document.getElementById('content');
ReactDOM.render((
    <Router history={hashHistory} >
        <Route path='/' component={HelpApp}>
            <IndexRoute component={Home} />
            <Route path='report' component={Report} />
            <Route path='user' component={User} />
            <Route path='category' component={Category} />
            <Route path='subCategory' component={SubCategory} />
            <Route path='section' component={Section} />
            <Route path='supplier' component={Supplier} />
            <Route path='product' component={Product} />
            <Route path='stock' component={Stock} />
            <Route path='inwardScan' component={InwardScan} />
            <Route path='outwardScan' component={OutwardScan} />
            <Route path='awaitingOrder' component={AwaitingOrder} />
            <Route path='generateBarcode' component={GenrateBarcode} />
            <Route path='followup' component={Followup} />
            <Route path='tracking' component={Tracking} />
        </Route>
    </Router>
), element);

document.body.classList.remove('loading');
