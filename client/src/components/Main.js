import QuestionBox from '../components/QuestionBox';
import { BlueLinkButton } from '../components/Buttons';
import {
  StyledHeader,
  HeaderRow,
  MainbarSortButtonContainer,
  SortButton,
  QuestionsNum,
} from '../components/styled/Main.styled';
import { useEffect, useState } from 'react';
import styled from 'styled-components';
import Pagination from '../components/Pagination/Pagination';

const MainContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin: 0 9%;
  height: max-content;
`;

const PaginationContainer = styled.div`
  margin-left: 20px;
  height: 50px;
  /* margin: 5px 0px 0px 20px; */
`;

const Main = () => {
  const [currentTab, setCurrentTab] = useState('Newest');

  const sortButtonOnClickHandler = (e) => {
    setCurrentTab(e.target.value);
  };

  useEffect(() => {
    (async () => {
      try {
        const responce = await fetch('/login', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },

          body: JSON.stringify({
            // nickName: '아이언맨',
            // email: 'IronMan@marvel.com',
            // password: 'ironman',
            // about_Me: 'I am IronMan',
            username: 'IronMan@marvel.com',
            password: 'ironman',
          }),
          credentials: 'include',
        });
        // const responce = await fetch(
        //   'http://ec2-3-36-117-214.ap-northeast-2.compute.amazonaws.com:8080/boards',
        //   {
        //     method: 'GET',
        //     Authorization:
        //       'Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlcyI6WyJBRE1JTiIsIlVTRVIiXSwidXNlcm5hbWUiOiJhZG1pbkBnbWFpbC5jb20iLCJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE2NzY4MTc3MjAsImV4cCI6MTY3NjgxOTUyMH0.YA6Ll0_rIi9blJKDJZuz1eoVWZA6SSbzgOj4T_TzLXzM5yCgYRB-22v_cnPVvrs1i0hnnhOTMaILQsBg0WjlFQ',
        //     credentials: 'include',
        //   }
        // );
        console.log(responce.headers.get('authorization'));
        if (!responce.ok) {
          console.log(responce.status);
        }
        const data = await responce.json();
        console.log(data);
      } catch (e) {
        console.log(e);
      }
    })();
  }, []);

  return (
    <MainContainer>
      <HeaderRow>
        <StyledHeader>Top Questions</StyledHeader>
        <BlueLinkButton to={'/create'} width={'100px'}>
          Ask&nbsp;Question
        </BlueLinkButton>
      </HeaderRow>
      <HeaderRow>
        <QuestionsNum>23&nbsp;questions</QuestionsNum>
        <MainbarSortButtonContainer>
          <SortButton
            className={currentTab === 'Newest' ? 'selected' : ''}
            isLeft={true}
            onClick={sortButtonOnClickHandler}
            value={'Newest'}
          >
            Newest
          </SortButton>
          <SortButton
            className={currentTab === 'Unanswered' ? 'selected' : ''}
            isLeft={false}
            onClick={sortButtonOnClickHandler}
            value={'Unanswered'}
          >
            Unanswered
          </SortButton>
        </MainbarSortButtonContainer>
      </HeaderRow>
      <QuestionBox></QuestionBox>
      <QuestionBox></QuestionBox>
      <QuestionBox></QuestionBox>
      <QuestionBox></QuestionBox>
      <QuestionBox></QuestionBox>
      <QuestionBox></QuestionBox>
      <PaginationContainer>
        <Pagination></Pagination>
      </PaginationContainer>
    </MainContainer>
  );
};

export default Main;
