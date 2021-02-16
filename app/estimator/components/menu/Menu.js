'use strict';

import React from "react";
import {Box, Fab, Grid} from "@material-ui/core";
import {AccountCircle, Add, ExitToApp} from "@material-ui/icons";
import styles from './Menu.module.css';
import {userName, userSurname} from "../../lib/textConf";
import {loginPageEndpoint, logoutEndpoint} from "../../lib/endpoints";
import {withRouter} from "next/router";

class Menu extends React.Component {
  constructor(props) {
    super(props);
    this.logOutHandler = this.logOutHandler.bind(this);
  }

  logOutHandler() {
    logoutEndpoint
      .send({})
      .then(_ => {
        this.props.router.push(loginPageEndpoint.path());
      })
      .catch(err => {
        console.log(err);
      });
  }

  render() {
    return (
      <Grid container justify='center'>
        <Box m={1}><Fab color='primary' aria-label='add' size={'medium'}><Add/></Fab></Box>
        <Box m={1} alignItems={'center'} display={'flex'}>
          <Fab color='primary' aria-label='account' size={'medium'} variant={'extended'}>
            <AccountCircle className={styles.withRightMargin}/>{`${userName()} ${userSurname()}`}
          </Fab>
        </Box>
        <Box m={1}>
          <Fab color='primary' aria-label='account' size={'medium'} onClick={this.logOutHandler}><ExitToApp/></Fab>
        </Box>
      </Grid>
    );
  }
}

export default withRouter(Menu)