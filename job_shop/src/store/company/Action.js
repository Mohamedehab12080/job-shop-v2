import axios from "axios"
import { CREATE_EMPLOYER_FAILURE, CREATE_EMPLOYER_SUCCCESS, CREATE_FIELD_FAILURE, CREATE_FIELD_SUCCCESS, GET_FIELDS_FAILURE, GET_FIELDS_SUCCCESS } from "./ActionType"
import { API_BASE_URL, api } from "../../config/api"


export const createEmployer=(employerData)=>async(dispatch)=>
{
    try {
        const {data}=await api.post(`/api/company/createEmployer`,employerData)
        dispatch({type:CREATE_EMPLOYER_SUCCCESS,payload:data})
    } catch (error) {
        console.error("Error : ",error)
        dispatch({type:CREATE_EMPLOYER_FAILURE,payload:error.message})
    }
}

export const createField=(fieldData)=>async(dispatch)=>
{
    try {
        const {data}=await api.post(`/api/company/createField`,fieldData)
        dispatch({type:CREATE_FIELD_SUCCCESS,payload:data})
    } catch (error) {
        console.error("Error : ",error)
        dispatch({type:CREATE_FIELD_FAILURE,payload:error.message})
    }
}

export const getAllFields=(compId)=>async(dispatch)=>
{
    try {
        const {data}=await api.post(`/api/company/findComapnyFields/${compId}`)
        dispatch({type:GET_FIELDS_SUCCCESS,payload:data})
    } catch (error) {
        console.error("Error : ",error)
        dispatch({type:GET_FIELDS_FAILURE,payload:error.message})
    }
}