'use strict';

import {SET_ERROR} from "../actions/utilsActions";

export const utilsInitialState = {error: ''}

const tasksReducer = (state = utilsInitialState, action) => {
  switch (action.type) {
    case SET_ERROR:
      return {...state, error: action.payload.error};
    default:
      return {...state};
  }
};

export default tasksReducer;