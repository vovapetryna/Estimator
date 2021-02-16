'use strict';

import styles from './Layout.module.css';
import Head from 'next/head';

export const siteTitle = 'Estimator';
const description = 'Estimator description';

export default function Layout({children}) {
    return (<div className={styles.container}>
        <Head>
            <link rel='icon' href='/favicon.ico'/>
            <meta name='description' content={description}/>
            <meta name='og:title' content={siteTitle}/>
        </Head>
        <main>{children}</main>
    </div>);
}