'use strict';

import React from 'react';
import {Box, Button, FormControl, IconButton, InputAdornment, InputLabel, OutlinedInput} from "@material-ui/core";
import {Visibility, VisibilityOff} from "@material-ui/icons";
import {loginEndpoint} from "../lib/endpoints";
import {withRouter} from "next/router";

class LoginForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {login: '', password: '', showPassword: false};
    this.handleChange = this.handleChange.bind(this);
    this.handleCheckChange = this.handleCheckChange.bind(this);
    this.handleLogin = this.handleLogin.bind(this);
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

  handleLogin() {
    loginEndpoint.send(this.state).then((res) => {
      this.props.router.push("/tasks");
    });
  }

  render() {
    return (
      <Box>
        <FormControl variant="outlined" fullWidth margin='normal'>
          <InputLabel htmlFor="loginSignIn">Login</InputLabel>
          <OutlinedInput id='loginSignIn' value={this.state.login} onChange={this.handleChange('login')}
                         labelWidth={50}/>
        </FormControl>
        <FormControl variant="outlined" fullWidth margin='normal'>
          <InputLabel htmlFor="passwordSignIn">Password</InputLabel>
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
          <Button variant="contained" color="primary" onClick={this.handleLogin}>Sing In</Button>
        </FormControl>
      </Box>
    );
  }
}

export default withRouter(LoginForm)