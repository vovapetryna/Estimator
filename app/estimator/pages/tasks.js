'use strict';

import React from 'react';
import Head from 'next/head';
import Layout, {siteTitle} from '../components/layout/Layout';
import {connect} from 'react-redux';
import {setTasksA} from "../redux/actions/tasksActions";
import Menu from "../components/menu/Menu";
import {setSessionA} from "../redux/actions/sessionActions";
import {setErrorA} from "../redux/actions/utilsActions";
import {initializeStore} from "../redux/store";
import {parseCookieToSession} from "../lib/textConf";
import TaskDialog from "../components/taskDialog/TaskDialog";
import {allTasksListing} from "../lib/fetchers";
import TaskGroup from "../components/taskGroup/TaskGroup";
import IntroText from "../components/IntroText/IntroText";

function TasksPage(props) {
  return (
    <Layout>
      <Head>
        <title>{siteTitle}</title>
      </Head>
      <Menu/>
      <TaskDialog/>
      {(props.tasks.length === 0) ? <IntroText/> :
        props.tasks.map(taskEntity => (
          <TaskGroup key={taskEntity.task.id} taskEntity={taskEntity}/>
        ))}
    </Layout>
  );
}

export async function getServerSideProps({req}) {
  const reduxStore = initializeStore();
  const {dispatch} = reduxStore;
  const tasks = await allTasksListing("");
  if (!tasks) {
    return {
      notFound: true,
    }
  }
  dispatch(setTasksA(tasks));
  dispatch(setSessionA({userId: "", login: "cors", name: "multi", surname: "domains"}));
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
