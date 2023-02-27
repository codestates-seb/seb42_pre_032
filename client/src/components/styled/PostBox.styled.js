import styled from 'styled-components';

export const QuestionTopContainer = styled.div`
  min-height: 70px;
  margin-top: 1.5%;
`;

export const QuestionBottom = styled.div`
  display: flex;
`;

export const AnswerBottomContainer = styled.div`
  align-items: flex-start;
  display: flex;
  justify-content: space-between;
  padding-top: 20px;
  width: 100%;
`;

export const ShareLinker = styled.a`
  color: #525960;

  &:hover {
    color: #7f8a95;
    cursor: pointer;
  }
`;

export const DeleteButton = styled.span`
  color: #a00000;
  margin-left: 10px;

  &:hover {
    color: #c50000;
    cursor: pointer;
  }
`;

export const AuthorInfoContainer = styled.div`
  align-items: center;
  background-color: #d9e9f7;
  box-sizing: border-box;
  display: flex;
  padding: 7px;
  min-height: 65px;
  min-width: 200px;
  margin-bottom: 15px;
`;

export const AuthorInfoContainerAnswer = styled.div`
  align-items: center;
  /* background-color: #d9e9f7; */
  box-sizing: border-box;
  display: flex;
  padding: 7px;
  min-height: 65px;
  min-width: 200px;
  margin-bottom: 15px;
`;
export const AuthorProfileImage = styled.img`
  height: 32px;
  width: 32px;
`;

export const AuthorProfileLinker = styled.a`
  all: unset;
  color: #2880d1;
  cursor: pointer;
  font-size: 14px;
  margin-left: 10px;
  &:hover {
    color: #4293f8;
  }
`;
