import { combineReducers } from 'redux';
import userSessionReducer from './userSessionReducer';

export default combineReducers({
  userSession: userSessionReducer,
});
