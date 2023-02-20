//헤더빼는법
//헤더컴포넌트에  if (window.location.pathname === '/notfound') return null 추가 부탁드리기
//import styled from 'styled-components';
import notfoundImage from '../image/notfound.png';
import { NotFoundWrapper } from './styled/NotFoundPage.styled';

const NotFoundPage = () => {
  return (
    <div>
      <NotFoundWrapper>
        <img src={notfoundImage} alt="NotFoundPage" />
      </NotFoundWrapper>
    </div>
  );
};

export default NotFoundPage;
