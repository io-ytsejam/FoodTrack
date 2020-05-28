import React from 'react';
import PropTypes from 'prop-types';
import ProgressBar from '../ProgressBar/ProgressBar';
import Paper from '@material-ui/core/Paper';
import { nextStep, raiseTime, lowerTime } from '../../../actions/cooking';
import { connect } from 'react-redux';

class Step extends React.Component {
  state = {
    time: 1
  };

  componentDidMount() {
    console.log(this.props.time);
    this.setState({ time: this.props.time });
  }

  updateTime = (props) => (time) => {
    const { raiseTime, lowerTime } = props;
    /* const change = time - (props.passedTime.minutes * 60 + props.passedTime.seconds);
    lowerTime((props.passedTime.minutes * 60 + props.passedTime.seconds) - time);*/
    const change = time;
    if (change > 0) {
      raiseTime(change);
    } else if (change < 0) {
      lowerTime(change * -1);
    }
  };

  render() {
    const { step, currentStep, index, time, passedTime, nextStep } = this.props;
    const progress = (passed, total) => {
      const progress = ((passed.minutes * 60 + passed.seconds) / time)*100;
      if (progress < 100) {
        return progress;
      } else {
        if (document.visibilityState === 'visible') {
          return nextStep();
        }
        const { index } = this.props;
        const title = 'Step number ' + (index + 1) + ' is done!';
        const icon = '/foodtrack64.png';
        const body = 'Time for next one';
        const stepNotification = new Notification(title, { icon, body });
        const autoHideNotyfication = setTimeout(() => {
          stepNotification.close();
        }, 5000);
        document.onvisibilitychange = () => {
          if (document.visibilityState === 'visible') {
            clearTimeout(autoHideNotyfication);
            stepNotification.close();
          }
        };
        return nextStep();
      }
    };

    return (
      <Paper
        elevation={3}
        key={index}
        className={currentStep === index ? 'recipe recipe--active' : 'recipe'}
        style={{ padding: '20px', marginTop: '-5px', marginBottom: '-5px' }}>
        <p>{step.stepdescription}</p>
        {
          currentStep === index ?
            <ProgressBar
              stepTime={this.state.time}
              updateTime={this.updateTime(this.props)}
              progress={progress(passedTime, undefined)}
              passedTime={passedTime.minutes * 60 + passedTime.seconds}
            /> : null
        }
      </Paper>
    );
  }
}

Step.propTypes = {
  step: PropTypes.object.isRequired,
  key: PropTypes.number.isRequired,
  currentStep: PropTypes.number,
  index: PropTypes.number,
  passedTime: PropTypes.object,
  totalTime: PropTypes.number,
  time: PropTypes.number
};

const mapStateToProps = (state) => ({
  currentStep: state.cooking.currentStep,
  passedTime: state.cooking.passed,
  totalTime: state.cooking.total
});

export default connect(
    mapStateToProps, {
      nextStep,
      lowerTime,
      raiseTime
    })(Step);
