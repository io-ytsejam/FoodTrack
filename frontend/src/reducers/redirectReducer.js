import { SET_REDIRECT_URL, SET_REDIRECTED } from '../actions';

const initialState = {
  url: '',
  redirected: false
};

export default (state = initialState, action) => {
  switch (action.type) {
    case SET_REDIRECT_URL:
      return {
        redirected: action.payload ? false : state.redirected,
        url: action.payload
      };
    case SET_REDIRECTED:
      return {
        ...state,
        redirected: action.payload
      };
    default: return state;
  }
};
