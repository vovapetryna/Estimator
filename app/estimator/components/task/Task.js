'use strict';

import React from 'react';
import {Box, Card, CardContent, List, Typography} from '@material-ui/core';
import Step from '../step/Step';

export default function Task(props) {
  return (
    <Box mb={1}>
      <Card>
        <CardContent>
          <Typography variant='h5' component='h2'>{props.taskEntity.task.name}</Typography>
          <Typography color='textSecondary'>{props.taskEntity.task.description}</Typography>
          <List dense={true}>
            {props.taskEntity.steps.map(step => <Step key={step.id} step={step}/>)}
          </List>
        </CardContent>
      </Card>
    </Box>
  );
}