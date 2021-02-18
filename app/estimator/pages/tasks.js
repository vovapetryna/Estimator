'use strict';

import React from 'react';
import Head from 'next/head';
import Layout, {siteTitle} from '../components/layout/Layout';
import {connect} from 'react-redux';
import {setTasksA} from "../redux/actions/tasksActions";
import Menu from "../components/menu/Menu";
import Task from "../components/task/Task";
import {setSessionA} from "../redux/actions/sessionActions";
import {setErrorA} from "../redux/actions/utilsActions";
import {initializeStore} from "../redux/store";
import {parseCookieToSession} from "../lib/textConf";
import TaskDialog from "../components/taskDialog/taskDialog";
import {allTasksListing} from "../lib/fetchers";

function TasksPage(props) {
  return (
    <Layout>
      <Head>
        <title>{siteTitle}</title>
      </Head>
      <Menu/>
      <TaskDialog/>
      {props.tasks.map(taskEntity => (
        <Task key={taskEntity.task.id} taskEntity={taskEntity}/>
      ))}

    </Layout>
  );
}

export async function getServerSideProps({req}) {
  const reduxStore = initializeStore();
  const {dispatch} = reduxStore;
  const tasks = await allTasksListing(req.headers.cookie);
  if (!tasks) {
    return {
      notFound: true,
    }
  }
  dispatch(setTasksA(tasks));
  dispatch(setSessionA(parseCookieToSession(req.headers.cookie)));
  return {props: {initStore: reduxStore.getState()}};
}

const mapStateToProps = state => ({
  tasks: state.tasks.tasks
});

const mapDispatchToProps = dispatch => {
  return ({
      setTasks: (tasks) => dispatch(setTasksA(tasks)),
      setSession: (session) => dispatch(setSessionA(session)),
      setError: (error) => dispatch(setErrorA(error))
    }
  );
};

export default connect(mapStateToProps, mapDispatchToProps)(TasksPage);
