import { GrSearch } from 'react-icons/gr';
import { Link, useNavigate } from 'react-router-dom';
import { LinkButton, BlueLinkButton } from './Buttons';
import { StyledHeader, StyledWrapper } from './styled/Header.styled';
import { useState } from 'react';
import StyledInput from './styled/Input.styled';

const Header = () => {
  const [str, setStr] = useState('');
  const navigate = useNavigate();

  const onChange = (e) => {
    setStr(e.target.value);
  };

  const onSubmit = (e) => {
    e.preventDefault();
    setStr('');
    navigate('/', { replace: false });
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
          <LinkButton to={'/login'} width="60px">
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
