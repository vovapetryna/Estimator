'use strict';

import {ADD_TASK, OPEN_CLOSE_TASK_DIALOG, SET_TASKS} from "../actions/tasksActions";

export const tasksInitialState = {tasks: [], isOpenTaskDialog: false}

const tasksReducer = (state = tasksInitialState, action) => {
  switch (action.type) {
    case SET_TASKS:
      return {...state, tasks: action.payload.tasks};
    case ADD_TASK:
      return {...state, tasks: state.tasks.concat(action.payload.task)}
    case OPEN_CLOSE_TASK_DIALOG:
      return {...state, isOpenTaskDialog: !state.isOpenTaskDialog}
    default:
      return {...state};
  }
};

export default tasksReducer;