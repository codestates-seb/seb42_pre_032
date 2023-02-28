import styled from 'styled-components';
import { useState } from 'react';

const LoginFormLayout = styled.div`
  display: flex;
  width: 288px;
  height: 236px;
  background-color: ${({ theme }) => theme.color.common.default_bg};
  font-size: ${({ theme }) => theme.size.login.label};
  font-weight: 700;
  color: solid 1px ${({ theme }) => theme.color.common.default_font};
  line-height: normal;
  box-shadow: 3px 3px 20px rgba(0, 0, 0, 0.2);
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  box-sizing: border-box;
  padding: 1rem;
  border-radius: 10px;
  justify-content: center;
  align-items: center;
  & form {
    width: 240;
    input {
      width: 100%;
      height: 35px;
    }
  }
`;
const ErrorMessage = styled.span`
  color: tomato;
  font-weight: 500;
  font-size: 12px;
`;
/* a {
  text-align: right;
  float: right;
  text-decoration: none;
  color: ${({ theme }) => theme.color.common.blue_btn_bg};
  font-size: ${({ theme }) => theme.size.common.default_font};
} */

const Login = () =>
{
  const [email, setEmail] = useState('');
  const [emailErrorMessage, setEmailErrorMessage] = useState('');
  const [password, setPassword] = useState('');

  const emailInputHandler = (event) =>
  {
    const emailRegex = /([\w-.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    const currentEmail = event.target.value;
    setEmail(currentEmail);

    if (!emailRegex.test(currentEmail)) {
      setEmailErrorMessage('올바른 이메일 형식이 아닙니다.');
    } else {
      setEmailErrorMessage('');
    }
  };
  const passwordInputValueHandler = (event) =>
  {
    setPassword(event.target.value);
  }

  const handleSubmit = (event) =>
  {
    event.preventDefault();

    fetch("http://localhost:8080/login", {
      method: 'POST',
      body: JSON.stringify({ email, password }),
      headers: {
        'Content-Type': 'application/json',
      },
      credentials: 'include',
    })
      .then((response) => response.json())
      .then((data) =>
      {
        if (data.success) {
          localStorage.setItem('access-token', data.token);
        } else {
          alert(data.message);
        }
      });
  };

  return (
    <LoginFormLayout>
      <form
        style={{ display: 'flex', flexDirection: 'column' }}
      >
        <label htmlFor="email">
          Email <br />
          <input type="email" name="email" value={email} onChange={emailInputHandler} />
          {email.length > 0 && <ErrorMessage>{emailErrorMessage}</ErrorMessage>}
        </label>
        <br />
        <label htmlFor="pasword">
          Password &nbsp;
          {/* <a href="/">Forgot password?</a> */}
          <br />
          <input type="password" name="password" value={password} onChange={passwordInputValueHandler} />
        </label>
        <br />
        <button
          type="submit"
          style={{
            height: '35px',
            width: '240px',
            backgroundColor: '#078AFF',
            borderRadius: '5px',
            color: '#fff',
            border: 'none',
            cursor: 'pointer',
          }} onSubmit={handleSubmit}
        >
          Log in
        </button>
      </form>
    </LoginFormLayout>
  );
};

export default Login;
