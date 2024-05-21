import { GET_RECOMMEDED_POSTS_FAILURE, GET_RECOMMEDED_POSTS_REQUEST, GET_RECOMMEDED_POSTS_SUCCESS } from "./ActionType"



const initialState={
    loading:false,
    data:null,
    error:null,
    posts:[],
    response:[],
}

export const recommedReducer=(state=initialState,action)=>
    {
        switch(action.type)
        {
            case GET_RECOMMEDED_POSTS_REQUEST:
                return {...state,loading:true,error:null};
            case GET_RECOMMEDED_POSTS_FAILURE:
                return {...state,loading:false,error:action.payload};
            case GET_RECOMMEDED_POSTS_SUCCESS:
                return {
                    ...state,
                    loading:false,
                    error:null,
                    posts:action.payload.postDtosResponse,
                    response:action.payload.pythonResponses
                }
            default:
                return state;
        }
    }