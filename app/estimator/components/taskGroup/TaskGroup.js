'use strict';

import React from 'react';
import Task from "../task/Task";
import {Box} from "@material-ui/core";

function TaskGroup(props) {
  return (
    <div>
      <Box mb={2}>
        <Task key={props.taskEntity.task.id} task={props.taskEntity.task}/>
        {props.taskEntity.secondary.map(t => {
          return <Box key={t.id} mb={0} mt={0.5} ml={2} mr={0}>
            <Task task={t}/>
          </Box>
        })}
      </Box>
    </div>
  );
}

export default TaskGroup;