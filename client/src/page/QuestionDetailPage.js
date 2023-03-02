import styled from 'styled-components';
import Sidebar from '../components/Sidebar';
import QuestionDetail from '../components/QuestionDetail';
import { useParams, useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';

const QuestionDetailContainer = styled.div`
  display: flex;
  height: max-content;
`;

const QuestionDetailPage = () => {
  const [data, setData] = useState([]);

  const params = useParams();
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
      const response = await fetch(`http://ec2-3-36-117-214.ap-northeast-2.compute.amazonaws.com:8080/boards/${params.id}`, {
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
  }, [jwt, params.id]);

  return (
    <QuestionDetailContainer>
      <Sidebar></Sidebar>
      <QuestionDetail data={data}></QuestionDetail>
    </QuestionDetailContainer>
  );
};

export default QuestionDetailPage;
