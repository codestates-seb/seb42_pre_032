import { ThemeProvider } from 'styled-components';
import GlobalStyle from './components/styled/global.styled';

function App() {
  const theme = {};

  return (
    <ThemeProvider theme={theme}>
      <GlobalStyle />
    </ThemeProvider>
  );
}

export default App;
