'use strict';

import React from "react";
import {IconButton, ListItem, ListItemAvatar, ListItemSecondaryAction, ListItemText} from "@material-ui/core";
import {Delete, PlayArrow} from "@material-ui/icons";

export default function Step(props) {
  return (
    <ListItem>
      <ListItemAvatar><IconButton><PlayArrow/></IconButton></ListItemAvatar>
      <ListItemText
        primary={props.step.name}
        secondary={props.step.description}
      />
      <ListItemSecondaryAction>
        <IconButton edge="end" aria-label="delete">
          <Delete/>
        </IconButton>
      </ListItemSecondaryAction>
    </ListItem>
  );
}