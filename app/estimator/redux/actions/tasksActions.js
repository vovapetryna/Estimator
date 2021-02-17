'use strict';

export const SET_TASKS = 'SET_TASKS';
export const ADD_TASK = 'ADD_TASK';

export const setTasksA = (tasks) => ({
  type: SET_TASKS,
  payload: {tasks: tasks}
});

export const addTaskA = (task) => ({
  type: ADD_TASK,
  payload: {task: task}
})