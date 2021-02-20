'use strict';

import {
  tasksAllListingEndpoint,
  tasksCreateEndpoint,
  tasksCreateSecondaryEndpoint,
  tesksDeleteEndpoint
} from "./endpoints";

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

export function createSecondary(data, cookies = '') {
  return tasksCreateSecondaryEndpoint
    .send(data, cookies)
    .then(res => res.json())
    .then(res => [res.success])
    .catch(_ => []);
}

export function deleteTask(data, cookies = '') {
  return tesksDeleteEndpoint
    .send(data, cookies)
    .then(res => res.json())
    .then(res => [res.success])
    .catch(_ => []);
}