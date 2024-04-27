
import { api } from '../../config/api'
import {GET_QUALIFICATION_FAILURE, GET_QUALIFICATION_SUCCESS} from './ActionType'
export const getQualifications=()=>async(dispatch)=>
{
    try {
        const {data}=await api.post(`/api/qualification/findAll`);
        dispatch({type:GET_QUALIFICATION_SUCCESS,payload:data})
    } catch (error) {
        console.log("Error : ",error)
        dispatch({type:GET_QUALIFICATION_FAILURE,payload:error.message})
    }
}