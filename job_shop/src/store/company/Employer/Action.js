import { api } from "../../../config/api"
import {GET_EMPLOYER_POSTS_SUCCESS, GET_EMPLOYER_POSTS_FAILURE, GET_EMPLOYER_FIELDS_SUCCESS, GET_EMPLOYER_FIELDS_FAILURE, GET_EMPLOYER_PROFILE_SUCCESS, GET_EMPLOYER_PROFILE_FAILURE } from "./ActionType"

export const getEmployerPosts=(empId)=>async(dispatch)=>
{
    try {
        const {data}=await api.get(`/api/employer/findAllPosts/${empId}`)
        console.log("Fetched Employer Posts : ",data)
        dispatch({type:GET_EMPLOYER_POSTS_SUCCESS,payload:data})
    } catch (error) {
        console.error("Error : ",error)
        dispatch({type:GET_EMPLOYER_POSTS_FAILURE,payload:error.message})
    }
}

export const getEmployerFields=(empId)=>async(dispatch)=>
{
    try {
        const {data}=await api.get(`/api/employer/findFields/${empId}`)
        console.log("Fetched Employer fields : ",data)
        dispatch({type:GET_EMPLOYER_FIELDS_SUCCESS,payload:data})
    } catch (error) {
        console.error("Error : ",error)
        dispatch({type:GET_EMPLOYER_FIELDS_FAILURE,payload:error.message})
    }
}

export const getEmployerProfile=(empId)=>async(dispatch)=>
{
    try {
        const {data}=await api.get(`/api/employerProfile/getInfo/${empId}`);
        console.log("Fetched Employer fields : ",data)
        dispatch({type:GET_EMPLOYER_PROFILE_SUCCESS,payload:data})
    } catch (error) {
        dispatch({type:GET_EMPLOYER_PROFILE_FAILURE,payload:error.message})
    }
}
    
