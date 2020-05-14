import { combineReducers } from 'redux';
import userSession from './userSession';
import cooking from './cooking';
import loading from './loading';
import redirect from './redirectReducer';

export default combineReducers({
  userSession, cooking, loading, redirect
});
