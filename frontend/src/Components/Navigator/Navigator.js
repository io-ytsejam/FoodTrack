import React, { Component } from 'react';
import PropTypes from 'prop-types';

import Navbar from '../UI/Navbar/Navbar';
import {
  BrowserRouter as Router,
  Switch,
  Route, withRouter
} from 'react-router-dom';
import { ThemeProvider } from '@material-ui/core';

import { theme } from '../UI/MaterialTheme';
import History from '../History/History';
import Dashboard from '../Dashboard/Dashboard';
import CreateRecipe from '../Recipe/CreateRecipe/CreateRecipe';

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
import { setRedirectURL, setRedirected } from '../../actions/redirect';
import WelcomeScreen from '../WelcomeScreen/WelcomeScreen';

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

  history = undefined

  verifySession = (history, userSignIn) => {
    const username = localStorage.getItem('username');
    const authToken = localStorage.getItem('authToken');

    userSignIn({ username, authToken });

    if (!authToken) {
      localStorage.setItem('username', '');
      return history.push('/welcome');
    }

    fetch('/api/isLogged', {
      headers: {
        token: authToken
      }
    })
        .then((res) => {
          if (res.ok) {
            res.json().then((isLogged) => {
              if (isLogged) {
                document.cookie = 'auth-token=;max-age=0';
              } else {
                history.push('/welcome');
                userSignIn(null);
              }
            });
          } else throw new Error('User session validation error');
        }).catch((err) => {
          console.error(err.message);
        });
  }

  componentDidMount() {
    const { userSignIn, history } = this.props;

    const getCookie = (name) =>
      document.cookie.replace(new RegExp('(?:(?:^|.*;s*)'+ name +
        's*=s*([^;]*).*$)|^.*$'), '$1');

    this.verifySession(history, userSignIn);
    console.log('TOKEN: ', getCookie('auth-token'));
    console.log(this.props.user);
    Notification
        .requestPermission()
        .then((status) => {
          console.log(status);
        });

    // this.props.signIn({ name: 'ytsejam', id: 2137 })
  }

  componentDidUpdate(prevProps, prevState, snapshot) {
    const { location, history, userSignIn } = this.props;
    if (prevProps.location.pathname !== location.pathname) {
      const { pathname } = location;
      if (!pathname.match(/(\/user-profile|\/sign-in|\/sign-up|\/welcome)/)) {
        this.verifySession(history, userSignIn);
      }
    }
  }

  render() {
    const { isReady } = this.state;
    const { isSignedIn, isLoading } = this.props;
    return (
      <>
        <ThemeProvider theme={theme}>
          {
            isLoading > 0 ? <SplashScreen/> : undefined
          }
          {/* <Router>*/}
          {
              !isReady?
                <MainLoader/> :
                null
          }
          <Navbar/>
          <div className="content">
            <ErrorBoundary key={window.location.pathname}>
              <Switch>
                {/* {!redirect.redirected && redirect.url ? <Redirect to={redirect.url}/> : null}*/}
                {/* Main page */}
                <Route exact path="/">
                  <Dashboard
                    setIsReady={this.setIsReady}
                  />
                </Route>
                <Router path="/welcome">
                  <WelcomeScreen />
                </Router>
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
                <Route path="/recipe/new">
                  <CreateRecipe />
                </Route>
                <Route path="/recipe/:id">
                  <Recipe />
                </Route>
              </Switch>
            </ErrorBoundary>
          </div>
          {/* </Router>*/}
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
  isLoading: state.loading.loading,
  redirect: state.redirect
});

export default connect(mapStateToProps, {
  userSignIn, increaseLoading, setRedirectURL, setRedirected
})(withRouter(Navigator));
