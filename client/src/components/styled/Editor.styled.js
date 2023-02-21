import styled from 'styled-components';
import URL from '../../asset/image/background.svg';

export const StyledWrraper = styled.section`
  padding: 0 24px;
  max-width: 1120px;
  margin: 0 auto;
  margin-bottom: 80px;

  .notice {
    border: 1px solid rgb(166, 206, 237);
    background-color: rgb(235, 244, 251);
    width: 70%;
    line-height: 19px;

    h2 {
      font-size: 21px;
    }

    .fw-normal {
      font-weight: 400;
    }

    .fw-bold {
      font-weight: 600;
    }

    .mb16 {
      margin-bottom: 16px;
    }

    .mb8 {
      margin-bottom: 8px;
    }

    .mb0 {
      margin-bottom: 0px;
    }

    .fs-body2 {
      font-size: 15px;
    }

    .p24 {
      padding: 24px;
    }

    .d-flex {
      display: flex;
    }

    .ai-center {
      align-items: center;
    }

    ul {
      list-style-type: disc;
      margin-left: 30px;
    }

    p {
      margin-bottom: 16px;
      margin-top: 0;
    }

    a:hover {
      color: hsl(206, 100%, 52%);
      text-decoration: none;
    }

    a {
      text-decoration: none;
      cursor: pointer;
    }
  }
`;

export const StyledTitle = styled.div`
  display: flex;
  height: 130px;
  align-items: center;

  background-image: url(${URL});
  background-repeat: no-repeat;
  background-position: right bottom;

  h2 {
    font-size: 27px;
    font-weight: 600;
  }
`;

export const StyledForm = styled.form`
  display: flex;
  flex-direction: column;
  width: 100%;
  align-items: flex-start;

  .buttons {
    width: 100%;
    display: flex;
    justify-content: space-between;
    margin-top: 15px;

    button:first-child {
      color: hsl(358, 62%, 47%);
      background-color: transparent;
      border: none;

      &:hover {
        background-color: hsl(358, 75%, 97%);
      }
    }
  }
`;

export const StyledBlock = styled.section`
  background-color: white;
  padding: 24px;
  width: 100%;
  border: 1px solid hsl(210, 8%, 90%);
  border-radius: 3px;
  line-height: 17px;
  margin-top: 12px;

  h3 {
    font-size: 15px;
    font-weight: 600;
    padding: 0 2px;
  }

  label {
    font-size: 12px;
    color: hsl(210, 8%, 25%);
  }

  &.title {
    input {
      padding-left: 3px;
      margin-top: 5px;
    }
  }

  &.content {
    padding-bottom: 70px;
    .quill {
      margin-top: 15px;
      height: 300px;
    }
  }
`;
