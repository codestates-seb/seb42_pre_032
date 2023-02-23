import styled from 'styled-components';
import ResultMain from '../components/ResultMain';
import Sidebar from '../components/Sidebar';

const ResultPageContainer = styled.div`
  display: flex;
  height: max-content;
`;
const SearchResultPage = () => {
  return (
    <ResultPageContainer>
      <Sidebar></Sidebar>
      <ResultMain></ResultMain>
    </ResultPageContainer>
  );
};

export default SearchResultPage;
