import {
  SET_TIME, PAUSE_RESUME_TIME, STOP_TIME, NEXT_STEP, PREV_STEP,
  LOWER_TIME, RAISE_TIME, SET_RECIPE
} from './index';

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

export const prevStep = (payload) => (dispatch) => {
  dispatch({
    type: PREV_STEP
  });
};

export const nextStep = (payload) => (dispatch) => {
  dispatch({
    type: NEXT_STEP
  });
};

export const raiseTime = (payload) => (dispatch) => {
  dispatch({
    type: RAISE_TIME,
    payload
  });
};

export const lowerTime = (payload) => (dispatch) => {
  dispatch({
    type: LOWER_TIME,
    payload
  });
};

export const setRecipe = (recipe) => (dispatch) => {
  dispatch({
    type: SET_RECIPE,
    payload: recipe
  });
};
