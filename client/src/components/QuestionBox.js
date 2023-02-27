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

const QuestionBox = () => {
  return (
    <QuestionContainer>
      <QuestionLeftSideContainer>
        <QuestionInfo>2 votes</QuestionInfo>
        <QuestionInfo>2 answers</QuestionInfo>
        <QuestionInfo>120 views</QuestionInfo>
      </QuestionLeftSideContainer>
      <QuestionRightSideContainer>
        <QuestionTitle>
          Programmatically navigate using React router
        </QuestionTitle>
        <QuestionPreview>
          With react-router I can use the Link element to create links which are
          natively handled by react router. I see internally it calls
          this.context.transitionTo(...)
        </QuestionPreview>
        <QuestionFooter>
          <Tag>reat.js</Tag>
          <Tag>reat.js</Tag>
          <Tag>reat.js</Tag>
          <Profile>
            <ProfileImg></ProfileImg>
            <ProfileName>서정희</ProfileName>
            <ProfileLog>May 7, 2020 at 8:12</ProfileLog>
          </Profile>
        </QuestionFooter>
      </QuestionRightSideContainer>
    </QuestionContainer>
  );
};

export default QuestionBox;
