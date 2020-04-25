import { SET_TIME, STOP_TIME, PAUSE_RESUME_TIME } from '../actions/index';

const initialState = {
  total: 0,
  passed: 0,
  inProgress: false
};

export default (state = initialState, action) => {
  switch (action.type) {
    case SET_TIME:
      return {
        ...state,
        total: action.payload
      };
    case STOP_TIME:
      return initialState;
    case PAUSE_RESUME_TIME:
      return {
        ...state,
        inProgress: !state.inProgress
      };
    default: return state;
  }
};
