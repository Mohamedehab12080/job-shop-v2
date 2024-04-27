import { CREATE_POST_FAILURE, CREATE_POST_REQUEST, CREATE_POST_SUCCESS, DELETE_POST_SUCCESS, FETCH_MATCHED_POSTS_FAILURE, FETCH_MATCHED_POSTS_REQUEST, FETCH_MATCHED_POSTS_SUCCESS, UPDATE_POST_FAILURE, UPDATE_POST_REQUEST } from "./ActionType"

const initialState={
    loading:false,
    data:null,
    error:null,
    posts:[],
    post:null,
    response:null
}

export const postReducer=(state=initialState,action)=>
{
    switch(action)
    {
        case FETCH_MATCHED_POSTS_REQUEST:
        case CREATE_POST_REQUEST:
        case UPDATE_POST_REQUEST:
            return {...state,loading:true,error:null};
        case FETCH_MATCHED_POSTS_FAILURE:
        case CREATE_POST_FAILURE:
        case UPDATE_POST_FAILURE:
            return {...state,loading:false,error:action.payload};
        case CREATE_POST_SUCCESS:
            return {
                ...state,
                loading:false,
                error:null,
                posts:[action.payload,...state.posts],
                response:action.payload
            };
        case FETCH_MATCHED_POSTS_SUCCESS:
            return {
                ...state,
                loading:false,
                error:null,
                posts:action.payload,
                response:null
            };
        case DELETE_POST_SUCCESS:
            return {
                ...state,
                loading:false,
                error:null,
                posts:state.posts.filter((post)=>post.id!==action.payload)
            };
            default:
                return state;
          
    }
}