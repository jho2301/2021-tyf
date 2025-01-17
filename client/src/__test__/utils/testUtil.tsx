import { render } from '@testing-library/react';
import { Suspense } from 'react';
import { BrowserRouter } from 'react-router-dom';
import { RecoilRoot } from 'recoil';
import { ThemeProvider } from 'styled-components';
import Spinner from '../../components/Spinner/Spinner';

import { theme } from '../../theme';

export const myRender = (children: React.ReactNode) =>
  render(
    <RecoilRoot>
      <ThemeProvider theme={theme}>
        <Suspense fallback={<Spinner />}>
          <BrowserRouter>{children}</BrowserRouter>
        </Suspense>
      </ThemeProvider>
    </RecoilRoot>
  );
