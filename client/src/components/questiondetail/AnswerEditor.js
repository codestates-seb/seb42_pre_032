import styled from 'styled-components';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
// import { useRef } from 'react';
import { BlueBigButton } from '../Buttons';

const EditorContainer = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
`;

const AnswerEditor = ({ content = '', setContent, onSubmit }) => {
  // const quillEl = useRef(null);

  // console.log(quillEl.current);

  const onContentChange = (value) => {
    setContent(value);
  };

  console.log(content);

  return (
    <EditorContainer>
      <form onSubmit={onSubmit}>
        <ReactQuill
          style={{ height: '150px', marginBottom: '60px' }}
          // ref={quillEl}
          value={content}
          onChange={onContentChange}
        />
        <BlueBigButton value="Post Your Answer" width="120px"></BlueBigButton>
      </form>
    </EditorContainer>
  );
};

export default AnswerEditor;
