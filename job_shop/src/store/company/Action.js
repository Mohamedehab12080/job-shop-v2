import axios from "axios"
import { CREATE_EMPLOYER_FAILURE, CREATE_EMPLOYER_SUCCCESS, CREATE_FIELD_FAILURE, CREATE_FIELD_SUCCCESS, DELETE_EMPLOYER_FAILURE, DELETE_EMPLOYER_SUCCCESS, DELETE_FIELD_FAILURE, DELETE_FIELD_SUCCCESS, GET_COMPANY_INFO_FAILURE, GET_COMPANY_INFO_SUCCCESS, GET_EMPLOYERS_FAILURE, GET_EMPLOYERS_SUCCCESS, GET_FIELDS_FAILURE, GET_FIELDS_SUCCCESS, GIVE_EMPLOYER_FIELDS_FAILURE, GIVE_EMPLOYER_FIELDS_SUCCCESS } from "./ActionType"
import { API_BASE_URL, api } from "../../config/api"
import { type } from "@testing-library/user-event/dist/type"


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

export const giveEmployerFields=(dataInsertion)=>async(dispatch)=>
{
    try {
        const {data}=await api.post(`/api/company/giveEmployerFields`,dataInsertion )
        dispatch({type:GIVE_EMPLOYER_FIELDS_SUCCCESS,payload:data})
    } catch (error) {
        console.error("Error : ",error)
        dispatch({type:GIVE_EMPLOYER_FIELDS_FAILURE,payload:error.message})
    }
}

export const deleteEmployer=(employerId)=>async(dispatch)=>
{
    try {
        const {data} =await api.delete(`/api/company/deleteEmployerWithId/${employerId}`)
        dispatch({type:DELETE_EMPLOYER_SUCCCESS,payload:data})
    } catch (error) {
        console.error("Error : ",error)
        dispatch({type:DELETE_EMPLOYER_FAILURE,payload:error.message})
    }
}

export const getEmployers=(compId)=>async(dispatch)=>
{
    try {
        const {data}=await api.get(`/api/employer/findAll/${compId}`)
        console.log("Returned data : ",data)
        dispatch({type:GET_EMPLOYERS_SUCCCESS,payload:data})
    } catch (error) {
        console.error("error : ",error)
        dispatch({type:GET_EMPLOYERS_FAILURE,payload:error.message})
    }
}
export const getCompanyInfo=(compId)=>async(dispatch)=>
    {
        try {
            const {data}=await api.get(`/api/companyProfile/getInfo/${compId}`)
            console.log("Returned data for companyProfile : ",data)
            dispatch({type:GET_COMPANY_INFO_SUCCCESS,payload:data})
        } catch (error) {
            console.error("error : ",error)
            dispatch({type:GET_COMPANY_INFO_FAILURE,payload:error.message})
        }
    }
    
export const createField=(fieldData)=>async(dispatch)=>
{
    try {
        const {data}=await api.post(`/api/company/createField`,fieldData)
        console.log("Field creation successful:", data);
        dispatch({type:CREATE_FIELD_SUCCCESS,payload:data})
    } catch (error) {
        console.error("Error : ",error)
        dispatch({type:CREATE_FIELD_FAILURE,payload:error.message})
    }
}

export const getAllFields=(compId)=>async(dispatch)=>
{
    try {
        const {data}=await api.get(`/api/company/findComapnyFields/${compId}`)
        console.log("Data from back : ",data)
        dispatch({type:GET_FIELDS_SUCCCESS,payload:data})
    } catch (error) {
        console.error("Error : ",error)
        dispatch({type:GET_FIELDS_FAILURE,payload:error.message})
    }
}

export const deleteField=(fieldId)=>async(dispatch)=>
{
    try {
        const {data}=await api.delete(`/api/companyField/delete/${fieldId}`);
        console.log("Field Delete : ",data)
        dispatch({type:DELETE_FIELD_SUCCCESS,payload:fieldId});
    } catch (error) {
     console.log("Error delete Field : ",error);
     dispatch({type:DELETE_FIELD_FAILURE,payload:error.message});   
    }
}