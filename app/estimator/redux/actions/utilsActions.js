'use strict';

export const SET_ERROR = 'SET_ERROR';

export const setErrorA = (error) => ({
  type: SET_ERROR,
  payload: {error: error}
});