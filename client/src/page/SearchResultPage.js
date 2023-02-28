import styled from 'styled-components';
import ResultMain from '../components/ResultMain';
import Sidebar from '../components/Sidebar';
import { useSearchParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { getSearchRsult } from '../util/api';
import Paging from '../components/Pagination';

const ResultPageContainer = styled.div`
  display: flex;
  height: max-content;
`;
const SearchResultPage = () => {
  const [searchResult, setSearchResult] = useState([]);
  const [resultNum, setresultNum] = useState(0);
  const jwt = localStorage.getItem('user');
  const [searchParams] = useSearchParams();
  const [page, setPage] = useState(1);
  const [pageinfo, setPageinfo] = useState({});

  useEffect(() => {
    (async () => {
      try {
        const data = await getSearchRsult(
          jwt,
          searchParams.get('q'),
          searchParams.get('page')
        );
        setSearchResult(data.data);
        setresultNum(data.pageinfo.totalElements);
        setPageinfo(data.pageinfo);
      } catch (e) {
        console.log(e);
      }
    })();
  }, [jwt, searchParams]);

  return (
    <>
      <ResultPageContainer>
      <Sidebar></Sidebar>
      <ResultMain
        searchResult={searchResult}
        resultNum={resultNum}
      ></ResultMain>
      </ResultPageContainer>
      <Paging page={page} setPage={setPage} itemsCount={pageinfo.size} totalItemCount={pageinfo.totalElements} pageRange={5}/>
    </>
    
  );
};

export default SearchResultPage;
