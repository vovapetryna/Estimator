'use strict';

import {tasksAllListingEndpoint, tasksCreateEndpoint} from "./endpoints";

export function allTasksListing(cookies = '') {
  return tasksAllListingEndpoint
    .send({}, cookies)
    .then(res => res.json())
    .then(res => res.success)
    .catch(err => console.log(err));
}

export function createTask(data, cookies = '') {
  return tasksCreateEndpoint
    .send(data, cookies)
    .then(res => res.json())
    .then(res => [res.success])
    .catch(_ => []);
}