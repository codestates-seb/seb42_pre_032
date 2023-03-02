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

  const param = useParams();
  //  const navigate = useNavigate();
  const jwt = localStorage.getItem('user');

  const deletePost = async (jwt) => {
    try {
      const response = await fetch(`http://ec2-3-36-117-214.ap-northeast-2.compute.amazonaws.com:8080/boards/${param.id}`, {
        method: 'DELETE',
        headers: {
          Authorization: jwt,
          'Content-Type': 'application/json',
        },
        // body: JSON.stringify({ title: 'aaa', body: content }),
        credentials: 'include',
      });
      if (!response.ok) {
        throw Error(response.status);
      }
      
    } catch (e) {
      throw Error(e);
    }
  };

  const onDelete = async(e) => {
    e.preventDefault();
    try {
      await deletePost(jwt);   
       navigate(`/`);
    } catch (e) {
      console.log(e);
    }
  };





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

              <DeleteButton onClick={onDelete}>Delete</DeleteButton>
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
