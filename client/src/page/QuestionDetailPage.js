import styled from 'styled-components';
import Sidebar from '../components/Sidebar';
import QuestionDetail from '../components/QuestionDetail';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';

const QuestionDetailContainer = styled.div`
  display: flex;
  height: max-content;
`;

const QuestionDetailPage = () => {
  const [data, setData] = useState([]);
  const params = useParams();
  console.log(params);

  console.log(data);

  useEffect(() => {
    async function fetchdata() {
      const response = await fetch(`/boards/${params.id}`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          Authorization:
            'Bearer eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6WyJBRE1JTiIsIlVTRVIiXSwidXNlcm5hbWUiOiJhZG1pbkBnbWFpbC5jb20iLCJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE2Nzc0OTgyNjIsImV4cCI6MTY3NzU4NDY2Mn0.DX5IPNVkG2yQelMoRHtjQbXV3z6dkMo77Aob-chnrhugrD681D3HzeaVJQGeC-aL',
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
