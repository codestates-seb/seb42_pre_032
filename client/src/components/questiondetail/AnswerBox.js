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
import ReactQuill from 'react-quill';

const AnswerBox = ({ answers }) => {
  console.log(answers);
  return (
    <div style={{ width: '100%' }}>
      <QuestionTopContainer>
        <ReactQuill value={answers.body} readOnly={true} theme={'bubble'} />
      </QuestionTopContainer>

      <QuestionBottom>
        <AnswerBottomContainer>
          <div>
            <ShareLinker>Share</ShareLinker>

            <DeleteButton>Delete</DeleteButton>
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
