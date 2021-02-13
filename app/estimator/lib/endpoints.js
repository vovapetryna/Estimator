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
  constructor(uri) {
    this.uri = uri;
  }
  async sendAsync(data) {
    return await postRequst(this.uri, data);
  }
  async send(data) {
    const res = await this.sendAsync(data);
    return (await res.json());
  }
}

export const root = "http://localhost:9001/api/v1";

export const loginEndpoint = new Endpoint(`${root}/login`);
export const logoutEndpoint = new Endpoint(`${root}/logout`);
export const registrationEndpoint = new Endpoint(`${root}/registration`);