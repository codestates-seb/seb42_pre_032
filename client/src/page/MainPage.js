import Main from '../components/Main';
import styled from 'styled-components';
import Sidebar from '../components/Sidebar';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Paging from '../components/Pagination';

const MainPageContainer = styled.div`
  display: flex;
  /* flex-direction: column; */
  height: max-content;
`;

const MainPage = () => {
  //데이터 저장하는 함수
  const [data, setData] = useState([]);
  const [page, setPage] = useState(1);
  const [pageinfo, setPageinfo] = useState({});
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
      const response = await fetch(`http://ec2-3-36-117-214.ap-northeast-2.compute.amazonaws.com:8080/boards?page=${page}`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          Authorization: jwt,
        },
        //body: JSON.stringify({}),
        credentials: 'include',
      });
      const data = await response.json();
      setData(data.data);
      setPageinfo(data.pageinfo);
    }
    fetchdata();
  }, [page,jwt]);


  return (
  <>
    <MainPageContainer>
      <Sidebar></Sidebar>
      <Main data={data} ></Main>
    </MainPageContainer>
    <Paging page={page} setPage={setPage} itemsCount={pageinfo.size} totalItemCount={pageinfo.totalElements} pageRange={5}/>
  </>
    
  );
};


export default MainPage;
