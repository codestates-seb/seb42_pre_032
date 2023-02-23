import styled from 'styled-components';
import { StyledHeader, HeaderRow } from '../components/styled/Main.styled';
import { BlueLinkButton } from '../components/Buttons';
import Question from '../components/questiondetail/Question';
import AnswerList from '../components/questiondetail/AnswerList';
import PostAnswer from '../components/questiondetail/PostAnswer';

const DetailContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin: 0 9%;
  height: max-content;
  padding-top: 1%;
`;

const InfoBox = styled.div`
  display: flex;
  width: 100%;
  height: max-content;
  padding: 1% 0% 1.5% 0%;
  border-bottom: 1px solid #e3e3e3;
`;

const PostContentContainer = styled.div`
  display: flex;
  justify-content: space-between;
  flex-direction: column;
  width: calc(100% - 300px);
  padding-top: 16px;
  box-sizing: border-box;
`;

const QuestionDetail = () => {
  return (
    <DetailContainer>
      <HeaderRow>
        <StyledHeader>
          I cant reall understan why my code loops useContext
        </StyledHeader>
        <BlueLinkButton to={'/create'} width={'100px'}>
          Ask&nbsp;Question
        </BlueLinkButton>
      </HeaderRow>
      <HeaderRow>
        <InfoBox>
          <span style={{ color: ' #525960' }}>Asked</span>
          <span style={{ marginLeft: '7px' }}>today</span>
        </InfoBox>
      </HeaderRow>
      <PostContentContainer>
        <Question />
        <span style={{ fontSize: '1.5rem', padding: '2% 0%' }}>2Answers</span>
        <AnswerList />
        <PostAnswer />
      </PostContentContainer>
    </DetailContainer>
  );
};

export default QuestionDetail;
