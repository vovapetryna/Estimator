'use strict';

import {applyMiddleware, createStore} from 'redux';
import {rootReducer} from 'fast-redux';
import {composeWithDevTools} from 'redux-devtools-extension';
import thunkMiddleware from 'redux-thunk'
import {createWrapper} from 'next-redux-wrapper';

export const initStore = (initialState = {}) => {
  return createStore(
    rootReducer,
    initialState,
    composeWithDevTools(applyMiddleware(thunkMiddleware))
  )
};

export const reduxPage = (comp) => createWrapper(initStore).withRedux(comp);