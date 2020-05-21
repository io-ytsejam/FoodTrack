import { INCREASE_LOADING, DECREASE_LOADING, CLEAR_LOADING } from '../actions/index';

const initialState = {
  loading: 0
};

export default (state = initialState, action) => {
  switch (action.type) {
    case INCREASE_LOADING: return {
      loading: state.loading + 1
    };
    case DECREASE_LOADING: return {
      loading: (state.loading > 0) ? state.loading - 1 : 0
    };
    case CLEAR_LOADING: return {
      loading: 0
    };
    default: return state;
  }
};
