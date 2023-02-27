import Main from '../components/Main';
import styled from 'styled-components';
import Sidebar from '../components/Sidebar';
import { useEffect, useState } from 'react';

const MainPageContainer = styled.div`
  display: flex;
  /* flex-direction: column; */
  height: max-content;
`;

const MainPage = () => {
  //데이터 저장하는 함수
  const [data, setData] = useState([]);

  // console.log(data);

  useEffect(() => {
    async function fetchdata() {
      const response = await fetch('/boards', {
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
