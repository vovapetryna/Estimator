'use strict';

export const addTaskR = (state, newTasks) => {
  return {...state, tasks: state.tasks.concat(newTasks)};
};

export const clearTaskR = (state) => {
  return {...state, tasks: []};
}