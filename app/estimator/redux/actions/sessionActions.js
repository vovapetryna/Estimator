'use strict';

export const SET_SESSION = 'SET_SESSION';

export const setSessionA = (session) => ({
  type: SET_SESSION,
  payload: {session: session}
});
