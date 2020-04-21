import React, { Component } from 'react';
// import PropTypes from 'prop-types';
import { TextField } from '@material-ui/core';
import './SignUp.sass';
import Button from '@material-ui/core/Button';
import { AccountCircle, Face } from '@material-ui/icons';
import InputAdornment from '@material-ui/core/InputAdornment';

/* eslint-disable no-invalid-this */
class SignUp extends Component {
  constructor(props) {
    super(props);
    this.state = {
      name: '',
      lastName: '',
      userName: '',
      password: ''
    };
  }

  handleChange = (event) => {
    console.log(event.target.value);
    this.setState({
      ...this.state,
      [event.target.name]: event.target.value
    });
  };

  render() {
    return (
      <div className="sign-up">
        <h1>Sign up</h1>
        <form
          onSubmit={(event) => {
            event.preventDefault();
            alert('Sign up with: ' + JSON.stringify(this.state));
          }}
        >
          <div
            className='input-wrapper'
          >
            <TextField
              name='name'
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
              name='userName'
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
              }} label="Username" color={'secondary'} variant="outlined"/>
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
            <Button type='submit'>Create account</Button>
          </div>
        </form>
      </div>
    );
  }
}

SignUp.propTypes = {};

export default SignUp;
