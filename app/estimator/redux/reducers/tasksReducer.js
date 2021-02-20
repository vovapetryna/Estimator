'use strict';

import {
  ADD_SECONDARY_TASK,
  ADD_TASK,
  DELETE_PRIMARY_TASK,
  DELETE_SECONDARY_TASK,
  OPEN_CLOSE_TASK_DIALOG,
  SET_TASK_CONTEXT,
  SET_TASKS
} from "../actions/tasksActions";

export const tasksInitialState = {tasks: [], isOpenTaskDialog: false, taskContext: []}

const tasksReducer = (state = tasksInitialState, action) => {
  switch (action.type) {
    case SET_TASKS:
      return {...state, tasks: action.payload.tasks};
    case ADD_TASK:
      return {...state, tasks: state.tasks.concat(action.payload.task)}
    case OPEN_CLOSE_TASK_DIALOG:
      return {...state, isOpenTaskDialog: !state.isOpenTaskDialog}
    case SET_TASK_CONTEXT:
      return {...state, taskContext: action.payload.task}
    case ADD_SECONDARY_TASK:
      return {
        ...state, tasks: state.tasks.map((taskEntity) => {
          if (action.payload.taskContext.length === 0) return taskEntity
          if (taskEntity.task.id !== action.payload.taskContext[0].id) return taskEntity
          return {
            ...taskEntity,
            secondary: taskEntity.secondary.concat(action.payload.task)
          }
        })
      }
    case DELETE_PRIMARY_TASK: {
      const taskIndex = state.tasks.findIndex(e => e.task.id === action.payload.task.id)
      return {
        ...state, tasks: [...state.tasks.slice(0, taskIndex), ...state.tasks.slice(taskIndex + 1)]
      }
    }
    case DELETE_SECONDARY_TASK: {
      const primaryIndex = state.tasks.findIndex(e => e.secondary.findIndex(s => s.id === action.payload.task.id) >= 0)
      const secondaryIndex = state.tasks[primaryIndex].secondary.findIndex(s => s.id === action.payload.task.id)
      const updatedPrimary = {
        ...state.tasks[primaryIndex],
        secondary: [...state.tasks[primaryIndex].secondary.slice(0, secondaryIndex), ...state.tasks[primaryIndex].secondary.slice(secondaryIndex + 1)]
      }
      return {
        ...state, tasks: [...state.tasks.slice(0, primaryIndex), updatedPrimary, ...state.tasks.slice(primaryIndex + 1)]
      }
    }
    default:
      return {...state};
  }
};

export default tasksReducer;