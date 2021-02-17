'use strict';

import cookie from 'cookie';

export const authError = 'Authorization failed';
export const regError = 'Registration failed';
export const regSuccess = 'Registration succeeded, please sign in';

export const taskFetchError = 'Error while task fetching';

export const sessionGetError = 'Error while trying to extract session';

export function parseCookieToSession(cookieRaw) {
  const sessionRaw = cookie.parse(cookieRaw)['_sessiondata'];
  const left = sessionRaw.indexOf("{");
  return JSON.parse(sessionRaw.slice(left));
}