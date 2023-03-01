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

const QuestionDetail = ({ data }) => {
  const { title, createdAt, answers } = data;
  

  return (
    <DetailContainer>
      <HeaderRow>
        <StyledHeader>{title}</StyledHeader>
        <BlueLinkButton to={'/create'} width={'100px'}>
          Ask&nbsp;Question
        </BlueLinkButton>
      </HeaderRow>
      <HeaderRow>
        <InfoBox>
          <span style={{ color: ' #525960' }}>Asked</span>
          <span style={{ marginLeft: '7px' }}>{createdAt}</span>
        </InfoBox>
      </HeaderRow>
      <PostContentContainer>
        <Question data={data} />
        <span style={{ fontSize: '1.5rem', padding: '2% 0%' }}>
          {answers && answers.length} Answers
        </span>
        <AnswerList answers={answers} />
        <PostAnswer />
      </PostContentContainer>
    </DetailContainer>
  );
};

export default QuestionDetail;
