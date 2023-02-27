import Editor from '../components/Editor';
import { useLocation } from 'react-router-dom';
import { useState } from 'react';
import useJWT from '../hook/useJWT';

const CreatePage = () => {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const path = useLocation().pathname.slice(1);
  const jwt = useJWT();
  console.log(jwt);

  const onTitleChange = (e) => {
    setTitle(e.target.value);
    console.log(e.target.value);
  };

  return (
    <>
      <Editor
        path={path}
        title={title}
        content={content}
        setContent={setContent}
        setTitle={onTitleChange}
      />
    </>
  );
};

export default CreatePage;
