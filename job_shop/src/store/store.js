import { applyMiddleware, combineReducers,legacy_createStore } from "redux";
import { thunk } from "redux-thunk";
import { authReducer } from "./Auth/Reducer";
import { postReducer } from "./Post/Reducer";
import {companyReducer} from "./company/Reducer"
import {skillReducer} from "./skills/Reducer"
import { qualReducer } from "./qualifications/Reducer";
import { employerReducer } from "./company/Employer/Reducer";
import { jobSeekerReducer } from "./JobSeeker/Reducer";

const rootReducers=combineReducers(
    {
      auth:authReducer,
      post:postReducer,
      comp:companyReducer,
      skills:skillReducer,
      quals:qualReducer,
      emp:employerReducer,
      jobSeeker:jobSeekerReducer
    }
);

export const store=legacy_createStore(rootReducers,applyMiddleware(thunk));