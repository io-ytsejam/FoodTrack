import React, { Component } from 'react';
import PropTypes from 'prop-types';

import Navbar from '../UI/Navbar/Navbar';
import {
  BrowserRouter as Router,
  Switch,
  Route
} from 'react-router-dom';
import {
  TransitionGroup
} from 'react-transition-group';
import { ThemeProvider } from '@material-ui/core';

import { theme } from '../UI/MaterialTheme';
import History from '../History/History';
import Dashboard from '../Dashboard/Dashboard';

import './Navigator.sass';
import MainLoader from '../UI/Loaders/MainLoader/MainLoader';
import { userSignIn } from '../../actions/userSession';
import { increaseLoading } from '../../actions/loading';
import { connect } from 'react-redux';
import Login from '../Login/Login';
import SignUp from '../Login/SignUp';
import SignIn from '../Login/SignIn';
import Profile from '../User/Profile';
import Recipe from '../Recipe/Recipe';
import ErrorBoundary from '../ErrorBoundary/ErrorBoundary';
import SplashScreen from '../../SplashScreen/SplashScreen';

/* eslint-disable no-invalid-this */
class Navigator extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isReady: true
    };
  }

  setIsReady = (isReady) => {
    this.setState({ isReady });
  };

  componentDidMount() {
    console.log(this.props.user);
    Notification
        .requestPermission()
        .then((status) => {
          console.log(status);
        });

    // this.props.signIn({ name: 'ytsejam', id: 2137 })
  }

  render() {
    const { isReady } = this.state;
    const { isSignedIn, isLoading, increaseLoading } = this.props;
    return (
      <>
        <ThemeProvider theme={theme}>
          {
            isLoading > 0 ? <SplashScreen/> : undefined
          }
          <Router>
            {
              !isReady?
                <MainLoader/> :
                null
            }
            <Navbar/>
            <div className="content">
              <TransitionGroup key={window.location.key}>
                <ErrorBoundary>
                  <Switch location={window.location}>
                    {/* Main page */}
                    <Route exact path="/">
                      <Dashboard
                        setIsReady={this.setIsReady}
                      />
                    </Route>
                    {/* Cooking history */}
                    <Route path="/history">
                      <History/>
                    </Route>
                    {/* User profile */}
                    <Route path="/user-profile">
                      {
                          isSignedIn?
                            <Profile />:
                            <Login />
                      }
                    </Route>
                    {/* Sign in */}
                    <Route path="/sign-in">
                      <SignIn />
                    </Route>
                    {/* Sign up */}
                    <Route path="/sign-up">
                      <SignUp />
                    </Route>
                    <Route path="/recipe/:id">
                      <Recipe />;
                    </Route>
                  </Switch>
                </ErrorBoundary>
              </TransitionGroup>
            </div>
          </Router>
        </ThemeProvider>
      </>
    );
  }
}

Navigator.propTypes = {
  user: PropTypes.object,
  isSignedIn: PropTypes.bool
};

const mapStateToProps = (state) => ({
  user: state.userSession,
  isSignedIn: Boolean(state.userSession?.id),
  isLoading: state.loading.loading
});

export default connect(mapStateToProps, { userSignIn, increaseLoading })(Navigator);
