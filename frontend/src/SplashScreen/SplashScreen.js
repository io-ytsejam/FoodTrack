import React from 'react';
import PropTypes from 'prop-types';
import { RiseLoader } from 'react-spinners';

let style = {};
const SplashScreen = (props) => {
  return (
    <div style={style}>
      <img src='foodtrack192.png' alt='fdtrck'/>
      <RiseLoader />
    </div>
  );
};

style = {
  position: 'absolute',
  top: 0,
  left: 0,
  width: '100vw',
  height: '100vh',
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
  zIndex: 69,
  backgroundColor: 'wheat',
  flexDirection: 'column'
};

SplashScreen.propTypes = {

};

export default SplashScreen;
