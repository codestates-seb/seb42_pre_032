import styled from 'styled-components';
import LogoSrc from '../../asset/image/1200px-Stack_Overflow_logo.png';

export const StyledHeader = styled.header`
  display: flex;
  align-items: center;
  height: 50px;
  border-top: 3px solid ${({ theme }) => theme.color.header.orange};
  background-color: hsl(210, 8%, 97.5%);
  box-shadow: 0 1px 2px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.05),
    0 2px 8px hsla(0, 0%, 0%, 0.05);

  .menu {
    display: flex;
    padding-left: 16px;
    padding-right: 16px;
    height: 100%;
    align-items: center;
    justify-content: center;

    & > svg {
      width: 17px;
      height: 100%;
    }
  }

  #logo {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 150px;
    height: 100%;
    span {
      display: block;
      width: 95%;
      height: 60%;
      background-image: url(${LogoSrc});
      background-size: 100% 100%;
      background-repeat: no-repeat;
      text-indent: -99999px;
    }
  }
`;

export const StyledWrapper = styled.div`
  display: flex;
  max-width: 1264px;
  width: 100%;
  height: 100%;
  margin: 0 auto;
  align-items: center;

  & > a {
    &:hover {
      background-color: hsl(210, 8%, 90%);
    }
  }

  form {
    display: flex;
    align-items: center;
    flex-grow: 1;
    padding: 0 8px;
    margin-right: 8px;
    justify-content: flex-end;

    div {
      position: relative;
      flex-grow: 1;

      svg {
        position: absolute;
        top: 7px;
        left: 7px;
      }

      input {
        border-color: hsl(210, 8%, 75%);
        width: 100%;
        height: 32px;
        padding-left: 30px;

        &:focus {
          outline: none;
          border-radius: 3px;
          border-color: hsl(206, 90%, 69.5%);
          box-shadow: 0 0 0 4px hsla(206, 100%, 40%, 0.15);
        }
      }
    }
  }

  & > div {
    display: flex;
    a {
      margin-right: 4px;
    }
  }
`;
