'use strict';

import React from 'react';
import {
  Box,
  Button,
  FormControl,
  IconButton,
  InputAdornment,
  InputLabel,
  OutlinedInput,
  Snackbar
} from "@material-ui/core";
import {Close, Visibility, VisibilityOff} from '@material-ui/icons';
import {loginEndpoint, tasksPageEndpoint} from '../../lib/endpoints';
import {withRouter} from 'next/router';
import {authText} from '../../lib/textConf';

class LoginForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {login: '', password: '', showPassword: false, authMessage: ''};
    this.handleChange = this.handleChange.bind(this);
    this.handleCheckChange = this.handleCheckChange.bind(this);
    this.handleLogin = this.handleLogin.bind(this);
    this.handleSnackBarClose = this.handleSnackBarClose.bind(this);
  }

  handleChange(param) {
    return ((event) => {
      this.setState({[param]: event.target.value});
    });
  }

  handleCheckChange(event) {
    event.preventDefault();
    this.setState({...this.state, showPassword: !this.state.showPassword});
  }

  handleSnackBarClose(event, reason) {
    if (reason === 'clickaway') {
      return;
    }
    this.setState({...this.state, authMessage: ''});
  }

  handleLogin() {
    loginEndpoint
      .send(this.state)
      .then(res => {
        if (!res.ok) {
          this.setState({...this.state, authMessage: authText.authError})
        }
        this.props.router.push(tasksPageEndpoint.path());
      })
      .catch(err => {
        this.setState({...this.state, authMessage: authText.authError});
        console.log(err);
      });
  }

  render() {
    return (
      <Box>
        <FormControl variant='outlined' fullWidth margin='normal'>
          <InputLabel htmlFor='loginSignIn'>{authText.login}</InputLabel>
          <OutlinedInput id='loginSignIn' value={this.state.login} onChange={this.handleChange('login')}
                         labelWidth={50}/>
        </FormControl>
        <FormControl variant='outlined' fullWidth margin='normal'>
          <InputLabel htmlFor='passwordSignIn'>{authText.password}</InputLabel>
          <OutlinedInput
            id='passwordSignIn'
            type={this.state.showPassword ? 'text' : 'password'}
            value={this.state.password}
            onChange={this.handleChange('password')}
            endAdornment={
              <InputAdornment position='end'>
                <IconButton aria-label='toggle password visibility' onClick={this.handleCheckChange} edge='end'>
                  {this.state.showPassword ? <Visibility/> : <VisibilityOff/>}
                </IconButton>
              </InputAdornment>
            }
            labelWidth={80}
          />
        </FormControl>
        <FormControl fullWidth margin='normal'>
          <Button variant='contained' color='primary' onClick={this.handleLogin}>{authText.signIn}</Button>
        </FormControl>
        <Snackbar anchorOrigin={{vertical: 'bottom', horizontal: 'center'}}
                  open={this.state.authMessage !== ''}
                  autoHideDuration={6000}
                  onClose={this.handleSnackBarClose}
                  message={this.state.authMessage}
                  action={
                    <React.Fragment>
                      <IconButton size='small' aria-label='close' color='inherit' onClick={this.handleSnackBarClose}>
                        <Close fontSize='small'/>
                      </IconButton>
                    </React.Fragment>
                  }/>
      </Box>
    );
  }
}

export default withRouter(LoginForm)