'use strict';

import React from 'react';
import {Grid} from '@material-ui/core';
import styles from './IntroText.module.css';
import {utilText} from "../../lib/textConf";

export default function IntroText(props) {
  return (
    <Grid container justify='center' alignItems={'center'}>
      <div className={styles.arrowBox}>
        <img src={'/images/arrow.svg'} alt={'ArrowRight'} className={styles.arrowBox}/>
      </div>
      <div className={styles.introText}>
        {utilText.startMessage}
      </div>
    </Grid>
  );
}
