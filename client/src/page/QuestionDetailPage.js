import styled from 'styled-components';
import Sidebar from '../components/Sidebar';
import QuestionDetail from '../components/QuestionDetail';

const QuestionDetailContainer = styled.div`
  display: flex;
  height: max-content;
`;

const QuestionDetailPage = () => {
  return (
    <QuestionDetailContainer>
      <Sidebar></Sidebar>
      <QuestionDetail></QuestionDetail>
    </QuestionDetailContainer>
  );
};

export default QuestionDetailPage;
