import styled from 'styled-components';
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";


const SignupForm = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  width: 268px;
  height: 450px;
  background-color: ${({ theme }) => theme.color.common.default_bg};
  font-size: ${({ theme }) => theme.size.login.label};
  font-weight: 700;
  color: solid 1px ${({ theme }) => theme.color.common.default_font};
  line-height: normal;
  box-shadow: 3px 3px 20px rgba(0, 0, 0, 0.2);
  position: absolute;
  left: 50%;
  top: 55%;
  transform: translate(-50%, -50%);
  box-sizing: border-box;
  padding: 1rem;
  border-radius: 10px;
  justify-content: center;
  align-items: center;
  border: ${({ isNameError }) =>
    isNameError ? "1px solid #d0393e" : "1px solid rgb(186, 191, 196)"};
    border: ${({ isEmailError }) =>
    isEmailError ? "1px solid #d0393e" : "1px solid rgb(186, 191, 196)"};
    border: ${({ isPwError }) =>
    isPwError ? "1px solid #d0393e" : "1px solid rgb(186, 191, 196)"};
  & form {
    width: 240;
    margin-top: 10px;
    input {
      width: 100%;
      height: 35px;
    }
  }
  div {
    color: ${({ theme }) => theme.color.sign_up.pwd_bottom_text};
    font-size: 12px;
    margin-top: 5px;
    padding: 5px;
    line-height: 20px;
  }
`;
const Error = styled.div`
  color: #d0393e;
  font-size: 12px;
  margin-bottom: 13px;
  width: 240px;
`;

const Container = styled.div`
  display: flex;
`;

const SignUp = () =>
{
  const [nickName, setNickName] = useState("");
  const [isNameError, setIsNameError] = useState(false);
  const [nameState, setNameState] = useState(false);

  const [email, setEmail] = useState("");
  const [isEmailError, setIsEmailError] = useState(false);
  const [emailErrorMessage, setEmailErrorMessage] = useState("");
  const [emailState, setEmailState] = useState(false);

  const [password, setPassword] = useState("");
  const [isPwError, setIsPwError] = useState(false);
  const [pwErrorMessage, setPwErrorMessage] = useState("");
  const [pwState, setPwState] = useState(false);

  const navigate = useNavigate();
  const nickNameHandler = (e) =>
  {
    setNickName(e.target.value);
  };

  const emailHandler = (e) =>
  {
    setEmail(e.target.value);
  };

  const passwordHandler = (e) =>
  {
    setPassword(e.target.value);
  };

  useEffect(() =>
  {
    if (nickName === "") {
      setIsNameError(false);
      setNameState(false);
    } else {
      setIsNameError(false);
      setNameState(true);
    }

    if (email === "") {
      setIsEmailError(false);
    } else if (
      email.match(
        /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
      )
    ) {
      setIsEmailError(false);
      setEmailState(true);
    } else {
      setEmailErrorMessage("Please enter the correct email format");
      setIsEmailError(true);
      setEmailState(false);
    }

    if (password === "") {
      setIsPwError(false);
    } else if (password.length >= 8 && password.match(/[a-zA-Z]+[0-9]+/)) {
      setIsPwError(false);
      setPwState(true);
    } else if (!password.match(/[0-9]+/) && password.match(/[a-zA-z]+/)) {
      setPwErrorMessage(
        "Please add one of the following things to make your password stronger: number"
      );
      setIsPwError(true);
      setPwState(false);
    } else if (password.match(/[0-9]+/) && !password.match(/[a-zA-z]+/)) {
      setPwErrorMessage(
        "Please add one of the following things to make your password stronger: letters"
      );
      setIsPwError(true);
      setPwState(false);
    } else if (password.length < 8) {
      setPwErrorMessage("Please enter at least 8 characters");
      setIsPwError(true);
      setPwState(false);
    }
  }, [nickName, email, password]);

  const signUpHandler = (event) =>
  {
    event.preventDefault();

    fetch("http://localhost:8080/signup", {
      method: 'POST',
      body: JSON.stringify({ nickName, email, password }),
      headers: {
        'Content-Type': 'application/json',
      },
      credentials: 'include',
    })
      .then((response) => response.json())
      .then((response) =>
      {
        console.log(response)
        if (response.signUp === true) {
          navigate("/login");
        } else {
          console.log("이미 가입된 회원입니다.");
        }
      })
  };


  return (
    <Container>
      <SignupForm >
        <form style={{ display: 'flex', flexDirection: 'column' }}>
          <label htmlFor="text">
            Display name <br />
            <input type="text" name="text" onChange={nickNameHandler} isNameError={isNameError} />
          </label>
          <br />
          <label htmlFor="email">
            Email <br />
            <input type="email" name="email" onChange={emailHandler} isEmailError={isEmailError} />
            {isEmailError ? <Error>{emailErrorMessage}</Error> : null}
          </label>
          <br />
          <label htmlFor="pasword" >
            Password <br />
            <input type="password" name="password" onChange={passwordHandler} isPwError={isPwError}
            />
            {isPwError ? <Error>{pwErrorMessage}</Error> : null}
          </label>
          <br />
          <div className="text">
            Passwords must contain at least eight <br />
            characters, including at least 1 letter and 1 number.
          </div>
          <br />
          <button
            type="submit"
            style={{
              height: '38px',
              width: '240px',
              backgroundColor: '#078AFF',
              borderRadius: '5px',
              color: '#fff',
              border: 'none',
              cursor: 'pointer',
            }}
            onClick={signUpHandler}
          >
            Sign up
          </button>
        </form>
      </SignupForm>
    </Container >
  );
};

export default SignUp;