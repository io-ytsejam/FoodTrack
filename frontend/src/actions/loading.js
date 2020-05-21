import { INCREASE_LOADING, DECREASE_LOADING, CLEAR_LOADING } from './index';

export const increaseLoading = () => (dispatch) => {
  dispatch({
    type: INCREASE_LOADING,
    payload: undefined
  });
};

export const decreaseLoading = () => (dispatch) => {
  dispatch({
    type: DECREASE_LOADING
  });
};

export const clearLoading = () => (dispatch) => {
  dispatch({
    type: CLEAR_LOADING
  });
};
