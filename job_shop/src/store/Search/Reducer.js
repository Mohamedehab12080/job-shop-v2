import {
  SEARCH_USER_FAILURE,
  SEARCH_USER_REQUEST,
  SEARCH_USER_SUCCESS,
} from "./ActionType";

const initialState = {
  loading: false,
  error: null,
  data: null,
  users: [],
  response: null,
};

export const searchReducer = (state = initialState, action) => {
  switch (action.type) {
    case SEARCH_USER_REQUEST:
      return { ...state, loading: true, error: null };
    case SEARCH_USER_FAILURE:
      return { ...state, loading: false, error: action.payload };
    case SEARCH_USER_SUCCESS:
      return {
        ...state,
        loading: false,
        error: null,
        data: action.payload,
        users: action.payload,
        response: action.payload,
      };
    default:
      return state;
  }
};
