import Main from '../components/Main';
import styled from 'styled-components';
import Sidebar from '../components/Sidebar';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

const MainPageContainer = styled.div`
  display: flex;
  /* flex-direction: column; */
  height: max-content;
`;

const MainPage = () => {
  //데이터 저장하는 함수
  const [data, setData] = useState([]);
  const navigate = useNavigate();

  // console.log(data);
  const jwt = localStorage.getItem('user');

  useEffect(() => {
    if (!jwt) {
      console.log(jwt);
      navigate('/log_in');
    }
  }, [jwt, navigate]);

  useEffect(() => {
    async function fetchdata() {
      const response = await fetch('/boards', {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          Authorization: jwt,
        },
        //body: JSON.stringify({}),
        credentials: 'include',
      });
      const data = await response.json();
      // console.log(data.data[0].title);
      setData(data.data);
    }
    fetchdata();
  }, []);

  return (
    <MainPageContainer>
      <Sidebar></Sidebar>
      <Main data={data}></Main>
    </MainPageContainer>
  );
};

export default MainPage;
