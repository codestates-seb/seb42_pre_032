import { GrSearch } from 'react-icons/gr';
import {
  Link,
  useLocation,
  useNavigate,
  useSearchParams,
} from 'react-router-dom';
import { LinkButton, BlueLinkButton, BlueButton } from './Buttons';
import { StyledHeader, StyledWrapper } from './styled/Header.styled';
import { useEffect, useState } from 'react';
import StyledInput from './styled/Input.styled';

const Header = () => {
  const [searchParams] = useSearchParams();
  let query = searchParams.get('q');
  const inputValue = query || '';
  const [str, setStr] = useState(inputValue);
  const navigate = useNavigate();
  const [token, setToken] = useState('');
  const { pathname } = useLocation();

  const onChange = (e) => {
    setStr(e.target.value);
  };

  useEffect(() => {
    setToken(localStorage.getItem('user'));
  }, [pathname]);

  useEffect(() => {
    setStr(inputValue);
  }, [inputValue]);

  const onSubmit = (e) => {
    e.preventDefault();
    navigate(`./searchresult?q=${str}`);
  };

  const onLogout = () => {
    localStorage.removeItem('user');
    navigate(`./log_in`);
  };

  return (
    <StyledHeader>
      <StyledWrapper>
        <Link id="logo" to="/">
          <span>Stack Overflow</span>
        </Link>
        <form onSubmit={onSubmit}>
          <div>
            <GrSearch />
            <StyledInput
              onChange={onChange}
              value={str}
              type="text"
              placeholder="Search..."
            />
          </div>
        </form>
        {token ? (
          <>
            <BlueButton onClick={onLogout} value="Log out" width="60px" />
          </>
        ) : (
          <div>
            <LinkButton to={'/log_in'} width="60px">
              Log in
            </LinkButton>
            <BlueLinkButton to={'/signup'} width="66px">
              Sign up
            </BlueLinkButton>
          </div>
        )}
      </StyledWrapper>
    </StyledHeader>
  );
};

export default Header;
