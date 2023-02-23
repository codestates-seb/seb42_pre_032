import styled from 'styled-components';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import { useRef } from 'react';
import { BlueBigButton } from '../Buttons';

const EditorContainer = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
`;

const AnswerEditor = (path, content = '', setContent) => {
  const quillEl = useRef(null);

  const onContentChange = (value) => {
    setContent(value);
  };

  return (
    <EditorContainer>
      <ReactQuill
        style={{ height: '150px', marginBottom: '60px' }}
        ref={quillEl}
        value={content}
        onChange={onContentChange}
      />
      <BlueBigButton value="Post Your Answer" width="120px"></BlueBigButton>
    </EditorContainer>
  );
};

export default AnswerEditor;
