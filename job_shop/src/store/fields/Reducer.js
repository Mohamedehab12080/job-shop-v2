import {
  FETCH_FIELDS_FAILURE,
  FETCH_FIELDS_FOR_CREATE_FAILURE,
  FETCH_FIELDS_FOR_CREATE_REQUEST,
  FETCH_FIELDS_FOR_CREATE_SUCCESS,
  FETCH_FIELDS_REQUEST,
  FETCH_FIELDS_SUCCESS,
} from "./ActionType";

const initialState = {
  loading: false,
  data: null,
  error: null,
  fields: [],
  field: null,
  response: null,
  fieldsForCreate: [],
};

export const fieldsReducer = (state = initialState, action) => {
  switch (action.type) {
    case FETCH_FIELDS_REQUEST:
    case FETCH_FIELDS_FOR_CREATE_REQUEST:
      return {
        ...state,
        loading: true,
        error: null,
      };
    case FETCH_FIELDS_FAILURE:
    case FETCH_FIELDS_FOR_CREATE_FAILURE:
      return {
        ...state,
        loading: false,
        error: action.payload,
      };
    case FETCH_FIELDS_SUCCESS:
      return {
        ...state,
        loading: false,
        error: null,
        fields: action.payload,
        response: action.payload,
      };
    case FETCH_FIELDS_FOR_CREATE_SUCCESS:
      return {
        ...state,
        loading: false,
        error: null,
        fields: state.fields,
        fieldsForCreate: action.payload,
        response: action.payload,
      };
    default:
      return state;
  }
};
