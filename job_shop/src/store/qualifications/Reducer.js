import { GET_QUALIFICATION_FAILURE, GET_QUALIFICATION_REQUEST, GET_QUALIFICATION_SUCCESS } from "./ActionType"


const initialState = {
    loading: false,
    error: null,
    quals: [],
    qual: null,
    response: null
}

export const qualReducer=(state=initialState,action)=>{

    switch(action.type)
    {
        case GET_QUALIFICATION_FAILURE:
            return{...state,loading:false,error:action.payload};
        case GET_QUALIFICATION_REQUEST:
            return{...state,loading:true,error:null};
        case GET_QUALIFICATION_SUCCESS:
            return{...state,loading:false,error:null,quals:action.payload};
        default:
            return state;
    }
}