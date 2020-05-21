import React, { Component } from 'react';
import './WelcomeScreen.sass';

class WelcomeScreen extends Component {
  render() {
    return (
      <div className='welcome-screen'>
        <h1>WELCOME</h1>
        <p>To be served by a foodtrack, you need to log in first</p>
      </div>
    );
  }
}

export default WelcomeScreen;
