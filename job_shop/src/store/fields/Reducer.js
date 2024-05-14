import { FETCH_FIELDS_FAILURE, FETCH_FIELDS_REQUEST, FETCH_FIELDS_SUCCESS } from "./ActionType"

const initialState={
    loading:false,
    data:null,
    error:null,
    fields:[],
    field:null,
    response:null
}


export const fieldsReducer=(state=initialState,action)=>
    {
        switch(action.type)
        {
            case FETCH_FIELDS_REQUEST:
                return {
                    ...state,
                    loading:true,
                    error:null
                };
            case FETCH_FIELDS_FAILURE:
                return {
                    ...state,
                    loading:false,
                    error:action.payload
                };
            case FETCH_FIELDS_SUCCESS:
                return {
                    ...state,
                    loading:false,
                    error:null,
                    fields:action.payload,
                    response:action.payload
                };
            default:
                return state;
        }
    }