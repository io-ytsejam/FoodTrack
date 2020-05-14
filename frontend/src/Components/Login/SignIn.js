import React, { Component } from 'react';
import { Redirect, withRouter } from 'react-router-dom';
import { TextField } from '@material-ui/core';
import { PropTypes } from 'prop-types';
import InputAdornment from '@material-ui/core/InputAdornment';
import { Face } from '@material-ui/icons';
import Button from '@material-ui/core/Button';
import { userSignIn } from '../../actions/userSession';
import { connect } from 'react-redux';

// import PropTypes from 'prop-types';

class SignIn extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: '',
      loginError: undefined
    };
  }

  componentDidUpdate(prevProps, prevState, snapshot) {
    const { userSession } = this.props;
    console.log(userSession);
  }

  handleChange = (event) => {
    this.setState({
      ...this.state,
      [event.target.name]: event.target.value
    });
  };

  handleSignIn = (e) => {
    const { username, password } = this.state;
    const { userSignIn, history } = this.props;
    fetch('/api/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        username,
        password
      })
    }).then((res) => {
      res.json().then((data) => {
        if (res.ok) {
          console.log(data.token);
          userSignIn({ username, authToken: data.token });
          localStorage.setItem('username', username);
          localStorage.setItem('authToken', data.token);
          document.cookie =
            'auth-token=' + data.token + ';max-age=' + (60*60*24*10).toString();
          history.push('/');
        } else {
          this.setState({
            loginError: new Error('Error while connecting to server: ' + data.error)
          });
        }
      }).catch(() => {
        const loginError =
          new Error('Unable to login, response from server is invalid');
        this.setState({ loginError });
      });
    }).catch((loginError) => {
      this.setState({ loginError });
    });
  };
  render() {
    if (this.state.loginError) {
      throw this.state.loginError;
    }
    const { userSession } = this.props;
    return (
      <div>
        <h1>Sign in</h1>
        <form
          onSubmit={(e) => {
            e.preventDefault();
            this.handleSignIn(e);
          }}
        >
          <TextField
            name='username'
            onChange={(event) => {
              this.handleChange(event);
            }}
            required
            InputProps={{
              startAdornment: (
                <InputAdornment position='start'>
                  <Face />
                </InputAdornment>
              )
            }} label="username" color={'secondary'} variant="outlined"/>
          <TextField
            name='password'
            onChange={(event) => {
              this.handleChange(event);
            }}
            required
            type='password'
            label="Password"
            color="secondary"
            variant="outlined"
          />
          <p>
            <Button type='submit'>sign in</Button>
          </p>
        </form>
      </div>
    );
  }
}

SignIn.propTypes = {
  userSession: PropTypes.object
};

const mapStateToProps = (state) => ({
  userSession: state.userSession
});

export default connect(mapStateToProps, { userSignIn })(withRouter(SignIn));
