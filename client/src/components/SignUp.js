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
  & form {
    width: 240;
    margin-top: 10px;
    input {
      width: 100%;
      height: 35px;
    }
  }
  /* div {
    color: ${({ theme }) => theme.color.sign_up.pwd_bottom_text};
    font-size: 12px;
    margin-top: 5px;
    padding: 5px;
    line-height: 20px;
  } */
`;
const Error = styled.div`
  color: #d0393e;
  font-size: 12px;
  margin-bottom: 13px;
  width: 240px;
`;
const Name = styled.div`
  width: 240px;
  font-weight: 800px;
  font-size: 13px;
  margin-bottom: 5px;
`;

const NameForm = styled.input`
  width: 240px;
  height: 33px;
  font-size: 13px;
  padding: 7.8px 9.1px;
  border: ${({ isNameError }) =>
    isNameError ? "1px solid #d0393e" : "1px solid rgb(186, 191, 196)"};
  border-radius: 4px;
  font-weight: 400px;
  margin-bottom: 10px;
`;

const Email = styled.div`
  width: 240px;
  font-weight: 800px;
  font-size: 13px;
  margin-bottom: 5px;
`;

const EmailForm = styled.input`
  width: 240px;
  height: 33px;
  font-size: 13px;
  padding: 7.8px 9.1px;
  border: ${({ isEmailError }) =>
    isEmailError ? "1px solid #d0393e" : "1px solid rgb(186, 191, 196)"};
  border-radius: 4px;
  font-weight: 400px;
  margin-bottom: 10px;
`;

const Password = styled.div`
  width: 240px;
  font-weight: 800px;
  font-size: 13px;
  margin-bottom: 5px;
`;

const PasswordForm = styled.input`
  width: 240px;
  height: 33px;
  font-size: 13px;
  padding: 7.8px 9.1px;
  border: ${({ isPwError }) =>
    isPwError ? "1px solid #d0393e" : "1px solid rgb(186, 191, 196)"};
  border-radius: 4px;
  font-weight: 400px;
  margin-bottom: 10px;
`;
const PasswordMessage = styled.div`
  width: 240px;
  height: 48px;
  font-size: 11px;
  color: #4b4b4b;
  margin-bottom: 16px;
`;

const SignupSubmit = styled.div`
  padding: 10.4px;
  width: 240px;
  height: 38px;
  background-color: #078AFF;
  box-shadow: rgba(255, 255, 255, 0.4) 0px 1px 0px 0px inset;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #fff;
  font-weight: 700;
  font-size: 13px;
  margin-top: 6px;
  cursor: pointer;
  border-radius: 5px;
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
  { if (pwState === true && emailState === true && nameState === true){
    event.preventDefault();

    fetch("/members", {
      method: 'POST',
      body: JSON.stringify({ nickName, email, password }),
      headers: {
        'Content-Type': 'application/json',
      },
      credentials: 'include',
    }).then((response) =>
      {
        navigate(`/log_in`);
        })
      }
    } 

  return (
    <Container>
      <SignupForm >
            <Name>Display name</Name>
            <NameForm onChange={nickNameHandler} isNameError={isNameError} />
            <Email>Email</Email>
            <EmailForm onChange={emailHandler} isEmailError={isEmailError} />
            {isEmailError ? <Error>{emailErrorMessage}</Error> : null}
            <Password>Password</Password>
            <PasswordForm
              type="password"
              onChange={passwordHandler}
              isPwError={isPwError}/>
            {isPwError ? <Error>{pwErrorMessage}</Error> : null}
            <PasswordMessage>
              Passwords must contain at least eight characters, including at
              least 1 letter and 1 number.
            </PasswordMessage>
            <SignupSubmit className="signup" onClick={signUpHandler}>
              Sign up
            </SignupSubmit>
      </SignupForm>
    </Container >
  );
  }

export default SignUp;