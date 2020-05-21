import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { pauseResumeTime } from '../../../actions/cooking';
import './ProgressBar.sass';

class ProgressBar extends Component {
  constructor(props) {
    super(props);
    this.state = {
      timeChanging: false,
      throttleSlider: false,
      currentMax: 0
    };
    this.sliderRef = React.createRef();
  }

  componentDidMount() {
    const slider = this.sliderRef.current;
    document.addEventListener('mouseleave', () => {
      const { inProgress, pauseResumeTime, total } = this.props;
      if (!inProgress && total) {
        pauseResumeTime();
      }
      this.setState({ timeChanging: false });
      slider.style.opacity = 0;
    });
  }


  componentDidUpdate(prevProps) {
    const { progress, stepTime } = this.props;
    if (prevProps.progress !== this.props.progress) {
      if (prevProps.stepTime !== stepTime) {
        this.prevValue = 0;
      } else {
        this.prevValue = this.sliderRef.current.value;
      }
      this.sliderRef.current.value = (progress / 100) * stepTime;
    }
  }

  prevValue = 0;

  delay = () => {
    return new Promise((res) => {
      setTimeout(() => {
        res();
      }, 300);
    });
  }

  render() {
    const { progress, stepTime, inProgress, total } = this.props;
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
          ref={this.sliderRef}
          // value={!this.state.timeChanging || this.props.progress}
          // onChange={this.changeTime}
          onMouseEnter={(e) => {
            this.prevValue = e.target.value;
            e.target.value = (this.props.progress / 100) * stepTime;
            if (inProgress) {
              this.props.pauseResumeTime();
            }
            this.setState({ timeChanging: true });
            e.target.style.opacity = 1;
          }}
          onMouseLeave={(e) => {
            if (!inProgress && total) {
              this.props.pauseResumeTime();
            }
            this.setState({ timeChanging: false });
            e.target.style.opacity = 0;
          }}
          onMouseUp={(e) => {
            this.props.updateTime(e.target.value - this.prevValue);
            // e.target.value = (progress / 100) * stepTime;
          }}
          type='range'
          style={{
            width: '100%',
            position: 'relative',
            bottom: '45px',
            opacity: 0
          }}
          min={1}
          max={stepTime}
        />
      </div>
    );
  }
}

ProgressBar.propTypes = {
  progress: PropTypes.number
};

const mapStateToProps = (state) => ({
  inProgress: state.cooking.inProgress,
  total: state.cooking.total
});

export default connect(mapStateToProps, { pauseResumeTime })(ProgressBar);
