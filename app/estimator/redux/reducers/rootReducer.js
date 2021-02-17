'use strict';

import tasksReducer, {tasksInitialState} from './tasksReducer';
import {combineReducers} from 'redux';
import sessionReducer, {sessionInitialState} from "./sessionReducer";
import utilsReducer, {utilsInitialState} from "./utilsReducer";

export const rootInitialState = {
  tasks: tasksInitialState,
  session: sessionInitialState,
  utils: utilsInitialState
}

const rootReducer = combineReducers({
  tasks: tasksReducer,
  session: sessionReducer,
  utils: utilsReducer
})

export default rootReducer;