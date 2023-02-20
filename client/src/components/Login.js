import styled from 'styled-components';

const LoginFormLayout = styled.form`
  display: flex;
  width: 288px;
  height: 236px;
  background-color: ${({ theme }) => theme.color.common.default_bg};
  font-size: ${({ theme }) => theme.size.login.label};
  font-weight: 700;
  color: solid ${({ theme }) => theme.color.common.default_font};
  line-height: normal;
  box-shadow: 3px 3px 30px rgba(0, 0, 0, 0.5);
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  box-sizing: border-box;
  padding: 1rem;
  border-radius: 10px;
  justify-content: center;
  align-items: center;

  a {
    text-align: right;
    float: right;
    text-decoration: none;
    color: ${({ theme }) => theme.color.common.blue_btn_bg};
    font-size: ${({ theme }) => theme.size.common.default_font};
  }
  input {
    width: 240px;
    height: 33px;
  }
`;

const Bottomtext = styled.div`
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  position: absolute;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-evenly;
  font-size: ${({ theme }) => theme.size.common.default_font};
  padding-top: 350px;
  width: 288px;
  height: 78px;
  line-height: normal;

  a {
    text-decoration: none;
    color: ${({ theme }) => theme.color.common.blue_btn_bg};
    font-size: ${({ theme }) => theme.size.common.default_font};
  }
`;

const Img = styled.div`
  img {
    width: 40px;
    height: 40px;
    left: 50%;
    top: 25%;
    transform: translate(-50%, -50%);
    position: absolute;
  }
`;

const Login = () => {
  return (
    <div className="login_page">
      <Img>
        <img
          src="https://github.com/codestates-seb/seb39_pre_001/blob/main/client/src/static/stack-overflow.png?raw=true"
          alt="stackoverflow로고"
        ></img>
      </Img>
      <LoginFormLayout>
        <form>
          <label htmlFor="email">
            Email <br />
            <input type="email" name="email" />
          </label>
          <br />
          <br />
          <label htmlFor="password">
            Password &nbsp;
            <a href="/">Forgot password?</a>
            <br />
            <input type="password" name="password" />
          </label>
          <br />
          <br />
          <button>Login</button>
        </form>
      </LoginFormLayout>
      <Bottomtext>
        <div>
          Don&#39;t have an account? &nbsp;
          <a href="/">Sign up</a>
        </div>
      </Bottomtext>
    </div>
  );
};

export default Login;
