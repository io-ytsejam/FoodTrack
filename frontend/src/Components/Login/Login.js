import React, { Component } from 'react';
// import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';

class Login extends Component {
  render() {
    return (
      <div>
        <h1>Login</h1>
        <h2>You are not signed in</h2>
        <p>
          In order to be served by our fancy Food Track
          <Link to="/sign-in"><strong> SIGN IN </strong></Link>
           or <Link to="/sign-up"><strong> SIGN UP </strong></Link>
            if you don&apos;t have a account yet.
        </p>
      </div>
    );
  }
}

Login.propTypes = {};

export default Login;
