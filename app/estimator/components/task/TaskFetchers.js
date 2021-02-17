'use strict';

import {tasksAllListingEndpoint} from "../../lib/endpoints";

export function allTasksListing(cookies = '') {
  return tasksAllListingEndpoint
    .send({}, cookies)
    .then(res => res.json())
    .then(res => res.success)
    .catch(err => console.log(err));
}