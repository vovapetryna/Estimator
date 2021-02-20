'use strict';

import React, {useState} from 'react';
import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  FormControl,
  InputLabel,
  OutlinedInput,
  Slider
} from '@material-ui/core';
import {connect} from 'react-redux';
import {taskText} from "../../lib/textConf";
import {addSecondaryTaskA, addTaskA, openCloseTaskDialogA, setTaskContextA} from "../../redux/actions/tasksActions";
import * as moment from "moment";
import {createSecondary, createTask} from "../../lib/fetchers";

function TaskDialog(props) {
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [estimate, setEstimate] = useState(moment.duration(2, 'hours').toISOString());
  const handleClose = () => {
    props.openCloseTaskDialog();
    if (props.taskContext.length === 0) {
      createTask({name: name, description: description, estimate: estimate, primaryTaskId: []}).then(r => {
        r.forEach(t => props.addTask({task: t, secondary: []}));
      }).finally(props.setTaskContext([]));
    } else {
      createSecondary({
        name: name,
        description: description,
        estimate: estimate,
        primaryTaskId: props.taskContext.map(t => t.id)
      }).then(r => {
        r.forEach(t => props.addSecondary(t, props.taskContext));
      }).finally(props.setTaskContext([]));
    }
  }

  const toDuration = (number) => moment.duration(number, 'minutes');
  const toDurationLabel = (number) => toDuration(number).toISOString().replace('P', '').replace('T', '').toLowerCase();
  const toDurationMinutesLabel = (number) => `${toDuration(number).minutes()}m`

  return (
    <Dialog open={props.isOpenTaskDialog} onClose={handleClose}>
      <DialogTitle id="form-dialog-title">{taskText.createDialogTitle}</DialogTitle>
      <DialogContent>
        <DialogContentText>{taskText.createDialogDetails}</DialogContentText>
        <FormControl variant='outlined' fullWidth margin='normal'>
          <InputLabel htmlFor='taskName'>{taskText.newName}</InputLabel>
          <OutlinedInput id='taskName' value={name} onChange={(e) => setName(e.target.value)} labelWidth={45}/>
        </FormControl>
        <FormControl variant='outlined' fullWidth margin='normal'>
          <InputLabel htmlFor='taskDescription'>{taskText.newDescription}</InputLabel>
          <OutlinedInput id='taskDescription' value={description}
                         onChange={(e) => setDescription(e.target.value)} labelWidth={80}/>
        </FormControl>
        <Slider defaultValue={120} min={5} max={480} step={5}
                valueLabelDisplay={'auto'}
                marks={[...Array(8).keys()].map(i => {
                  const number = (i + 1) * 60
                  return {value: number, label: toDurationLabel(number)}
                })}
                valueLabelFormat={toDurationMinutesLabel}
                getAriaValueText={toDurationLabel}
                onChangeCommitted={(_, v) => setEstimate(toDuration(v).toISOString())}
        />
      </DialogContent>
      <DialogActions><Button onClick={handleClose}
                             color="primary">{taskText.submitTaskCreation}</Button></DialogActions>
    </Dialog>
  );
}

const mapStateToProps = state => ({
  isOpenTaskDialog: state.tasks.isOpenTaskDialog,
  taskContext: state.tasks.taskContext,
});

const mapDispatchToProps = dispatch => {
  return ({
      addTask: (task) => dispatch(addTaskA(task)),
      openCloseTaskDialog: () => dispatch(openCloseTaskDialogA()),
      setTaskContext: (task) => dispatch(setTaskContextA(task)),
      addSecondary: (task, taskContext) => dispatch(addSecondaryTaskA(task, taskContext)),
    }
  );
};


export default connect(mapStateToProps, mapDispatchToProps)(TaskDialog);