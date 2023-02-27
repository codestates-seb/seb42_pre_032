import styled from 'styled-components';
import AnswerEditor from './AnswerEditor';
import { useState } from 'react';

const PostAnswerContaniner = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  height: 300px;
`;

const PostAnswer = () => {
  const [content, setContent] = useState('');
  return (
    <PostAnswerContaniner>
      <AnswerEditor content={content} setContent={setContent}></AnswerEditor>
    </PostAnswerContaniner>
  );
};

export default PostAnswer;
