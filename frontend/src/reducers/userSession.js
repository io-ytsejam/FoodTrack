import { USER_SIGN_IN } from '../actions';

const initialState = {
  name: '',
  lastName: '',
  username: '',
  id: '',
  authToken: ''
};

export default (state = initialState, action) => {
  switch (action.type) {
    case USER_SIGN_IN:
      return {
        ...state,
        ...action.payload,
      };
    default: return state;
  }
};
