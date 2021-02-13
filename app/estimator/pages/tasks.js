'use strict';

import React from 'react';
import Head from 'next/head';
import Layout, {siteTitle} from '../components/layout';

export default class Home extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <Layout>
        <Head>
          <title>{siteTitle}</title>
        </Head>
        Tests
      </Layout>
    );
  }
}