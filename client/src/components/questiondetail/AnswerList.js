import styled from 'styled-components';
import AnswerBox from './AnswerBox';
import Vote from '../Vote';

const AnswerListContainer = styled.div`
  width: 100%;
  display: flex;
  border-bottom: 1px solid #e3e3e3;
`;

const AnswerListInnerContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`;

const AnswerList = () => {
  return (
    <div>
      <AnswerListContainer>
        <Vote></Vote>
        <AnswerListInnerContainer>
          <AnswerBox></AnswerBox>
        </AnswerListInnerContainer>
      </AnswerListContainer>
      <AnswerListContainer>
        <Vote></Vote>
        <AnswerListInnerContainer>
          <AnswerBox></AnswerBox>
        </AnswerListInnerContainer>
      </AnswerListContainer>
    </div>
  );
};

export default AnswerList;
