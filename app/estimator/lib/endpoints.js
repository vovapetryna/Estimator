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

export const root = "http://a125130cd0288462ab97ea347c4339c8-2072835968.eu-west-3.elb.amazonaws.com/api/v1";

export const loginPageEndpoint = new Endpoint('/', false);
export const loginEndpoint = new Endpoint(`${root}/login`, true);
export const logoutEndpoint = new Endpoint(`${root}/logout`, true);
export const registrationEndpoint = new Endpoint(`${root}/registration`, true);

export const tasksPageEndpoint = new Endpoint(`/tasks`, false);
export const tasksAllListingEndpoint = new Endpoint(`${root}/tasks/listing`, true);
export const tasksCreateEndpoint = new Endpoint(`${root}/tasks/create`, true);
export const tasksCreateSecondaryEndpoint = new Endpoint(`${root}/tasks/addSecondary`, true);
export const tesksDeleteEndpoint = new Endpoint(`${root}/tasks/delete`, true);