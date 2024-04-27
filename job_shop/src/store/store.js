import { applyMiddleware, combineReducers,legacy_createStore } from "redux";
import { thunk } from "redux-thunk";
import { authReducer } from "./Auth/Reducer";
import { postReducer } from "./Post/Reducer";
import {companyReducer} from "./company/Reducer"
import {skillReducer} from "./skills/Reducer"
import { qualReducer } from "./qualifications/Reducer";

const rootReducers=combineReducers(
    {
      auth:authReducer,
      post:postReducer,
      comp:companyReducer,
      skills:skillReducer,
      quals:qualReducer,
    }
);

export const store=legacy_createStore(rootReducers,applyMiddleware(thunk));