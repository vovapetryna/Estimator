'use strict';

import React from "react";
import {Box, Fab, Grid} from "@material-ui/core";
import {AccountCircle, Add, ExitToApp} from "@material-ui/icons";
import styles from './Menu.module.css';
import {loginPageEndpoint, logoutEndpoint} from "../../lib/endpoints";
import {withRouter} from "next/router";
import {addTaskA, openCloseTaskDialogA} from "../../redux/actions/tasksActions";
import {connect} from "react-redux";

function Menu(props) {
  const logOutHandler = () => {
    logoutEndpoint
      .send({})
      .then(_ => {
        props.router.push(loginPageEndpoint.path());
      })
      .catch(err => {
        console.log(err);
      });
  }
  return (
    <Grid container justify='center'>
      <Box m={1}><Fab color='primary' aria-label='add' size={'medium'} onClick={props.openCloseTaskDialog}><Add/></Fab></Box>
      <Box m={1} alignItems={'center'} display={'flex'}>
        <Fab color='primary' aria-label='account' size={'medium'} variant={'extended'}>
          <AccountCircle
            className={styles.withRightMargin}/>{`${props.session.name} ${props.session.surname}`}
        </Fab>
      </Box>
      <Box m={1}>
        <Fab color='primary' aria-label='account' size={'medium'} onClick={logOutHandler}><ExitToApp/></Fab>
      </Box>
    </Grid>
  );
}

const mapStateToProps = state => ({
  session: state.session.session
});

const mapDispatchToProps = dispatch => {
  return ({
      openCloseTaskDialog: () => dispatch(openCloseTaskDialogA()),
    }
  );
};


export default connect(mapStateToProps, mapDispatchToProps)(withRouter(Menu));