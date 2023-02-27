import QuestionBox from '../components/QuestionBox';
import { BlueLinkButton } from '../components/Buttons';
import {
  StyledHeader,
  HeaderRow,
  MainbarSortButtonContainer,
  SortButton,
  QuestionsNum,
} from '../components/styled/Main.styled';
import { useState } from 'react';
import styled from 'styled-components';

const MainContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin: 0 9%;
  height: max-content;
`;

const PaginationContainer = styled.div`
  margin-left: 20px;
`;

const ResultMain = () => {
  const [currentTab, setCurrentTab] = useState('Newest');

  const sortButtonOnClickHandler = (e) => {
    setCurrentTab(e.target.value);
  };
  return (
    <MainContainer>
      <HeaderRow>
        <StyledHeader>Search Results</StyledHeader>
        <BlueLinkButton to={'/create'} width={'100px'}>
          Ask&nbsp;Question
        </BlueLinkButton>
      </HeaderRow>
      <HeaderRow>
        <QuestionsNum>5&nbsp;questions</QuestionsNum>
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
      <PaginationContainer></PaginationContainer>
    </MainContainer>
  );
};

export default ResultMain;
