import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import Editor from '../components/Editor';
import { updatePost, getPost } from '../util/api';

const UpdatePage = () => {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const { id } = useParams();
  const navigate = useNavigate();
  const jwt = localStorage.getItem('user');

  useEffect(() => {
    if (!jwt) {
      navigate(`/log_in`);
    }
  }, [jwt, navigate]);

  useEffect(() => {
    (async () => {
      const { title, body } = await getPost(jwt, id);
      setTitle(title);
      setContent(body);
    })();
  }, [id, jwt]);

  const onTitleChange = (e) => {
    setTitle(e.target.value);
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    try {
      await updatePost(jwt, title, content, id);
      navigate(`/questiondetail/${id}`);
    } catch (e) {
      console.log(e);
    }
  };

  return (
    <>
      <Editor
        title={title}
        content={content}
        setContent={setContent}
        setTitle={onTitleChange}
        onSubmit={onSubmit}
      />
    </>
  );
};

export default UpdatePage;
