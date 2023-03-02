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
  console.log(answers)

  

  const jwt = localStorage.getItem('user');

  const deletePost = async (jwt) => {
    try {
      const response = await fetch(`http://ec2-3-36-117-214.ap-northeast-2.compute.amazonaws.com:8080/answers/${answers.answerId}`, {
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
      console.log(response);
    } catch (e) {
      throw Error(e);
    }
  };

  const onDelete = async(e) => {
    e.preventDefault();
    try {
      await deletePost(jwt);   
       window.location.reload()
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <div style={{ width: '100%' }}>
      <QuestionTopContainer>
        <ReactQuill value={answers.body} readOnly={true} theme={'bubble'} />
      </QuestionTopContainer>

      <QuestionBottom>
        <AnswerBottomContainer>
          <div>
            <ShareLinker>Share</ShareLinker>

            <DeleteButton onClick={onDelete}>Delete</DeleteButton>
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
