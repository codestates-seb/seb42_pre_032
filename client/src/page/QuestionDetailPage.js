import styled from 'styled-components';
import Sidebar from '../components/Sidebar';
import QuestionDetail from '../components/QuestionDetail';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

const QuestionDetailContainer = styled.div`
  display: flex;
  height: max-content;
`;

const QuestionDetailPage = () => {
  const [data, setData] = useState([]);
  const params = useParams();
  console.log(params);
  const navigate = useNavigate();

  console.log(data);
  const jwt = localStorage.getItem('user');

  useEffect(() => {
    if (!jwt) {
      console.log(jwt);
      navigate('/log_in');
    }
  }, [jwt, navigate]);

  useEffect(() => {
    async function fetchdata() {
      const response = await fetch(`/boards/${params.id}`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          Authorization: jwt,
        },
        //body: JSON.stringify({}),
        credentials: 'include',
      });
      const data = await response.json();
      setData(data);
    }
    fetchdata();
  }, []);

  return (
    <QuestionDetailContainer>
      <Sidebar></Sidebar>
      <QuestionDetail data={data}></QuestionDetail>
    </QuestionDetailContainer>
  );
};

export default QuestionDetailPage;
