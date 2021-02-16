'use strict';

import React from "react";
import {IconButton, ListItem, ListItemAvatar, ListItemSecondaryAction, ListItemText} from "@material-ui/core";
import {Delete, PlayArrow} from "@material-ui/icons";

export default class Step extends React.Component {
  constructor(props) {
    super(props);
    this.step = props.step;
  }

  render() {
    return (
      <ListItem>
        <ListItemAvatar><IconButton><PlayArrow/></IconButton></ListItemAvatar>
        <ListItemText
          primary={this.step.name}
          secondary={this.step.description}
        />
        <ListItemSecondaryAction>
          <IconButton edge="end" aria-label="delete">
            <Delete/>
          </IconButton>
        </ListItemSecondaryAction>
      </ListItem>
    );
  }
}