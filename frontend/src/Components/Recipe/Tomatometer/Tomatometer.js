import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { setTime, pauseResumeTime, stopTime } from '../../../actions/cooking';
import { nextStep, prevStep } from '../../../actions/cooking';
import IconButton from '@material-ui/core/IconButton';
import PlayCircleOutlineIcon from '@material-ui/icons/PlayCircleOutline';
import SkipPreviousIcon from '@material-ui/icons/SkipPrevious';
import SkipNextIcon from '@material-ui/icons/SkipNext';
import PauseCircleOutlineIcon from '@material-ui/icons/PauseCircleOutline';
import Paper from '@material-ui/core/Paper';

class Tomatometer extends Component {
  render() {
    const { inProgress, totalTime, setTime, stopTime, pauseResumeTime, readyInMinutes,
      nextStep, prevStep
    } = this.props;
    const handlePauseResume = (e) => {
      if (!totalTime) {
        return setTime(readyInMinutes * 60);
      }
      pauseResumeTime();
    };
    return (
      <div>
        <Paper>
          <IconButton>
            <SkipPreviousIcon onClick={prevStep} />
          </IconButton>
          <IconButton>
            {
              inProgress ?
                <PauseCircleOutlineIcon onClick={(e) => handlePauseResume(e)} /> :
                <PlayCircleOutlineIcon onClick={(e) => handlePauseResume(e)} />
            }
          </IconButton>
          <IconButton>
            <SkipNextIcon onClick={nextStep} />
          </IconButton>
        </Paper>
      </div>
    );
  }
}

const mapStateToProps = (state) => ({
  inProgress: state.cooking.inProgress,
  passedTime: state.cooking.passed,
  totalTime: state.cooking.total
});

Tomatometer.propTypes = {
  readyInMinutes: PropTypes.number.isRequired,
  inProgress: PropTypes.bool.isRequired,
  passedTime: PropTypes.number.isRequired,
  totalTime: PropTypes.number.isRequired,
  setTime: PropTypes.func.isRequired,
  stopTime: PropTypes.func.isRequired,
  pauseResumeTime: PropTypes.func.isRequired,
  nextStep: PropTypes.func.isRequired,
  prevStep: PropTypes.func.isRequired
};

export default connect(mapStateToProps,
    { setTime, stopTime, pauseResumeTime, nextStep, prevStep })(Tomatometer);
