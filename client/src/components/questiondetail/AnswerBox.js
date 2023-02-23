import {
  QuestionTopContainer,
  QuestionBottom,
  AnswerBottomContainer,
  ShareLinker,
  DeleteButton,
  AuthorInfoContainerAnswer,
  AuthorProfileImage,
  AuthorProfileLinker,
} from '../../components/styled/PostBox.styled';

const AnswerBox = () => {
  return (
    <div>
      <QuestionTopContainer>
        {
          'Ok so my code was originally written using just props but then my teacher asked me to remove those props and use useContext instead. Eveything was fine until i try to load the code. It just loops and bugs my browser. Could you guys gimme a clue on what it might be?'
        }
      </QuestionTopContainer>
      <QuestionBottom>
        <AnswerBottomContainer>
          <div>
            <div>
              <ShareLinker>Share</ShareLinker>

              <DeleteButton>Delete</DeleteButton>
            </div>
          </div>
          <AuthorInfoContainerAnswer>
            <AuthorProfileImage />
            <AuthorProfileLinker>nickname</AuthorProfileLinker>
          </AuthorInfoContainerAnswer>
        </AnswerBottomContainer>
      </QuestionBottom>
    </div>
  );
};

export default AnswerBox;
