import styled from 'styled-components';

export const StyledFooter = styled.footer`
  width: 100vw;
  height: max-content;
  background-color: hsl(210, 8%, 15%);
  color: hsl(210, 8%, 60%);
  padding-top: 10px;
  padding-bottom: 0px;
  .footerContainer {
    margin: 0;
    display: flex;
    justify-content: space-between;

    padding: 32px 12px 12px;
    width: 100vw;
    height: 100%;
    .footLogo {
      flex: 0 0 64px;
      margin: -12px 0 32px;
    }
    .menuContainer {
      display: flex;
      flex: 2 1 auto;
      > ul {
        flex: 1 0 auto;
        padding: 0 12px 24px 0;
        > h5 {
          margin: 0 0 4px;
          > a {
            text-decoration: none;
            color: hsl(210, 8%, 75%);
          }
          color: hsl(210, 8%, 75%);
        }
        > li {
          line-height: 2;
          font-size: 13px;
          list-style: none;
        }
      }
    }
    .snsCopyright {
      display: flex;
      flex-direction: column;
      flex: 1 1 150px;
      font-size: 11px;

      .snsContainer {
        > ul {
          padding: 0;
          display: flex;
          > li {
            list-style: none;
            margin-left: 12px;
            padding: 4px 0;
            &:first-child {
              margin: 0;
            }
          }
        }
      }
      .copyrightContainer {
        margin: auto 0 24px;
        list-style: none;
      }
    }
  }
`;
