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

const Main = ({ data }) => {
  console.log(data);
  const [currentTab, setCurrentTab] = useState('Newest');

  const sortButtonOnClickHandler = (e) => {
    setCurrentTab(e.target.value);
  };

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
      {/* <QuestionBox data={data}></QuestionBox> */}
      <div>
        {/* {console.log(data[0])} */}
        {/* {data.map((el) => console.log(el))} */}
        {data.map((el) => (
          <QuestionBox el={el} key={el.boardId} />
        ))}
      </div>
      <PaginationContainer>
        <Pagination></Pagination>
      </PaginationContainer>
    </MainContainer>
  );
};

export default Main;
