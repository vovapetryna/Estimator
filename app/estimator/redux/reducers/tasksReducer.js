'use strict';

import {ADD_TASK, SET_TASKS} from "../actions/tasksActions";

export const tasksInitialState = {tasks: []}

const tasksReducer = (state = tasksInitialState, action) => {
  switch (action.type) {
    case SET_TASKS:
      return {...state, tasks: action.payload.tasks};
    case ADD_TASK:
      return {...state, tasks: state.tasks.concat(action.payload.task)}
    default:
      return {...state};
  }
};

export default tasksReducer;