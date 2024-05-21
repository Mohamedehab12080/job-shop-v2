import { FETCH_LOCATIONS_FAILURE, FETCH_LOCATIONS_REQUEST, FETCH_LOCATIONS_SUCCESS } from "./ActionType";

const initialState={
    loading:false,
    data:null,
    error:null,
    locations:[],
    location:null,
    response:null
}


export const locationsReducer=(state=initialState,action)=>
    {
        switch(action.type)
        {
            case FETCH_LOCATIONS_REQUEST:
                return {
                    ...state,
                    loading:true,
                    error:null
                };
            case FETCH_LOCATIONS_FAILURE:
                return {
                    ...state,
                    loading:false,
                    error:action.payload
                };
            case FETCH_LOCATIONS_SUCCESS:
                return {
                    ...state,
                    loading:false,
                    error:null,
                    locations:action.payload,
                    response:action.payload
                };
            default:
                return state;
        }
    }