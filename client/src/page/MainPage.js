import Main from '../components/Main';
import styled from 'styled-components';
import Sidebar from '../components/Sidebar';

const MainPageContainer = styled.div`
  display: flex;
  /* flex-direction: column; */
  height: max-content;
`;

const MainPage = () => {
  return (
    <MainPageContainer>
      <Sidebar></Sidebar>
      <Main></Main>
    </MainPageContainer>
  );
};

export default MainPage;
