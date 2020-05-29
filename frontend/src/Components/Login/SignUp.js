import React, { Component } from 'react';
// import PropTypes from 'prop-types';
import { TextField } from '@material-ui/core';
import './SignUp.sass';
import Button from '@material-ui/core/Button';
import { AccountCircle, Face } from '@material-ui/icons';
import InputAdornment from '@material-ui/core/InputAdornment';
import { userSignIn } from '../../actions/userSession';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';

/* eslint-disable no-invalid-this */
class SignUp extends Component {
  constructor(props) {
    super(props);
    this.state = {
      firstName: '',
      lastName: '',
      nickname: '',
      password: '',

      registrationError: undefined,
      registrationStatus: 'create account'
    };
  }

  handleChange = (event) => {
    this.setState({
      ...this.state,
      [event.target.name]: event.target.value
    });
  };

  render() {
    const { registrationStatus, registrationError } = this.state;
    if (registrationError) {
      throw registrationError;
    }
    return (
      <div className="sign-up">
        <h1>Sign up</h1>
        <form
          onSubmit={(event) => {
            event.preventDefault();
            const { firstName, lastName, nickname, password } = this.state;
            const { history } = this.props;

            this.setState({ registrationStatus: 'signing up...' });
            fetch('/api/registration', {
              method: 'POST',
              body: JSON.stringify({ firstName, lastName, nickname, password }),
              mode: 'no-cors'
            })
                .then((res) => res.json())
                .then((data) => {
                  userSignIn({ username: nickname, authToken: data.token });
                  localStorage.setItem('username', nickname);
                  localStorage.setItem('authToken', data.token);
                  document.cookie =
                    'auth-token=' + data.token + ';max-age=' + (60*60*24*10).toString();
                  history.push('/');
                }).catch((err) => {
                  console.error(registrationError);
                  this.setState({ registrationError });
                });
          }}
        >
          <div
            className='input-wrapper'
          >
            <TextField
              name='firstName'
              onChange={(event) => {
                this.handleChange(event);
              }}
              required
              InputProps={{
                startAdornment: (
                  <InputAdornment position='start'>
                    <AccountCircle />
                  </InputAdornment>
                )
              }} label="Name" color={'secondary'} variant="outlined"/>
            <TextField
              name='lastName'
              onChange={(event) => {
                this.handleChange(event);
              }}
              required
              label="Last name"
              color={'secondary'}
              variant="outlined"
            />
            <TextField
              name='nickname'
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
              }} label="nickname" color={'secondary'} variant="outlined"/>
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
            <Button type='submit'>{registrationStatus}</Button>
          </div>
        </form>
      </div>
    );
  }
}

SignUp.propTypes = {};

const mapDispatchToProps = (dispatch) => ({
  userSignIn: (sessionInfo) => {
    dispatch(userSignIn(sessionInfo));
  }
});

export default connect(null, mapDispatchToProps)(withRouter(SignUp));
