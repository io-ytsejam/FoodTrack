import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { pauseResumeTime } from '../../../actions/cooking';
import './ProgressBar.sass';

class ProgressBar extends Component {
  state = {
    max: 0,
    timeChanging: false,
    throttleSlider: false
  };
  componentDidMount() {
    this.setState({ max: this.props.time });
  }

  render() {
    const { progress, stepTime, inProgress } = this.props;
    const { throttleSlider } = this.state;
    const passedTime = 5.4;
    return (
      <div
        className='progress-bar progress-bar--active'
      >
        <div
          className="progress"
          style={{
            width: progress + '%'
          }}
        >
        </div>
        <input
          value={!this.state.timeChanging || this.props.progress}
          onChange={(e) => {
            console.log('New time: ', e.target.value * stepTime / 100);
            console.log('Passed time in input: ', this.props.passedTime);
            const change = e.target.value * stepTime / 100;
            if (Math.abs(this.props.passedTime - change) > stepTime * 1/40) {
              this.props.updateTime(Math.round(change));
            }
          }}
          onMouseEnter={(e) => {
            if (inProgress) {
              this.props.pauseResumeTime();
            }
            this.setState({ timeChanging: true });
            e.target.style.opacity = 1;
          }}
          onMouseLeave={(e) => {
            if (!inProgress) {
              this.props.pauseResumeTime();
            }
            this.setState({ timeChanging: false });
            e.target.style.opacity = 0;
          }}
          onMouseUp={(e) => {
            return;
            /* console.log(e.nativeEvent.target.value);
            console.log('New time: ', e.target.value * stepTime / 100);
            console.log('Passed time in input: ', this.props.passedTime);
            const change = e.target.value * stepTime / 100;
            if (Math.abs(this.props.passedTime - change) > stepTime * 1/40) {
              this.props.updateTime(Math.round(change));
            }*/
            console.log(e.nativeEvent.target.value);
            console.log('New time: ', e.nativeEvent.target.value * stepTime / 100);
            console.log('Passed time in input: ', this.props.passedTime);
            const change = e.nativeEvent.target.value * stepTime / 100;
            if (Math.abs(this.props.passedTime - change) > stepTime * 1/40) {
              this.props.updateTime(Math.round(change));
            }
          }}
          type='range'
          style={{
            width: '100%',
            position: 'relative',
            bottom: '45px',
            opacity: 0
          }}
          min={1}
          max={this.state.timeChanging ? 100 : stepTime}
        />
      </div>
    );
  }
}

ProgressBar.propTypes = {
  progress: PropTypes.number.isRequired
};

const mapStateToProps = (state) => ({
  inProgress: state.cooking.inProgress
});

export default connect(mapStateToProps, { pauseResumeTime })(ProgressBar);
