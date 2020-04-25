import { USER_SIGN_IN } from './index';

export const userSignIn = (payload) => (dispatch) => {
  dispatch({ type: USER_SIGN_IN, payload });
};
