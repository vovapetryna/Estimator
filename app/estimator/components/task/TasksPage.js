'use strict';

import React from 'react';
import Head from 'next/head';
import Layout, {siteTitle} from '../layout/Layout';
import {connect} from 'react-redux';
import {namespaceConfig} from 'fast-redux';
import {bindActionCreators} from 'redux';
import {addTaskR, clearTaskR, setTasksR} from './TasksActions';
import {allTasksListing} from './TaskFetchers';
import Task from './Task';
import Menu from "../menu/Menu";

const defaultState = {tasks: []}
const {action: actionCreator, getState: getTasksPageState} = namespaceConfig('tasks', defaultState);

const addTasks = actionCreator('addTask', addTaskR);
const clearTasks = actionCreator('clearTask', clearTaskR);
const setTasks = actionCreator('setTasks', setTasksR);

class TasksPage extends React.Component {
  constructor(props) {
    super(props);
    this.setTasks = props.setTasks;
    this.addTasks = props.addTasks;
    this.clearTasks = props.clearTasks;
    this.props = props;

    allTasksListing().then(data => {
      this.setTasks(data)
    });
  }

  render() {
    return (
      <Layout>
        <Head>
          <title>{siteTitle}</title>
        </Head>
        <Menu/>
        {this.props.tasks.map(taskEntity => (
          <Task key={taskEntity.task.id} taskEntity={taskEntity}/>
        ))}
      </Layout>
    );
  }
}

function mapStateToProps(state) {
  return getTasksPageState(state);
}

function mapDispatchToProps(dispatch) {
  return bindActionCreators({addTasks, clearTasks, setTasks}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(TasksPage);
