'use strict';

import cookie from 'cookie';
import React from "react";

export const utilText = {
  sessionGetError: 'Error while trying to extract session',
  startMessage: 'Click Here to Start )',
}

export const taskText = {
  createDialogTitle: 'Create Task',
  createDialogDetails: '(Set task Estimate)',
  submitTaskCreation: 'Submit',
  newName: 'Name',
  newDescription: 'Description',

  fetchError: 'Error while task fetching',
}

export const authText = {
  login: 'Login',
  password: 'Password',
  signIn: 'Sign In',
  signUp: 'Sign Up',
  name: 'Name',
  surname: 'Surname',

  authError: 'Authorization failed',
  regError: 'Registration failed',
  regSuccess: 'Registration succeeded, please sign in'
}

export function parseCookieToSession(cookieRaw) {
  // const sessionRaw = cookie.parse(cookieRaw)['_sessiondata'];
  // const left = sessionRaw.indexOf("{");
  return {userId: "", login: "cors", name: "multi", surname: "domains"};
}