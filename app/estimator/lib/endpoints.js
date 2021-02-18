'use strict';

function postRequest(uri, data, cookies = '') {
  return (fetch(uri, {
    method: "post",
    mode: "cors",
    headers: {
      'Content-Type': 'application/json',
      'Cookie': cookies
    },
    credentials: 'include',
    body: JSON.stringify(data)
  }));
}

class Endpoint {
  constructor(uri, isApi) {
    this.uri = uri;
    this.isApi = isApi;
  }

  path() {
    return this.uri
  }

  async send(data, cookies = '') {
    return postRequest(this.uri, data, cookies);
  }
}

export const root = "http://localhost:9001/api/v1";

export const loginPageEndpoint = new Endpoint('/', false);
export const loginEndpoint = new Endpoint(`${root}/login`, true);
export const logoutEndpoint = new Endpoint(`${root}/logout`, true);
export const registrationEndpoint = new Endpoint(`${root}/registration`, true);

export const tasksPageEndpoint = new Endpoint(`/tasks`, false);
export const tasksAllListingEndpoint = new Endpoint(`${root}/tasks/listing`, true);
export const tasksCreateEndpoint = new Endpoint(`${root}/tasks/create`, true);