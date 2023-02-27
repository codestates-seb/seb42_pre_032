import { GrSearch } from 'react-icons/gr';
import {
  Link,
  // useLocation,
  useNavigate,
  useSearchParams,
} from 'react-router-dom';
import { LinkButton, BlueLinkButton } from './Buttons';
import { StyledHeader, StyledWrapper } from './styled/Header.styled';
import { useEffect, useState } from 'react';
import StyledInput from './styled/Input.styled';

const Header = () => {
  const [searchParams] = useSearchParams();
  let query = searchParams.get('q');
  const inputValue = query || '';
  const [str, setStr] = useState(inputValue);
  const navigate = useNavigate();
  // const { pathname } = useLocation();

  const onChange = (e) => {
    setStr(e.target.value);
  };

  useEffect(() => {
    setStr(inputValue);
  }, [inputValue]);

  const onSubmit = (e) => {
    e.preventDefault();
    navigate(`./searchresult?q=${str}`);
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
        <div>
          <LinkButton to={'/log_in'} width="60px">
            Log in
          </LinkButton>
          <BlueLinkButton to={'/signup'} width="66px">
            Sign up
          </BlueLinkButton>
        </div>
      </StyledWrapper>
    </StyledHeader>
  );
};

export default Header;
