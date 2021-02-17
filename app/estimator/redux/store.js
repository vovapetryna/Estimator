'use strict';

import {applyMiddleware, createStore} from 'redux';
import rootReducer, {rootInitialState} from "./reducers/rootReducer";
import {useMemo} from "react";
import {composeWithDevTools} from "redux-devtools-extension";

let store

const makeStore = (initialState = rootInitialState) => {
  return createStore(rootReducer, initialState, composeWithDevTools(applyMiddleware()));
};

export const initializeStore = (preloadedState) => {
  let _store = store ?? makeStore(preloadedState)
  if (preloadedState && store) {
    _store = makeStore({
      ...store.getState(),
      ...preloadedState,
    })
    store = undefined
  }
  if (typeof window === 'undefined') return _store
  if (!store) store = _store
  return _store
}

export function useStore(initialState) {
  return useMemo(() => initializeStore(initialState), [initialState])
}