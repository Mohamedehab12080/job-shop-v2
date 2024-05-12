import { rejectApplication } from "./Action";
import { ACCEPT_APPLICATION_FAILURE, ACCEPT_APPLICATION_REQUEST, ACCEPT_APPLICATION_SUCCESS, CREATE_POST_FAILURE, CREATE_POST_REQUEST, CREATE_POST_SUCCESS,DELETE_APPLICATION_FAILURE,DELETE_APPLICATION_REQUEST,DELETE_APPLICATION_SUCCESS,DELETE_POST_FAILURE,DELETE_POST_REQUEST,DELETE_POST_SUCCESS,FETCH_COMPANY_POSTS_FAILURE,FETCH_COMPANY_POSTS_REQUEST,FETCH_COMPANY_POSTS_SUCCESS,FETCH_EMPLOYER_POSTS_FAILURE,FETCH_EMPLOYER_POSTS_REQUEST,FETCH_EMPLOYER_POSTS_SUCCESS,FETCH_MATCHED_POSTS_FAILURE, FETCH_MATCHED_POSTS_REQUEST, FETCH_MATCHED_POSTS_SUCCESS, FETCH_POST_APPLICATIONS_FAILURE, FETCH_POST_APPLICATIONS_REQUEST, FETCH_POST_APPLICATIONS_SUCCESS, FIND_POST_BY_ID_FAILURE, FIND_POST_BY_ID_REQUEST, FIND_POST_BY_ID_SUCCESS, MAKE_APPLICATION_FAILURE, MAKE_APPLICATION_REQUEST, MAKE_APPLICATION_SUCCESS, REJECT_APPLICATION_FAILURE, REJECT_APPLICATION_REQUEST, REJECT_APPLICATION_SUCCESS, SHOW_POSTS_AFTER_APPLY_FAILURE, SHOW_POSTS_AFTER_APPLY_REQUEST, SHOW_POSTS_AFTER_APPLY_SUCCESS, UPDATE_POST_FAILURE, UPDATE_POST_REQUEST, UPDATE_POST_SUCCESS } from "./ActionType"

const initialState={
    loading:false,
    data:null,
    error:null,
    posts:[],
    userPosts:[],
    applications:[],
    remainedSkills:[],
    remainedQualifications:[],
    stateOfApplication:null,
    post:null,
    response:null
}


export const postReducer=(state=initialState,action)=>
{
    switch(action.type)
    {
        case FETCH_MATCHED_POSTS_REQUEST:
        case FETCH_COMPANY_POSTS_REQUEST:
        case FETCH_POST_APPLICATIONS_REQUEST:
        case ACCEPT_APPLICATION_REQUEST:
        case DELETE_APPLICATION_REQUEST:
        case DELETE_POST_REQUEST:
        case UPDATE_POST_REQUEST:
        case CREATE_POST_REQUEST:
        case MAKE_APPLICATION_REQUEST:
        case SHOW_POSTS_AFTER_APPLY_REQUEST:
        case REJECT_APPLICATION_REQUEST:
        case FETCH_EMPLOYER_POSTS_REQUEST:
        case FIND_POST_BY_ID_REQUEST:
            return {...state,loading:true,error:null};
        case FETCH_MATCHED_POSTS_FAILURE:
        case FETCH_COMPANY_POSTS_FAILURE:
        case FETCH_POST_APPLICATIONS_FAILURE:
        case ACCEPT_APPLICATION_FAILURE:
        case DELETE_APPLICATION_FAILURE:
        case DELETE_POST_FAILURE:
        case UPDATE_POST_FAILURE:
        case CREATE_POST_FAILURE:
        case MAKE_APPLICATION_FAILURE:
        case SHOW_POSTS_AFTER_APPLY_FAILURE:
        case REJECT_APPLICATION_FAILURE:
        case FETCH_EMPLOYER_POSTS_FAILURE:
        case FIND_POST_BY_ID_FAILURE:
            return {...state,loading:false,error:action.payload};
        case FETCH_MATCHED_POSTS_SUCCESS:
        case FETCH_COMPANY_POSTS_SUCCESS:
            return {
                ...state,
                loading:false,
                error:null,
                posts:action.payload,
                response:action.payload
            };
        case FIND_POST_BY_ID_SUCCESS:
            return {
                ...state,
                loading:false,
                error:null,
                post:action.payload,
                response:action.payload
            };
        case FETCH_EMPLOYER_POSTS_SUCCESS:
            return {
                ...state,
                loading:false,
                error:null,
                userPosts:state.posts.filter((post)=>post.employerId === action.payload),
                response:action.payload
            };
        case FETCH_POST_APPLICATIONS_SUCCESS:
            return {
                ...state,
                loading:false,
                error:null,
                applications:action.payload,
                response:action.payload
            };
        case ACCEPT_APPLICATION_SUCCESS: 
            const applicationsPast=state.applications.filter((app)=> app.id !== action.payload.id);
            return {
                ...state,
                loading:false,
                error:null, 
                applications:[action.payload,...applicationsPast],
                response:[action.payload,...applicationsPast]
            };
        case DELETE_APPLICATION_SUCCESS:
        case REJECT_APPLICATION_SUCCESS:
            return {
                ...state,
                loading:false,
                error:null,
                applications:state.applications.filter((app)=> app.id !== action.payload.id),
                response:state.applications.filter((app)=> app.id !== action.payload.id)
            };
        case MAKE_APPLICATION_SUCCESS:
            if (action.payload && action.payload.remainedSkills && action.payload.remainedQualifications && action.payload.matched) {
                return {
                  ...state,
                  loading: false,
                  error: null,
                  remainedSkills: action.payload.remainedSkills,
                  remainedQualifications: action.payload.remainedQualifications,
                  stateOfApplication: action.payload.matched,
                //   posts: state.posts.filter(post => post.id !== action.payload.postId),
                  response: action.payload
                };
              } else {
                console.log("A7A APPLY : Invalid payload received in MAKE_APPLICATION_SUCCESS",)
                // Handle case where payload or its necessary properties are undefined
                return {
                  ...state,
                  loading: false,
                  error: 'Invalid payload received in MAKE_APPLICATION_SUCCESS',
                  response: null
                };
              }
        case SHOW_POSTS_AFTER_APPLY_SUCCESS:
            const updatedPosts = state.posts.filter(post => post.id !== action.payload);
            return {
                ...state,
                loading: false,
                error: null,
                posts: updatedPosts,
                response: action.payload, // Assuming you want to store the deleted post's id in response
              };
        case CREATE_POST_SUCCESS:
            return {
                ...state,
                loading:false,
                error:null,
                posts:[action.payload,...state.posts],
                post:action.payload,
                response:action.payload
            };
        case DELETE_POST_SUCCESS:
            return {
                ...state,
                loading:false,
                error:null,
                posts:state.posts.filter((post)=> post.id !== action.payload),
                response:action.payload
            };
        case UPDATE_POST_SUCCESS:
            return {
                ...state,
                loading:false,
                error:null,
                posts:[action.payload,...state.posts],
                response:action.payload
            };
        default:
            return state;
          
    }
}