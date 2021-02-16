'use strict';

import cookieCutter from 'cookie-cutter';

export const authError = 'Authorization failed';
export const regError = 'Registration failed';

export const regSuccess = 'Registration succeeded, please sign in';

function getSession() {
  const sessionStr = cookieCutter.get('_sessiondata');
  const left = sessionStr.indexOf("{");
  return JSON.parse(sessionStr.slice(left));
}

export function userName() {
  return getSession().name;
}

export function userSurname() {
  return getSession().surname;
}

export function userLogin() {
  return getSession().login;
}

export function userId() {
  return getSession().userId;
}
