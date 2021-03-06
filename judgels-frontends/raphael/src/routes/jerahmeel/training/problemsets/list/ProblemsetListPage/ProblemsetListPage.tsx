import { Callout, Intent } from '@blueprintjs/core';
import * as React from 'react';

import { APP_CONFIG } from 'conf';
import { withBreadcrumb } from 'components/BreadcrumbWrapper/BreadcrumbWrapper';

const ProblemsetListPage = () => (
  <Callout intent={Intent.WARNING} icon="info-sign">
    <strong>New page under construction.</strong> See the old training page here:{' '}
    <a href={APP_CONFIG.tempHome.jerahmeelUrl}>
      <strong>{APP_CONFIG.tempHome.jerahmeelUrl}</strong>
    </a>
  </Callout>
);

export default withBreadcrumb('Problemsets')(ProblemsetListPage);
