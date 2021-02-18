'use strict';

import React, {useState} from 'react';
import {Box, Dialog, DialogTitle, FormControl, InputLabel, OutlinedInput, Typography} from '@material-ui/core';
import {connect} from 'react-redux';
import {taskText} from "../../lib/textConf";
import {addTaskA, openCloseTaskDialogA} from "../../redux/actions/tasksActions";
import {createTask} from "../../lib/fetchers";

function TaskDialog(props) {
  const [name, setName] = useState('')
  const [description, setDescription] = useState('')
  const handleClose = () => {
    createTask({name: name, description: description}).then(r => {
      props.openCloseTaskDialog();
      r.forEach(t => props.addTask(t));
    })
  }
  return (
    <Dialog open={props.isOpenTaskDialog} onClose={handleClose}>
      <Box m={1}>
        <Typography variant='h5' component='h2'>{taskText.createDialogTitle}</Typography>
        <FormControl variant='outlined' fullWidth margin='normal'>
          <InputLabel htmlFor='taskName'>{taskText.newName}</InputLabel>
          <OutlinedInput id='taskName' value={name} onChange={(e) => setName(e.target.value)} labelWidth={45}/>
        </FormControl>
        <FormControl variant='outlined' fullWidth margin='normal'>
          <InputLabel htmlFor='taskDescription'>{taskText.newDescription}</InputLabel>
          <OutlinedInput id='taskDescription' value={description}
                         onChange={(e) => setDescription(e.target.value)} labelWidth={80}/>
        </FormControl>
      </Box>
    </Dialog>
  );
}

const mapStateToProps = state => ({
  isOpenTaskDialog: state.tasks.isOpenTaskDialog
});

const mapDispatchToProps = dispatch => {
  return ({
      addTask: (task) => dispatch(addTaskA(task)),
      openCloseTaskDialog: () => dispatch(openCloseTaskDialogA()),
    }
  );
};


export default connect(mapStateToProps, mapDispatchToProps)(TaskDialog);