// import styled from 'styled-components';
import {
  QuestionContainer,
  QuestionLeftSideContainer,
  QuestionRightSideContainer,
  QuestionInfo,
  QuestionTitle,
  QuestionPreview,
  Tag,
  QuestionFooter,
  Profile,
  ProfileImg,
  ProfileName,
  ProfileLog,
} from '../components/styled/QuestionBox.styled';

const QuestionBox = ({ el }) => {
  console.log(el);
  const { title, body, like, createdAt, writer } = el;
  return (
    <QuestionContainer>
      <QuestionLeftSideContainer>
        <QuestionInfo>{like} votes</QuestionInfo>
        <QuestionInfo>2 answers</QuestionInfo>
        <QuestionInfo>120 views</QuestionInfo>
      </QuestionLeftSideContainer>
      <QuestionRightSideContainer>
        <QuestionTitle>{title}</QuestionTitle>
        <QuestionPreview>{body}</QuestionPreview>
        <QuestionFooter>
          <Tag>reat.js</Tag>
          <Tag>reat.js</Tag>
          <Tag>reat.js</Tag>
          <Profile>
            <ProfileImg></ProfileImg>
            <ProfileName>{writer}</ProfileName>
            <ProfileLog>{createdAt}</ProfileLog>
          </Profile>
        </QuestionFooter>
      </QuestionRightSideContainer>
    </QuestionContainer>
  );
};

export default QuestionBox;
