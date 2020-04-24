import React, { Component } from 'react';
import PropTypes from 'prop-types';

import Navbar from '../UI/Navbar/Navbar';
import {
  BrowserRouter as Router,
  Switch,
  Route
} from 'react-router-dom';
import {
  TransitionGroup,
  CSSTransition
} from 'react-transition-group';
import { ThemeProvider } from '@material-ui/core';

import { theme } from '../UI/MaterialTheme';
import History from '../History/History';
import Dashboard from '../Dashboard/Dashboard';

import './Navigator.sass';
import MainLoader from '../UI/Loaders/MainLoader/MainLoader';
import { userSignIn } from '../../actions/userSession';
import { connect } from 'react-redux';
import Login from '../Login/Login';
import SignUp from '../Login/SignUp';
import SignIn from '../Login/SignIn';
import Profile from '../User/Profile';
import Recipe from '../Recipe/Recipe';
import ErrorBoundary from '../ErrorBoundary/ErrorBoundary';

const mapStateToProps = (state) => ({
  user: state.userSession,
  isSignedIn: Boolean(state.userSession?.id)
});

const mapDispatchToProps = (dispatch) => ({
  signIn: (payload) => {
    dispatch(userSignIn(payload));
  }
});

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

    // this.props.signIn({ name: 'ytsejam', id: 2137 })
  }

  componentDidUpdate(prevProps, prevState, snapshot) {
    if (this.props.user !== prevProps.user) {
      console.log(this.props.user);
    }
  }

  render() {
    const { isReady } = this.state;
    const { isSignedIn } = this.props;
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
              <TransitionGroup key={window.location.key}>
                <CSSTransition timeout={500} classNames={'fade'}>
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
                      <Route
                        path="/recipe/:id"
                        component={(props) =>
                          <Recipe {...props} />
                        }
                      />
                    </Switch>
                  </ErrorBoundary>
                </CSSTransition>
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

export default connect(mapStateToProps, mapDispatchToProps)(Navigator);
