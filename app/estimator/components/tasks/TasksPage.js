'use strict';

import React from 'react';
import Head from 'next/head';
import Layout, {siteTitle} from '../../components/layout';
import {connect} from 'react-redux';
import {Button} from "@material-ui/core";
import {namespaceConfig} from "fast-redux";
import {bindActionCreators} from "redux";
import {addTaskR, clearTaskR} from "./TasksActions";

const defaultState = {tasks: []}
const {action: actionCreator, getState: getTasksPageState} = namespaceConfig('tasks', defaultState);

const addTasks = actionCreator('addTask', addTaskR);
const clearTasks = actionCreator('clearTask', clearTaskR);

class TasksPage extends React.Component {
  constructor(props) {
    super(props);
    this.addTasks = props.addTasks;
    this.clearTasks = props.clearTasks;
  }

  render() {
    return (
      <Layout>
        <Head>
          <title>{siteTitle}</title>
        </Head>
        Tests
        <Button onClick={(_) => this.addTasks("task")}>Add Task</Button>
        <Button onClick={(_) => this.clearTasks("task")}>Clear Task</Button>
      </Layout>
    );
  }
}

function mapStateToProps(state) {
  return getTasksPageState(state);
}

function mapDispatchToProps(dispatch) {
  return bindActionCreators({addTasks, clearTasks}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(TasksPage);
