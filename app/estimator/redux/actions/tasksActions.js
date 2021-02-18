'use strict';

export const SET_TASKS = 'SET_TASKS';
export const ADD_TASK = 'ADD_TASK';
export const OPEN_CLOSE_TASK_DIALOG = 'OPEN_CLOSE_TASK_DIALOG';

export const setTasksA = (tasks) => ({
  type: SET_TASKS,
  payload: {tasks: tasks}
});

export const addTaskA = (task) => ({
  type: ADD_TASK,
  payload: {task: task}
})

export const openCloseTaskDialogA = () => ({
  type: OPEN_CLOSE_TASK_DIALOG,
  payload: {}
})