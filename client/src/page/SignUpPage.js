import styled from 'styled-components';
import SignUp from '../components/SignUp';
import { Link } from 'react-router-dom';

const Background = styled.div`
  background-color: ${({ theme }) => theme.color.header.bg};
  width: 100vw;
  height: calc(100vh - 50px);
  display: flex;
  justify-content: center;
  flex-direction: row;
  align-items: center;
  flex-wrap: wrap;
`;

const SignupBgtext = styled.div`
  color: black;
  top: 15%;
  left: 50%;
  margin-top: 5px;
  transform: translate(-50%, -50%);
  position: absolute;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  justify-content: space-evenly;
  font-size: 25px;
  /* width: 500px;
  height: 70px; */
  line-height: normal;
`;
const SignupbottomText = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-evenly;
  position: absolute;
  margin-top: 30px;
  top: 90%;
  left: 50%;
  transform: translate(-50%, -50%);
  flex-direction: column;
  font-size: ${({ theme }) => theme.size.common.default_font};
`;
const NavLink = styled(Link)`
  font-weight: 500;
  text-decoration: none;
  color: ${({ theme }) => theme.color.common.blue_btn_bg};
  font-size: ${({ theme }) => theme.size.common.default_font};
`;

const SignUpPage = () =>
{

  return (
    <Background>
      <div>
        <SignupBgtext>
          <p>
            Create your Stack Overflow account. It’s free <br /> and only takes
            a minute.
          </p>
        </SignupBgtext>
        <br />
        <SignUp />
        <br />
        <SignupbottomText>
          <p>
            Already have an account? &nbsp;
            <NavLink to="/log_in" >Log in</NavLink>
          </p>
        </SignupbottomText>
      </div>
    </Background>
  );
};

export default SignUpPage;
