'use strict';

function postRequest(uri, data) {
  return (fetch(uri, {
    method: "post",
    mode: "cors",
    headers: {'Content-Type': 'application/json'},
    credentials: 'same-origin',
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

  async send(data) {
    return postRequest(this.uri, data);
  }
}

export const root = "/api";

export const loginPageEndpoint = new Endpoint('/', false);
export const loginEndpoint = new Endpoint(`${root}/login`, true);
export const logoutEndpoint = new Endpoint(`${root}/logout`, true);
export const registrationEndpoint = new Endpoint(`${root}/registration`, true);

export const tasksPageEndpoint = new Endpoint(`/tasks`, false);
export const tasksAllListingEndpoint = new Endpoint(`${root}/tasks/listing`, true);