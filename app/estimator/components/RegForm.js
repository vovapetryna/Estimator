'use strict';

import React from 'react';
import {Box, Button, FormControl, IconButton, InputAdornment, InputLabel, OutlinedInput} from "@material-ui/core";
import {Visibility, VisibilityOff} from "@material-ui/icons";
import {loginEndpoint, registrationEndpoint} from "../lib/endpoints";

export default class RegForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {name: '', surname: '', login: '', password: '', showPassword: false};
    this.handleChange = this.handleChange.bind(this);
    this.handleCheckChange = this.handleCheckChange.bind(this);
    this.handleReg = this.handleReg.bind(this);
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

  handleReg() {
    registrationEndpoint.send(this.state)
  }

  render() {
    return (
      <Box>
        <FormControl variant="outlined" fullWidth margin='normal'>
          <InputLabel htmlFor="nameSignUp">Name</InputLabel>
          <OutlinedInput id='nameSignUp' value={this.state.name} onChange={this.handleChange('name')} labelWidth={40}/>
        </FormControl>
        <FormControl variant="outlined" fullWidth margin='normal'>
          <InputLabel htmlFor="surnameSignUp">Surname</InputLabel>
          <OutlinedInput id='surnameSignUp' value={this.state.surname} onChange={this.handleChange('surname')} labelWidth={80}/>
        </FormControl>
        <FormControl variant="outlined" fullWidth margin='normal'>
          <InputLabel htmlFor="loginSignUp">Login</InputLabel>
          <OutlinedInput id='loginSignUp' value={this.state.login} onChange={this.handleChange('login')} labelWidth={50}/>
        </FormControl>
        <FormControl variant="outlined" fullWidth margin='normal'>
          <InputLabel htmlFor="passwordSignUp">Password</InputLabel>
          <OutlinedInput
            id='passwordSignUp'
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
          <Button variant="contained" color="primary" onClick={this.handleReg}>Sign Up</Button>
        </FormControl>
      </Box>
    );
  }
}