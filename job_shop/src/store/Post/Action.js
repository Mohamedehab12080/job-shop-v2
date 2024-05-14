import axios from "axios"
import { ACCEPT_APPLICATION_FAILURE, ACCEPT_APPLICATION_SUCCESS, CREATE_POST_FAILURE, CREATE_POST_SUCCESS, DELETE_APPLICATION_FAILURE, DELETE_APPLICATION_SUCCESS, DELETE_POST_FAILURE, DELETE_POST_SUCCESS, FETCH_COMPANY_POSTS_FAILURE, FETCH_COMPANY_POSTS_SUCCESS, FETCH_EMPLOYER_POSTS_FAILURE, FETCH_EMPLOYER_POSTS_SUCCESS, FETCH_MATCHED_POSTS_FAILURE, FETCH_MATCHED_POSTS_SUCCESS, FETCH_MATCHED_SEARCH_POSTS_FAILURE, FETCH_MATCHED_SEARCH_POSTS_SUCCESS, FETCH_POST_APPLICATIONS_FAILURE, FETCH_POST_APPLICATIONS_SUCCESS, FETCH_POST_BY_ID_SUCCESS, FIND_POST_BY_ID_FAILURE, FIND_POST_BY_ID_SUCCESS, MAKE_APPLICATION_FAILURE, MAKE_APPLICATION_SUCCESS, REJECT_APPLICATION_FAILURE, REJECT_APPLICATION_SUCCESS, SHOW_POSTS_AFTER_APPLY_FAILURE, SHOW_POSTS_AFTER_APPLY_SUCCESS } from "./ActionType"
import { api } from "../../config/api"


export const createPost=(postData)=>async(dispatch)=>
{
    try {
        const {data}=await api.post(`/api/employer/post`,postData)
        console.log("Created Post Response : ",data)
        dispatch({type:CREATE_POST_SUCCESS,payload:data})
    } catch (error) {
        console.error("Error : ",error)
        dispatch({type:CREATE_POST_FAILURE,payload:error.message})
    }
}

export const findPostById=(postId)=>async(dispatch)=>
{
    try {
        const {data}=await api.get(`/api/Post/findById/${postId}`)
        console.log("Fetched Post By Id : ",data)
        dispatch({type:FIND_POST_BY_ID_SUCCESS,payload:data})
    } catch (error) {
        console.error("Error : ",error)
        dispatch({type:FIND_POST_BY_ID_FAILURE,payload:error.message})
    }
}

export const fetchMatchedPosts=(userId)=>async(dispatch)=>
{
    try {
        const {data}=await api.get(`/api/Post/findPostsWithProfileSkills/${userId}`)
        dispatch({type:FETCH_MATCHED_POSTS_SUCCESS,payload:data})
    } catch (error) {
        console.error("Error : ",error)
        dispatch({type:FETCH_MATCHED_POSTS_FAILURE,payload:error.message})
    }
}
export const findFilteredPosts=(searchData)=>async(dispatch)=>
{
    try {
        const {data}=await api.post(`/api/Post/postSearch`,searchData);
        console.log("returned Posts From Search : ",data);
        dispatch({type:FETCH_MATCHED_SEARCH_POSTS_SUCCESS,payload:data});
    } catch (error) {
        dispatch({type:FETCH_MATCHED_SEARCH_POSTS_FAILURE,payload:error.message})
    }
}
export const fetchCompanyPosts=(userId)=>async(dispatch)=>
{
    try {
        const {data}=await api.get(`/api/Post/findPostsForCompany/${userId}`)
        console.log("DATA FROM BACK FETCH BOSTS : ",data);
        dispatch({type:FETCH_COMPANY_POSTS_SUCCESS,payload:data})
    } catch (error) {
        console.error("Error : ",error)
        dispatch({type:FETCH_COMPANY_POSTS_FAILURE,payload:error.message})
    }
}

export const fetchEmployerPosts=(userId)=>async(dispatch)=>
    {
        try {
            // const {data}=await api.get(`/api/Post/findPostsForEmployer/${userId}`)
            // console.log("DATA FROM BACK FETCH BOSTS fOR empoyer : ",data);
            dispatch({type:FETCH_EMPLOYER_POSTS_SUCCESS,payload:userId})
        } catch (error) {
            console.error("Error : ",error)
            dispatch({type:FETCH_EMPLOYER_POSTS_FAILURE,payload:error.message})
        }
    }

export const fetchBestPostApplications=(postId)=>async(dispatch)=>
{
    try {
        const {data}=await api.get(`/api/Post/getBestApplications/${postId}`);
        console.log("FETCHED APPLICATIONS FROM BACK : ",data)
        dispatch({type:FETCH_POST_APPLICATIONS_SUCCESS,payload:data})
    } catch (error) {
        console.error("Error : ",error)
        dispatch({type:FETCH_POST_APPLICATIONS_FAILURE,payload:error.message})
    }
}

export const deleteApplication=(applicationsId)=>async(dispatch)=>
{
    try {
        const {data}=await api.delete(`/api/Post/deleteApplication/${applicationsId}`)
        dispatch({type:DELETE_APPLICATION_SUCCESS,payload:data})
    } catch (error) {
        console.error("Error : ",error)
        dispatch({type:DELETE_APPLICATION_FAILURE,payload:error.message})
    }
}
export const rejectApplication=(applicationsId)=>async(dispatch)=>
    {
        try {
            const {data}=await api.delete(`/api/Post/rejectApplication/${applicationsId}`)
            dispatch({type:REJECT_APPLICATION_SUCCESS,payload:data})
        } catch (error) {
            console.error("Error : ",error)
            dispatch({type:REJECT_APPLICATION_FAILURE,payload:error.message})
        }
    }
export const acceptApplication=(applicationsId)=>async(dispatch)=>
{
    try {
        const {data}=await api.put(`/api/Post/acceptApplication/${applicationsId}`)
        dispatch({type:ACCEPT_APPLICATION_SUCCESS,payload:data})
    } catch (error) {
        console.error("Error : ",error)
        dispatch({type:ACCEPT_APPLICATION_FAILURE,payload:error.message})
    }
}

export const applyForPost=(applicationData)=>async(dispatch)=>
{
    try {
       const {data}=await api.post(`/api/jobSeekers/apply`,applicationData);
       console.log("Response for apply : Remained Skills : ",data.remainedSkills," Remained Qual :",data.remainedQualifications)
       dispatch({type:MAKE_APPLICATION_SUCCESS,payload:data});
    } catch (error) {
        console.error("ERROR APPLY : ",error);
        dispatch({type:MAKE_APPLICATION_FAILURE,payload:error.message})
    }
}

export const showPostsAfterApply=(postId)=>async(dispatch)=>{
    try {       
        dispatch({type:SHOW_POSTS_AFTER_APPLY_SUCCESS,payload:postId});
    } catch (error) {
        console.log("Error show Posts After delete : ",error);
        dispatch({type:SHOW_POSTS_AFTER_APPLY_FAILURE,payload:error.message});
    }
}
export const deletePost=(postId)=>async(dispatch)=>
{
    try {
        const {data}=await api.delete(`/api/Post/companyDeletePost/${postId}`)
        console.log("Delete Post Response : ",data)
        dispatch({type:DELETE_POST_SUCCESS,payload:postId})
    } catch (error) {
        console.error("Error : ",error)
        dispatch({type:DELETE_POST_FAILURE,payload:error.message})
    }
}