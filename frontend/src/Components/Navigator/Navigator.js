import React, {Component} from 'react';
import PropTypes from 'prop-types';

import Navbar from "../UI/Navbar/Navbar";
import App from "../App";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import {ThemeProvider} from "@material-ui/core";

import {theme} from '../UI/MaterialTheme'
import History from "../History/History";
import Dashboard from "../Dashboard/Dashboard";

import './Navigator.sass'
import MainLoader from "../UI/Loaders/MainLoader/MainLoader";

class Navigator extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isReady: true
    }
  }

  setIsReady = isReady => {
    this.setState({ isReady })
  };

  render() {
    const { isReady } = this.state;
    return (
      <>
        <ThemeProvider theme={theme}>
            <Router>
              {
                !isReady?
                <MainLoader/> :
                null
              }
              <Navbar/>
              <div className="content">
                <Route exact path="/">
                  <Dashboard
                    setIsReady={this.setIsReady}
                  />
                </Route>
                <Route path="/history">
                  <History/>
                </Route>
              </div>
            </Router>
        </ThemeProvider>
      </>
    );
  }
}

Navigator.propTypes = {};

export default Navigator;