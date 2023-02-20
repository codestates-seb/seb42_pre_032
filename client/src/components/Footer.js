import { Link } from 'react-router-dom';
import { StyledFooter } from './styled/Footer.styled';
import { ReactComponent as FooterLogo } from '../asset/image/LogoGraysm.svg';

const Footer = () => {
  if (window.location.pathname === '/*') return null;

  return (
    <>
      <StyledFooter>
        <ul className="footerContainer">
          <div className="footLogo">
            <Link to="/">
              <FooterLogo />
            </Link>
          </div>
          <li className="menuContainer">
            <ul>
              <h5>
                <a href="/">STACK OVERFLOW</a>
              </h5>
              <li>Questions&nbsp;&nbsp;&nbsp;&nbsp;</li>
              <li>Help</li>
            </ul>
            <ul>
              <h5>PRODUCTS</h5>
              <li>Teams&nbsp;&nbsp;&nbsp;&nbsp;</li>
              <li>Advertising&nbsp;&nbsp;&nbsp;&nbsp;</li>
              <li>Collectives&nbsp;&nbsp;&nbsp;&nbsp;</li>
              <li>Talent</li>
            </ul>
            <ul>
              <h5>COMPANY</h5>
              <li>About&nbsp;&nbsp;&nbsp;&nbsp;</li>
              <li>Press&nbsp;&nbsp;&nbsp;&nbsp;</li>
              <li>Work Here&nbsp;&nbsp;&nbsp;&nbsp;</li>
              <li>Legal&nbsp;&nbsp;&nbsp;&nbsp;</li>
              <li>Privacy Policy&nbsp;&nbsp;&nbsp;&nbsp;</li>
              <li>Terms of Service&nbsp;&nbsp;&nbsp;&nbsp;</li>
              <li>Contact Us&nbsp;&nbsp;&nbsp;&nbsp;</li>
              <li>Cookie Settings&nbsp;&nbsp;&nbsp;&nbsp;</li>
              <li>Cookie Policy&nbsp;&nbsp;&nbsp;&nbsp;</li>
            </ul>
            <ul>
              <h5>STACK EXCHANGE NETWORK</h5>
              <li>Technology&nbsp;&nbsp;&nbsp;&nbsp;</li>
              <li>Culture & recreation&nbsp;&nbsp;&nbsp;&nbsp;</li>
              <li>Life & arts&nbsp;&nbsp;&nbsp;&nbsp;</li>
              <li>Science&nbsp;&nbsp;&nbsp;&nbsp;</li>
              <li>Professional&nbsp;&nbsp;&nbsp;&nbsp;</li>
              <li>Business&nbsp;&nbsp;&nbsp;&nbsp;</li>
              <li>API&nbsp;&nbsp;&nbsp;&nbsp;</li>
              <li>Data</li>
            </ul>
          </li>
          <div className="snsCopyright">
            <div className="snsContainer">
              <ul>
                <li>Blog</li>
                <li>FaceBook</li>
                <li>Twitter</li>
                <li>LinkedIn</li>
                <li>Instagram</li>
              </ul>
            </div>
            <div className="copyrightContainer">
              <li>Site design / logo © 2022 Stack Exchange Inc; </li>
              <li>user contributions licensed under CC BY-SA. </li>
              <li>rev 2022.12.21.43127</li>
            </div>
          </div>
        </ul>
      </StyledFooter>
    </>
  );
};

export default Footer;
