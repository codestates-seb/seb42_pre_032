import { createGlobalStyle } from 'styled-components';
import reset from 'styled-reset';

const GlobalStyle = createGlobalStyle` 
  ${reset}
  
  *, *:before, *:after {
    box-sizing: border-box;
  }

  body{
    background-color: ${({ theme }) => theme.color.common.blue_btn_bg};
  }

`;

export default GlobalStyle;
