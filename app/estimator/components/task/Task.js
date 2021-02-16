'use strict';

import React from "react";
import {Card, CardContent, List, Typography} from "@material-ui/core";
import Step from "../step/Step";

export default class Task extends React.Component {
  constructor(props) {
    super(props);
    this.task = props.taskEntity.task;
    this.steps = props.taskEntity.steps;
  }

  render() {
    return (
      <Card>
        <CardContent>
          <Typography variant="h5" component="h2">{this.task.name}</Typography>
          <Typography color="textSecondary">{this.task.description}</Typography>
          <List dense={true}>
            {this.steps.map(step => <Step key={step.id} step={step}/>)}
          </List>
        </CardContent>
      </Card>
    );
  }
}