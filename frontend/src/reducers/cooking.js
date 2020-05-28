import {
  SET_TIME, STOP_TIME, PAUSE_RESUME_TIME,
  NEXT_STEP, PREV_STEP, RAISE_TIME, LOWER_TIME
} from '../actions/index';

const initialState = {
  total: 0,
  passed: {
    minutes: 0,
    seconds: 0
  },
  inProgress: false,
  currentStep: 0,
  recipe: {}
};

export default (state = initialState, action) => {
  const { payload } = action;
  switch (action.type) {
    case SET_TIME:
      return {
        ...initialState,
        total: action.payload,
        inProgress: (action.payload !== -1)
      };
    case STOP_TIME:
      return initialState;
    case PAUSE_RESUME_TIME:
      return {
        ...state,
        inProgress: !state.inProgress
      };
    case NEXT_STEP:
      return {
        ...state,
        currentStep: state.currentStep + 1
      };
    case PREV_STEP:
      return {
        ...state,
        currentStep: state.currentStep - 1
      };
    case RAISE_TIME: {
      const { seconds, minutes } = state.passed;
      const date = new Date();
      date.setMinutes(minutes);
      date.setSeconds(seconds + (payload || 1));
      return {
        ...state,
        passed: {
          minutes: date.getMinutes(),
          seconds: date.getSeconds()
        }
      };
    } case LOWER_TIME: {
      const { seconds, minutes } = state.passed;
      const date = new Date();
      date.setSeconds(seconds);
      date.setMinutes(minutes);
      date.setSeconds(seconds - (payload || 1));
      return {
        ...state,
        passed: {
          minutes: date.getMinutes(),
          seconds: date.getSeconds()
        }
      };
    } default: return state;
  }
};
