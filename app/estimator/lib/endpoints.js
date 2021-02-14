'use strict';

function postRequst(uri, data) {
  return (fetch(uri, {
    method: "post",
    mode: "cors",
    headers: {'Content-Type': 'application/json'},
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

  async send(data) {
    if (!this.isApi) return new Promise(() => {});
    return postRequst(this.uri, data);
  }

  async sendBody(data) {
    if (!this.isApi) return new Promise(() => {});
    return this.send(data).then(res => res.json());
  }
}

export const root = "http://localhost:9001/api/v1";

export const loginEndpoint = new Endpoint(`${root}/login`, true);
export const logoutEndpoint = new Endpoint(`${root}/logout`, true);
export const registrationEndpoint = new Endpoint(`${root}/registration`, true);

export const tasksEndpoing = new Endpoint(`/tasks`, false);