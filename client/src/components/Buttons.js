import {
  StyledButton,
  StyledBlueButton,
  StyledBlueBigButton,
  StyledLink,
  StyledBlueLink,
  StyledBigBlueLink,
} from './styled/Button.styled';

export const Button = ({ value, width }) => {
  return <StyledButton width={width}>{value}</StyledButton>;
};

export const BlueButton = ({ value, width, onClick }) => {
  return (
    <StyledBlueButton onClick={onClick} width={width}>
      {value}
    </StyledBlueButton>
  );
};

export const BlueBigButton = ({ value, width }) => {
  return <StyledBlueBigButton width={width}>{value}</StyledBlueBigButton>;
};

export const LinkButton = ({ width, to, children }) => {
  return (
    <StyledLink to={to} width={width}>
      {children}
    </StyledLink>
  );
};

export const BlueLinkButton = ({ width, to, children }) => {
  return (
    <StyledBlueLink to={to} width={width}>
      {children}
    </StyledBlueLink>
  );
};

export const BlueBigLinkButton = ({ width, to, children }) => {
  return (
    <StyledBigBlueLink to={to} width={width}>
      {children}
    </StyledBigBlueLink>
  );
};
