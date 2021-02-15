'use strict';

import React from "react";
import {Card, CardContent, CardHeader, Typography} from "@material-ui/core";

export default class Task extends React.Component {
  constructor(props) {
    super(props);
    this.taskName = props.name;
    this.taskDesc = props.desc;
  }

  render() {
    return (
      <Card>
        <CardHeader title={this.taskName}/>
        <CardContent><Typography component='p'>{this.taskDesc}</Typography></CardContent>
      </Card>
    );
  }
}