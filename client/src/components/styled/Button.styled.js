import styled from 'styled-components';
import { Link } from 'react-router-dom';

export const StyledButton = styled.button`
  display: block;
  color: black;
  background-color: #e1ecf4;
  font-size: ${({ theme }) => theme.size.common.default_font};
  height: 33px;
  border: 0.6px solid rgb(122, 167, 199);
  width: ${(props) => props.width};

  &:hover {
    background-color: hsla(205, 57%, 81%, 1);
  }
`;

export const StyledBlueButton = styled(StyledButton)`
  color: white;
  background-color: ${({ theme }) => theme.color.common.blue_btn_bg};
  &:hover {
    background-color: hsl(206, 100%, 40%);
  }
`;

export const StyledBlueBigButton = styled(StyledBlueButton)`
  color: white;
  background-color: ${({ theme }) => theme.color.common.blue_btn_bg};
  height: 38px;
`;

export const StyledLink = styled(Link)`
  display: block;
  text-align: center;
  font-size: ${({ theme }) => theme.size.common.default_font};
  height: 33px;
  line-height: 29px;
  color: black;
  width: ${(props) => props.width};
  border: 0.6px solid rgb(122, 167, 199);
  text-decoration: none;
  background-color: #e1ecf4;
  &:hover {
    background-color: hsla(205, 57%, 81%, 1);
  }
`;

export const StyledBlueLink = styled(StyledLink)`
  color: white;
  background-color: ${({ theme }) => theme.color.common.blue_btn_bg};

  &:hover {
    background-color: hsl(206, 100%, 40%);
  }
`;

export const StyledBigBlueLink = styled(StyledBlueLink)`
  height: 38px;
  line-height: 34px;
`;
