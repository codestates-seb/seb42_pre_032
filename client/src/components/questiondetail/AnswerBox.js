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

const AnswerBox = ({ answers }) => {
  console.log(answers);
  return (
    <div>
      <QuestionTopContainer>{answers.body}</QuestionTopContainer>
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
            <AuthorProfileLinker>{answers.title}</AuthorProfileLinker>
          </AuthorInfoContainerAnswer>
        </AnswerBottomContainer>
      </QuestionBottom>
    </div>
  );
};

export default AnswerBox;
