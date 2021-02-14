"use strict";

import "../styles/global.css";
import CssBaseline from '@material-ui/core/CssBaseline';
import React from 'react';
import PropTypes from 'prop-types';
import Head from 'next/head';

export default function App({Component, pageProps}) {
    React.useEffect(() => {
        const jssStyles = document.querySelector('#jss-server-side');
        if (jssStyles) {
            jssStyles.parentElement.removeChild(jssStyles);
        }
    }, []);

    return (
        <React.Fragment>
            <Head>
                <meta name="viewport" content="minimum-scale=1, initial-scale=1, width=device-width"/>
            </Head>
            <CssBaseline/>
            <Component {...pageProps} />
        </React.Fragment>
    );
}

App.propTypes = {
    Component: PropTypes.elementType.isRequired,
    pageProps: PropTypes.object.isRequired,
};