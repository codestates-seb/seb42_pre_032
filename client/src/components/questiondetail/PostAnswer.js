import styled from 'styled-components';
import AnswerEditor from './AnswerEditor';
import { useState } from 'react';
import {   useParams } from 'react-router-dom';

const PostAnswerContaniner = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  height: 300px;
`;

const PostAnswer = () => {
  const [content, setContent] = useState('');
  const param = useParams();
  //const navigate = useNavigate();
  const jwt = localStorage.getItem('user');

  const createAnswer = async (jwt, content) => {
    try {
      const response = await fetch(`/answers/${param.id}`, {
        method: 'POST',
        headers: {
          Authorization: jwt,
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ title: 'aaa', body: content }),
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

  const onSubmit =  (e) => {
    e.preventDefault();
    try {
       createAnswer(jwt, content);
      // setContent("")
      //navigate(`/questiondetail/${param.id}`);
      window.location.reload()
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <PostAnswerContaniner>
      <AnswerEditor
        content={content}
        setContent={setContent}
        onSubmit={onSubmit}
      ></AnswerEditor>
    </PostAnswerContaniner>
  );
};

export default PostAnswer;
