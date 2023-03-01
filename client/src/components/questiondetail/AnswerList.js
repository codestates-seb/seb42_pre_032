import styled from 'styled-components';
import AnswerBox from './AnswerBox';
import AnswerVote from '../AswerVote';
//import AnswerContainer from './AnswerContainer';

const AnswerListContainer = styled.div`
  width: 100%;
  display: flex;
  border-bottom: 1px solid #e3e3e3;
`;

const AnswerListInnerContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  .answercontainer {
    display: flex;
    width: 100%;
  }
`;

const AnswerList = ({ answers }) => {
  console.log(answers);
  return (
    <div>
      <AnswerListContainer>
        {/* <AnswerContainer></AnswerContainer> */}
        {/* <AnswerVote></AnswerVote> */}
        <AnswerListInnerContainer>
          {answers &&
            answers.map((answers) => (
              <div key={answers.answerId} className="answercontainer">
                <AnswerVote answers={answers}></AnswerVote>
                <AnswerBox answers={answers} />
              </div>
            ))}
        </AnswerListInnerContainer>
      </AnswerListContainer>
    </div>
  );
};

export default AnswerList;
