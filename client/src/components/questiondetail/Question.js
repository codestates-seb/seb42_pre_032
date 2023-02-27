import styled from 'styled-components';
import Vote from '../Vote';
import PostBox from './PostBox';

const PostTopContainer = styled.div`
  width: 100%;
  display: flex;
  border-bottom: 1px solid #e3e3e3;
`;

const PostTopInnerContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`;

const Question = () => {
  return (
    <PostTopContainer>
      <Vote></Vote>
      <PostTopInnerContainer>
        <PostBox></PostBox>
      </PostTopInnerContainer>
    </PostTopContainer>
  );
};

export default Question;
