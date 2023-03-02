import styled from 'styled-components';
import { useState } from "react";
import { useNavigate } from 'react-router-dom';

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
const Error = styled.span`
  color: tomato;
  font-weight: 500;
  font-size: 12px;
`;

const Login = () =>
{
  const [username, setUsername] = useState('');
  const [emailErrorMessage, setEmailErrorMessage] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();


  const emailInputHandler = (event) =>
  {
    const emailRegex = /([\w-.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    const currentEmail = event.target.value;
    setUsername(currentEmail);

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
    fetch("http://ec2-3-36-117-214.ap-northeast-2.compute.amazonaws.com:8080/login", {
      method: 'POST',
      body: JSON.stringify({ username, password }),
      headers: {
        'Content-Type': 'application/json',
      },
      credentials: 'include',
    }).then((response) =>
      { 
        localStorage.setItem('user', response.headers.get('Authorization'));
        navigate(`/`);
        
      }).catch((e) => {console.log(e)})
  };

  const onSubmit = (event) => {
    event.preventDefault()
  }

  return (
    <LoginFormLayout>
      <form
        style={{ display: 'flex', flexDirection: 'column' }} onSubmit={onSubmit}
        method="POST"
      >
        <label htmlFor="email">
          Email <br />
          <input type="email" name="username" value={username} onChange={emailInputHandler} />
          {username.length > 0 && <Error>{emailErrorMessage}</Error>}
        </label>
        <br />
        <label htmlFor="pasword">
          Password &nbsp;
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
          }} onClick={handleSubmit}
        > Log in
        </button>
      </form>
    </LoginFormLayout>
  );
};

export default Login;
