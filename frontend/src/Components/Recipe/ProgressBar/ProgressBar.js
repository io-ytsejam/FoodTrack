import React, { Component } from 'react';
import PropTypes from 'prop-types';
import './ProgressBar.sass';

class ProgressBar extends Component {
  render() {
    const { currentStep, index } = this.props;
    return (
      <div
        className={
          'progress-bar' + (currentStep === index ? ' progress-bar--active' : '')
        }
      >
        <div
          className="progress"
          style={{
            width: '69%'
          }}
        >

        </div>
      </div>
    );
  }
}

ProgressBar.propTypes = {
  currentStep: PropTypes.bool.isRequired,
  index: PropTypes.number.isRequired
};

export default ProgressBar;
