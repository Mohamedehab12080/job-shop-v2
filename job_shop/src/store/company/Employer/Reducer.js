import { GET_EMPLOYER_FIELDS_FAILURE, GET_EMPLOYER_FIELDS_REQUEST, GET_EMPLOYER_FIELDS_SUCCESS, GET_EMPLOYER_POSTS_FAILURE, GET_EMPLOYER_POSTS_REQUEST, GET_EMPLOYER_POSTS_SUCCESS, GET_EMPLOYER_PROFILE_FAILURE, GET_EMPLOYER_PROFILE_REQUEST, GET_EMPLOYER_PROFILE_SUCCESS, UPDATE_POST_FAILURE, UPDATE_POST_REQUEST, UPDATE_POST_SUCCESS } from "./ActionType"

const initialState={
    loading:false,
    data:null,
    error:null,
    posts:[],
    post:null,
    fields:[],
    field:null,
    response:null,
    employerData:null,
    isRquestUser:false
}

export const employerReducer=(state=initialState,action)=>
{
    switch(action.type)
    {
        case GET_EMPLOYER_POSTS_FAILURE:
        case GET_EMPLOYER_FIELDS_FAILURE:
        case GET_EMPLOYER_PROFILE_FAILURE:
            return {...state,loading:false,error:action.payload};
        case GET_EMPLOYER_POSTS_REQUEST:
        case GET_EMPLOYER_FIELDS_REQUEST:
        case GET_EMPLOYER_PROFILE_REQUEST:
            return {...state,loading:true,error:null};
        case GET_EMPLOYER_POSTS_SUCCESS:
            return {
                ...state,
                loading:false,
                error:null,
                posts:action.payload,
                response:action.payload
            }
        case GET_EMPLOYER_FIELDS_SUCCESS:
            return {
                ...state,
                loading:false,
                error:null,
                fields:action.payload,
                response:action.payload
            }
        case GET_EMPLOYER_PROFILE_SUCCESS:
            return {
                ...state,
                loading:false,
                error:null,
                response:action.payload,
                employerData:action.payload.employerDto,
                isRquestUser:action.payload.isRquestUser
            }
        default:
            return state;
    }
}