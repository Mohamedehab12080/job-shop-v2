import { api } from "../../config/api";
import { ADD_JOBSEEKER_SKILLS_QUALIFICATIONS_FAILURE, ADD_JOBSEEKER_SKILLS_QUALIFICATIONS_SUCCESS, DELETE_APPLICATION_SUCCESS, DELETE_JOBSEEKER_APPLICATION_FAILURE, DELETE_JOBSEEKER_APPLICATION_SUCCESS, GET_JOBSEEKER_APPLICATIONS_FAILURE, GET_JOBSEEKER_APPLICATIONS_SUCCESS, GET_JOBSEEKER_INFO_FAILURE, GET_JOBSEEKER_INFO_SUCCESS, GET_JOBSEEKER_SKILLS_FAILURE, GET_JOBSEEKER_SKILLS_QUALIFICATIONS_FAILURE, GET_JOBSEEKER_SKILLS_QUALIFICATIONS_SUCCESS, GET_JOBSEEKER_SKILLS_SUCCESS, UPDATE_JOBSEEKER_CONTACTS_FAILURE, UPDATE_JOBSEEKER_CONTACTS_SUCCESS, UPDATE_JOBSEEKER_INFO_FAILURE, UPDATE_JOBSEEKER_INFO_SUCCESS } from "./ActionType";


export const findJobSeekerSkillsQualifications=(userId)=>async(dispatch)=>
{
    try {
        const {data}=await api.get(`/api/jobSeekers/findSkillsAndQualificationsForuserItself/${userId}`);
        console.log("Fetched Job Seeker Qualifications Skills : ",data);
        dispatch({type:GET_JOBSEEKER_SKILLS_QUALIFICATIONS_SUCCESS,payload:data});
    } catch (error) {
        console.log("error",error);
        dispatch({ type:GET_JOBSEEKER_SKILLS_QUALIFICATIONS_FAILURE, payload: error.message })
    }
}

export const findJobSeekerApplications=(userId)=>async(dispatch)=>
    {
        try {
            const {data}=await api.get(`/api/jobSeekers/findAllApplications/${userId}`);
            console.log("Fetched Job Seeker Applications FROM BACK-END : ",data);
            dispatch({type:GET_JOBSEEKER_APPLICATIONS_SUCCESS,payload:data});
        } catch (error) {
            console.log("error",error);
            dispatch({ type:GET_JOBSEEKER_APPLICATIONS_FAILURE, payload: error.message })
        }
    }

    export const deleteJobSeekerApplication=(applicationId)=>async(dispatch)=>
        {
            try {
                const {data}=await api.delete(`/api/jobSeekers/delete-Application/${applicationId}`);
                console.log("Response of delete Application JobSeeker : ",data);
               if(data.status)
                {
                    dispatch({type:DELETE_JOBSEEKER_APPLICATION_SUCCESS,payload:applicationId});
                }else 
                {
                    dispatch({type:DELETE_JOBSEEKER_APPLICATION_FAILURE,payload:`Can't delete Application : ${applicationId}`});
                }
            } catch (error) {
                console.log("error",error);
                dispatch({ type:DELETE_JOBSEEKER_APPLICATION_FAILURE, payload: error.message })
            }
        }
    

export const addSkillsQualifications=(skillsQualificationsData)=>async(dispatch)=>
{
    try {
        const {data}=await api.post(`/api/jobSeekers/save-skills-qualifications`,skillsQualificationsData);
        dispatch({type:ADD_JOBSEEKER_SKILLS_QUALIFICATIONS_SUCCESS,payload:skillsQualificationsData});
    } catch (error) {
        console.log("error",error);
        dispatch({ type:ADD_JOBSEEKER_SKILLS_QUALIFICATIONS_FAILURE, payload: error.message })
    }
}

export const getInfo=(userId)=>async(dispatch)=>
{
    try {
        const {data}=await api.get(`/api/jobSeekerProfile/getInfo/${userId}`);
        console.log("Data Fetched Profile : ",data);
        dispatch({type:GET_JOBSEEKER_INFO_SUCCESS,payload:data});
    } catch (error) {
        console.log("Error getting info : ",error);
        dispatch({type:GET_JOBSEEKER_INFO_FAILURE,payload:error});
    }
}

export const updateProfile=(formData)=>async(dispatch)=>
{
try {
    const {data}=await api.put(`/api/jobSeekers/update`,formData);
    console.log("Data Fetched Profile : ",data);
    dispatch({type:UPDATE_JOBSEEKER_INFO_SUCCESS,payload:data});
} catch (error) {
    dispatch({type:UPDATE_JOBSEEKER_INFO_FAILURE,payload:error});
}
}

export const updateJobSeekerContacts=(contacts)=>async(dispatch)=>
    {
    try {
        const {data}=await api.put(`/api/user/updateContacts`,contacts);
        dispatch({type:UPDATE_JOBSEEKER_CONTACTS_SUCCESS,payload:data});
    } catch (error) {
        dispatch({type:UPDATE_JOBSEEKER_CONTACTS_FAILURE,payload:error.message});
    }
    }
    
    