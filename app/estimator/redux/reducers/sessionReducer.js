'use strict';

import {SET_SESSION} from "../actions/sessionActions";

export const sessionInitialState = {session: {}}

const tasksReducer = (state = sessionInitialState, action) => {
  switch (action.type) {
    case SET_SESSION:
      return {...state, session: action.payload.session};
    default:
      return {...state};
  }
};

export default tasksReducer;