import { SET_TIME, PAUSE_RESUME_TIME, STOP_TIME } from './index';

export const setTime = (payload) => (dispatch) => {
  dispatch({
    type: SET_TIME,
    payload
  });
};

export const pauseResumeTime = (payload) => (dispatch) => {
  dispatch({
    type: PAUSE_RESUME_TIME,
    payload
  });
};

export const stopTime = (payload) => (dispatch) => {
  dispatch({
    type: STOP_TIME,
    payload
  });
};
