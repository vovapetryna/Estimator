'use strict';

export const SET_TASKS = 'SET_TASKS';
export const ADD_TASK = 'ADD_TASK';
export const OPEN_CLOSE_TASK_DIALOG = 'OPEN_CLOSE_TASK_DIALOG';
export const SET_TASK_CONTEXT = 'TASK_CONTEXT';
export const ADD_SECONDARY_TASK = 'ADD_SECONDARY_TASK';
export const DELETE_PRIMARY_TASK = 'DELETE_PRIMARY_TASK';
export const DELETE_SECONDARY_TASK = 'DELETE_SECONDARY_TASK';

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

export const setTaskContextA = (task) => ({
  type: SET_TASK_CONTEXT,
  payload: {task: task}
})

export const addSecondaryTaskA = (task, taskContext) => ({
  type: ADD_SECONDARY_TASK,
  payload: {task: task, taskContext: taskContext}
})

export const deletePrimaryTaskA = (task) => ({
  type: DELETE_PRIMARY_TASK,
  payload: {task: task}
})

export const deleteSecondaryTaskA = (task) => ({
  type: DELETE_SECONDARY_TASK,
  payload: {task: task}
})