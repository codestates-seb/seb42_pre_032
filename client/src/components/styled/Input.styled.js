import styled from 'styled-components';

const StyledInput = styled.input`
  border-color: hsl(210, 8%, 75%);
  width: 100%;
  height: 32px;
  padding-left: 30px;

  &:focus {
    outline: none;
    border-radius: 3px;
    border-color: hsl(206, 90%, 69.5%);
    box-shadow: 0 0 0 4px hsla(206, 100%, 40%, 0.15);
  }
`;

export default StyledInput;
