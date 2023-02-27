import Editor from '../components/Editor';
import {
  useLocation,
  // , useNavigate
} from 'react-router-dom';
import {
  // useEffect,
  useState,
} from 'react';
import useJWT from '../hook/useJWT';
import { createPost } from '../util/api';

const CreatePage = () => {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const path = useLocation().pathname.slice(1);
  // const navigate = useNavigate();
  const jwt = useJWT();

  // useEffect(() => {
  //   if (!jwt) {
  //     console.log(jwt);
  //     navigate(`/log_in`);
  //   }
  // }, [jwt, navigate]);

  const onTitleChange = (e) => {
    setTitle(e.target.value);
  };

  const onSubmit = (e) => {
    e.preventDefault();
    try {
      createPost(jwt, title, content);
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <>
      <Editor
        path={path}
        title={title}
        content={content}
        setContent={setContent}
        setTitle={onTitleChange}
        onSubmit={onSubmit}
      />
    </>
  );
};

export default CreatePage;
