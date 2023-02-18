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

export const BlueButton = ({ value, width }) => {
  return <StyledBlueButton width={width}>{value}</StyledBlueButton>;
};

export const BlueBigButton = ({ value, width }) => {
  return <StyledBlueBigButton width={width}>{value}</StyledBlueBigButton>;
};

export const LinkButton = ({ width, children }) => {
  return <StyledLink width={width}>{children}</StyledLink>;
};

export const BlueLinkButton = ({ width, children }) => {
  return <StyledBlueLink width={width}>{children}</StyledBlueLink>;
};

export const BlueBigLinkButton = ({ width, children }) => {
  return <StyledBigBlueLink width={width}>{children}</StyledBigBlueLink>;
};
