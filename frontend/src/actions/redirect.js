import { SET_REDIRECT_URL, SET_REDIRECTED } from './index';

export const setRedirectURL = (url) => (dispatch) => {
  dispatch({
    type: SET_REDIRECT_URL,
    payload: url
  });
};

export const setRedirected = (x) => (dispatch) => {
  dispatch({
    type: SET_REDIRECTED,
    payload: x
  });
};
