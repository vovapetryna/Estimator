'use strict';

import React from 'react';
import Head from 'next/head';
import Layout, {siteTitle} from '../components/layout/Layout';
import LoginForm from "../components/loginForm/LoginForm";
import {Accordion, AccordionDetails, AccordionSummary, Box, Fab, Grid, Typography} from "@material-ui/core";
import RegForm from "../components/regForm/RegForm";
import {CheckCircle, ExpandMore} from "@material-ui/icons";

export default class Home extends React.Component {
  constructor(props) {
    super(props);
    this.state = {expanded: 'signIn'};
    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(panel) {
    return ((event, _) => {
      this.setState({
        ...this.state,
        expanded: panel
      })
    });
  }

  render() {
    return (
      <Layout>
        <Head>
          <title>{siteTitle}</title>
        </Head>
        <Grid container justify='center'>
          <Box m={4}><Fab color={'primary'}><CheckCircle/></Fab></Box>
        </Grid>
        <Box m={1} height={'100%'}>
          <Accordion expanded={this.state.expanded === 'signIn'} onChange={this.handleChange('signIn')}>
            <AccordionSummary expandIcon={<ExpandMore/>}><Typography>Sign In</Typography></AccordionSummary>
            <AccordionDetails><LoginForm/></AccordionDetails>
          </Accordion>
          <Accordion expanded={this.state.expanded === 'signUp'} onChange={this.handleChange('signUp')}>
            <AccordionSummary expandIcon={<ExpandMore/>}><Typography>Sign Up</Typography></AccordionSummary>
            <AccordionDetails><RegForm/></AccordionDetails>
          </Accordion>
        </Box>
      </Layout>
    );
  }
}