'use strict';

import React, {useState} from 'react';
import {Box, Card, Collapse, Icon, IconButton, Toolbar, Tooltip, Typography} from '@material-ui/core';
import {Add, Delete, ExpandLess, ExpandMore, PlayArrow} from "@material-ui/icons";
import styles from './Task.module.css';
import PieChart from "../charts/PieChart";
import BarChart from "../charts/BarChart";
import {
  deletePrimaryTaskA,
  deleteSecondaryTaskA,
  openCloseTaskDialogA,
  setTaskContextA
} from "../../redux/actions/tasksActions";
import {connect} from "react-redux";
import {deleteTask} from "../../lib/fetchers";

function Task(props) {
  const [isCollapsed, setCollapsed] = useState(!props.task.isPrimary);

  const handleAddSecondary = () => {
    props.openCloseTaskDialog();
    props.setTaskContext([props.task]);
  }

  const handleDelete = () => {
    deleteTask(props.task).then(r => {
      r.forEach(b => {
        if (b && props.task.isPrimary) props.deletePrimary(props.task)
        if (b && !props.task.isPrimary) props.deleteSecondary(props.task)
      })
    })
  }

  return (
    <Card className={styles.root}>
      <div className={styles.details}>
        <Box p={1} pb={0}>
          <Typography component="h5" variant="h5">{props.task.name}</Typography>
          <Collapse in={!isCollapsed} timeout={'auto'}>
            <Typography variant="subtitle1" color="textSecondary">{props.task.description}</Typography>
          </Collapse>
        </Box>
        <Box p={1} pt={0}>
          <Tooltip title={'Begin work'} aria-label={'tooltip-for-play'}>
            <IconButton aria-label={'startTask'} size={'small'}><PlayArrow/></IconButton>
          </Tooltip>
          {(props.task.isPrimary) ?
            <Tooltip title={'Add related task'} aria-label={'tooltip-for-add'}>
              <IconButton aria-label={'addSecondaryTask'} size={'small'} onClick={handleAddSecondary}><Add/></IconButton>
            </Tooltip> : <></>}
          <Tooltip title={`Show ${isCollapsed ? 'more' : 'less'}`} aria-label={'tooltip-for-colapse'}>
            <IconButton aria-label={'collapseTask'} size={'small'} onClick={() => setCollapsed(!isCollapsed)}>
              {isCollapsed ? <ExpandMore/> : <ExpandLess/>}
            </IconButton>
          </Tooltip>
          <Tooltip title={'Delete Task'} aria-label={'tooltip-for-delete'}>
            <IconButton aria-label={'deleteTask'} size={'small'} onClick={handleDelete}><Delete/></IconButton>
          </Tooltip>
        </Box>
      </div>
      <div className={styles.cover}>
        <Collapse in={!isCollapsed} timeout={'auto'}><PieChart/></Collapse>
        <Collapse in={isCollapsed} timeout={'auto'}><BarChart/></Collapse>
      </div>
    </Card>
  );
}

const mapStateToProps = _ => ({});

const mapDispatchToProps = dispatch => {
  return ({
      openCloseTaskDialog: () => dispatch(openCloseTaskDialogA()),
      setTaskContext: (task) => dispatch(setTaskContextA(task)),
      deletePrimary: (task) => dispatch(deletePrimaryTaskA(task)),
      deleteSecondary: (task) => dispatch(deleteSecondaryTaskA(task)),
    }
  );
};


export default connect(mapStateToProps, mapDispatchToProps)(Task);