import {ADD_JOBSEEKER_SKILLS_QUALIFICATIONS_FAILURE, ADD_JOBSEEKER_SKILLS_QUALIFICATIONS_REQUEST, ADD_JOBSEEKER_SKILLS_QUALIFICATIONS_SUCCESS, ADD_JOBSEEKER_SKILLS_REQUEST, ADD_JOBSEEKER_SKILLS_SUCCESS, DELETE_JOBSEEKER_APPLICATION_FAILURE, DELETE_JOBSEEKER_APPLICATION_REQUEST, DELETE_JOBSEEKER_APPLICATION_SUCCESS, GET_JOBSEEKER_APPLICATIONS_FAILURE, GET_JOBSEEKER_APPLICATIONS_REQUEST, GET_JOBSEEKER_APPLICATIONS_SUCCESS, GET_JOBSEEKER_QUALIFICATIONS_FAILURE, GET_JOBSEEKER_QUALIFICATIONS_REQUEST, GET_JOBSEEKER_QUALIFICATIONS_SUCCESS, GET_JOBSEEKER_SKILLS_FAILURE, GET_JOBSEEKER_SKILLS_QUALIFICATIONS_FAILURE, GET_JOBSEEKER_SKILLS_QUALIFICATIONS_REQUEST, GET_JOBSEEKER_SKILLS_QUALIFICATIONS_SUCCESS, GET_JOBSEEKER_SKILLS_REQUEST, GET_JOBSEEKER_SKILLS_SUCCESS } from "./ActionType"


const initialState={
    loading:false,
    error:null,
    skills:[],
    skillsObjects:[],
    qualificationsObjects:[],
    qualifications:[],
    applications:[],
    response:null,
    data:null
}

export const jobSeekerReducer=(state=initialState,action)=>
{

    switch(action.type)
    {
        case GET_JOBSEEKER_SKILLS_QUALIFICATIONS_REQUEST:
        case GET_JOBSEEKER_APPLICATIONS_REQUEST:
        case ADD_JOBSEEKER_SKILLS_QUALIFICATIONS_REQUEST:
        case DELETE_JOBSEEKER_APPLICATION_REQUEST:
            return {...state,loading:true,error:null};
        case GET_JOBSEEKER_SKILLS_QUALIFICATIONS_FAILURE:
        case GET_JOBSEEKER_APPLICATIONS_FAILURE:
        case ADD_JOBSEEKER_SKILLS_QUALIFICATIONS_FAILURE: 
        case DELETE_JOBSEEKER_APPLICATION_FAILURE:
            return {...state,loading:false,error:action.payload};
        case GET_JOBSEEKER_SKILLS_QUALIFICATIONS_SUCCESS:
            return {
                ...state,
                loading:false,
                error:null,
                skillsObjects:action.payload.jobSeekerSkillList,
                qualificationsObjects:action.payload.jobSeekerQualificationsList,
                skills:action.payload.skills,
                qualifications:action.payload.qualifications,
                response:action.payload};
        case ADD_JOBSEEKER_SKILLS_QUALIFICATIONS_SUCCESS:
            return {
                ...state,
                loading:false,
                error:null,
                skills:action.payload.skills,
                qualifications:action.payload.qualifications,
                response:action.payload};
        case GET_JOBSEEKER_APPLICATIONS_SUCCESS:
            return {
                ...state,
                loading:false,
                error:null,
                applications:action.payload,
                response:action.payload};
        case DELETE_JOBSEEKER_APPLICATION_SUCCESS:
            return {
                ...state,
                loading:false,
                error:null,
                applications:state.applications.filter((app)=>app.id !== action.payload),
                response:state.applications.filter((app)=>app.id !== action.payload)};
        default:
            return state;
    }
}