import styled from 'styled-components';

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

  /* a {
    text-align: right;
    float: right;
    text-decoration: none;
    color: ${({ theme }) => theme.color.common.blue_btn_bg};
    font-size: ${({ theme }) => theme.size.common.default_font};
  } */
`;

const Login = () => {
  return (
    <LoginFormLayout>
      <form
        style={{ display: 'flex', flexDirection: 'column' }}
        // onSubmit={onSubmit}
        // onChange={onChange}
      >
        <label htmlFor="email">
          Email <br />
          <input type="email" name="email" />
        </label>
        <br />
        <label htmlFor="pasword">
          Password &nbsp;
          {/* <a href="/">Forgot password?</a> */}
          <br />
          <input type="password" name="password" />
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
          }}
        >
          Log in
        </button>
      </form>
    </LoginFormLayout>
  );
};
export default Login;
