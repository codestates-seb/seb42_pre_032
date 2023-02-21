import { useState } from 'react';
// import { useParams } from 'react-router-dom';
import Editor from '../components/Editor';

const UpdatePage = () => {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  // const { id } = useParams();

  const onTitleChange = (e) => {
    setTitle(e.target.value);
  };

  return (
    <>
      <Editor
        title={title}
        content={content}
        setContent={setContent}
        setTitle={onTitleChange}
      />
    </>
  );
};

export default UpdatePage;
