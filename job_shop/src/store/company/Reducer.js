import { CREATE_EMPLOYER_FAILURE, CREATE_EMPLOYER_REQUEST, CREATE_EMPLOYER_SUCCCESS, CREATE_FIELD_FAILURE, CREATE_FIELD_REQUEST, CREATE_FIELD_SUCCCESS, DELETE_EMPLOYER_FAILURE, DELETE_EMPLOYER_REQUEST, DELETE_FIELD_SUCCCESS, GET_ALL_POSTS_FAILURE, GET_ALL_POSTS_REQUEST, GET_ALL_POSTS_SUCCESS, GET_COMPANY_INFO_FAILURE, GET_COMPANY_INFO_REQUEST, GET_COMPANY_INFO_SUCCCESS, GET_EMPLOYERS_FAILURE, GET_EMPLOYERS_REQUEST, GET_EMPLOYERS_SUCCCESS, GET_FIELDS_FAILURE, GET_FIELDS_REQUEST, GET_FIELDS_SUCCCESS, GIVE_EMPLOYER_FIELDS_FAILURE, GIVE_EMPLOYER_FIELDS_REQUEST, GIVE_EMPLOYER_FIELDS_SUCCCESS, UPDATE_COMPANY_INFO_FAILURE, UPDATE_COMPANY_INFO_REQUEST, UPDATE_COMPANY_INFO_SUCCCESS, UPDATE_EMPLOYER_FAILURE, UPDATE_EMPLOYER_REQUEST, UPDATE_FIELD_FAILURE, UPDATE_FIELD_REQUEST } from "./ActionType"

const initialState={
    loading:false,
    data:null,
    error:null,
    employers:[],
    employer:null,
    fields:[],
    posts:[],
    field:null,
    response:null,
    companyData:null,
    isRequestUser:false
}
export const companyReducer=(state=initialState,action)=>
{

    switch(action.type)
    {
        case CREATE_FIELD_REQUEST:
        case CREATE_EMPLOYER_REQUEST:
        case UPDATE_FIELD_REQUEST:
        case UPDATE_EMPLOYER_REQUEST:
        case DELETE_EMPLOYER_REQUEST:
        case GET_EMPLOYERS_REQUEST:
        case GET_FIELDS_REQUEST:
        case GET_ALL_POSTS_REQUEST:
        case GIVE_EMPLOYER_FIELDS_REQUEST:
        case GET_COMPANY_INFO_REQUEST:
        case UPDATE_COMPANY_INFO_REQUEST:
            return {...state,loading:true,error:null};
        case CREATE_FIELD_FAILURE:
        case CREATE_EMPLOYER_FAILURE:
        case UPDATE_FIELD_FAILURE:
        case UPDATE_EMPLOYER_FAILURE:
        case DELETE_EMPLOYER_FAILURE:
        case GET_EMPLOYERS_FAILURE:
        case GET_FIELDS_FAILURE:
        case GET_ALL_POSTS_FAILURE:
        case GIVE_EMPLOYER_FIELDS_FAILURE:
        case GET_COMPANY_INFO_FAILURE:
        case UPDATE_COMPANY_INFO_FAILURE:
            return {...state,loading:false,error:action.payload};
        
        case CREATE_FIELD_SUCCCESS:
            return {
                ...state,
                loading:false,
                error:null,
                fields:[action.payload,...state.fields],
                field:action.payload,
                response:action.payload
            };
        case CREATE_EMPLOYER_SUCCCESS:
            return {
                ...state,
                loading:false,
                error:null,
                employers:[action.payload,...state.employers],
                employer:action.payload,
                response:action.payload
            };

        case GET_COMPANY_INFO_SUCCCESS:
            return {
                ...state,
                loading:false,
                error:null,
                companyData:action.payload.companyDto,
                response:action.payload,
                fields:action.payload.companyFieldsNames,
                employers:action.payload.employersUserName,
                isRequestUser:action.payload.requestUser
            };
        case UPDATE_COMPANY_INFO_SUCCCESS:
            return {
                ...state,
                loading:false,
                error:null,
                companyData:action.payload,
                response:action.payload,
                fields:state.fields,
                employers:state.employers,
                isRequestUser:state.isRequestUser
            };
        case GIVE_EMPLOYER_FIELDS_SUCCCESS:
            return {
                ...state,
                loading:false,
                error:null,
                response:action.payload
            };
        case GET_EMPLOYERS_SUCCCESS:
            return {
                ...state,
                loading:false,
                error:null,
                employers:action.payload
            };

        case GET_FIELDS_SUCCCESS:
            return {
                ...state,
                loading:false,
                error:null,
                fields:action.payload
            };
        case GET_ALL_POSTS_SUCCESS:
            return {
                ...state,
                loading:false,
                error:null,
                posts:action.payload
            };
        case DELETE_FIELD_SUCCCESS:
            return {
                ...state,
                loading:false,
                error:null,
                fields:state.fields.filter((field)=> field.id !== action.payload)
            };
        default:
            return state;
    }
}