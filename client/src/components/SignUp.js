import styled from 'styled-components';

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

  div {
    color: ${({ theme }) => theme.color.sign_up.pwd_bottom_text};
    font-size: 12px;
    margin-top: 5px;
    padding: 5px;
    line-height: 20px;
  }
`;

const Container = styled.div`
  display: flex;
`;

const SignUp = () => {
  return (
    <Container>
      <SignupForm>
        <form style={{ display: 'flex', flexDirection: 'column' }}>
          <label htmlFor="text">
            Display name <br />
            <input type="text" name="text" />
          </label>
          <br />
          <label htmlFor="email">
            Email <br />
            <input type="email" name="email" />
          </label>
          <br />
          <label htmlFor="pasword">
            Password <br />
            <input type="password" name="password" />
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
          >
            Sign up
          </button>
        </form>
      </SignupForm>
    </Container>
  );
};

export default SignUp;
