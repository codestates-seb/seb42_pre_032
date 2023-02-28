import styled from 'styled-components';
import Login from '../components/Login';
import { Link } from 'react-router-dom';
import logo_stack from '../asset/stack-overflow.png';
import { useEffect } from 'react';
// import { response } from 'express';

const Background = styled.div`
  background-color: ${({ theme }) => theme.color.header.bg};
  width: 100vw;
  height: calc(100vh - 50px);
  display: flex;
  justify-content: center;
  align-items: center;

  a {
    color: ${({ theme }) => theme.color.common.blue_btn_bg};
    font-size: ${({ theme }) => theme.size.common.default_font};
  }
`;
const Logowrapper = styled.div`
  display: flex;
  justify-content: center;
  margin-bottom: 400px;
  cursor: pointer;
  img {
    width: 32px;
    height: 37px;
  }
`;
const Bottomtext = styled.div`
  left: 50%;
  top: 75%;
  transform: translate(-50%, -50%);
  position: absolute;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-evenly;
  font-size: ${({ theme }) => theme.size.common.default_font};
  width: 288px;
  height: 78px;
  line-height: normal;
`;
const NavLink = styled(Link)`
  font-weight: 500;
  text-decoration: none;
  color: ${({ theme }) => theme.color.common.blue_btn_bg};
  font-size: ${({ theme }) => theme.size.common.default_font};
`;

const LoginPage = () =>
{
  useEffect(() =>
  {
    async function fetchdata()
    {
      const response = await fetch('/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          username: 'admin@gmail.com',
          password: '123',
        }),
        credentials: 'include',
      });
      console.log(response.headers.get('authorization'));
    }
    fetchdata();
  }, []);


  return (
    <Background>
      <Logowrapper>
        <img src={logo_stack} alt="logo_stack" />
      </Logowrapper>
      <div>
        <Login />
        <Bottomtext>
          <div>
            Don&#39;t have an account? &nbsp;
            <NavLink to="/signup">Sign up</NavLink>
          </div>
        </Bottomtext>
      </div>
    </Background>
  );
};

export default LoginPage;
