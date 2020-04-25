import { combineReducers } from 'redux';
import userSession from './userSession';
import cooking from './cooking';
import loading from './loading';

export default combineReducers({
  userSession,
  cooking,
  loading
});
