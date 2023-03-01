import {
  QuestionTopContainer,
  QuestionBottom,
  AnswerBottomContainer,
  ShareLinker,
  DeleteButton,
  AuthorInfoContainer,
  AuthorProfileImage,
  AuthorProfileLinker,
} from '../../components/styled/PostBox.styled';
import { useParams, useNavigate } from 'react-router-dom';
import ReactQuill from 'react-quill';

const PostBox = ({ data }) => {
  const navigate = useNavigate();

  const params = useParams();

  console.log(params);
  console.log(data.body);

  return (
    <div>
      <QuestionTopContainer>
        <ReactQuill value={data.body} readOnly={true} theme={'bubble'} />
      </QuestionTopContainer>
      <QuestionBottom>
        <AnswerBottomContainer>
          <div>
            <div>
              <ShareLinker onClick={() => navigate(`/update/${params.id}`)}>
                update
              </ShareLinker>

              <DeleteButton>Delete</DeleteButton>
            </div>
          </div>
          <AuthorInfoContainer>
            <AuthorProfileImage />
            <AuthorProfileLinker>{data.writer}</AuthorProfileLinker>
          </AuthorInfoContainer>
        </AnswerBottomContainer>
      </QuestionBottom>
    </div>
  );
};

export default PostBox;
