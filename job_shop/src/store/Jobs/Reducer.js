import { GET_JOBS_FAILURE, GET_JOBS_REQUEST, GET_JOBS_SUCCESS } from "./ActionType";

const initialState = {
  loading: false,
  error: null,
  jobs: [],
  job: null,
  response: null,
};

export const jobReducer = (state = initialState, action) => {
  switch (action.type) {
    case GET_JOBS_REQUEST:
      return { ...state, loading: true, error: null };
    case GET_JOBS_FAILURE:
      return { ...state, loading: false, error: action.payload };
    case GET_JOBS_SUCCESS:
      return {
        ...state,
        loading: false,
        error: null,
        jobs: action.payload,
      };
    default:
      return state;
  }
};
