'use strict';

import {tasksAllListingEndpoint} from "../../lib/endpoints";

export function allTasksListing() {
  return tasksAllListingEndpoint
    .send({})
    .then(res => res.json())
    .then(res => res.success)
    .catch(err => console.log(err));
}