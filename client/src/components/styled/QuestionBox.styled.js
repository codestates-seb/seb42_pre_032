import styled from 'styled-components';

export const QuestionContainer = styled.div`
  border-top: 1px solid #c5c5c5;
  display: flex;
  height: max-content;
  padding: 2% 0%;
`;

export const QuestionLeftSideContainer = styled.div`
  align-items: end;
  display: flex;
  flex-direction: column;
  margin-right: 20px;
  width: 12%;
`;

export const QuestionRightSideContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 80%;
  padding: 0% 0% 0% 1%;
`;

export const QuestionInfo = styled.p`
  color: ${(props) => (props.isVote ? `black` : `gray`)};
  font-size: 13px;
  margin: 0;
  margin-bottom: 8px;
`;

export const QuestionTitle = styled.a`
  color: #0074cc;
  font-size: large;
  font-weight: 500;
  &:hover {
    color: #49a5f0;
    cursor: pointer;
  }
`;

export const QuestionPreview = styled.div`
  display: flex;
  font-size: 15px;
  margin-top: 10px;
  text-overflow: ellipsis;
`;

export const Tag = styled.div`
  background-color: #e1ecf4;
  border: 1px #e1ecf4;
  border-radius: 5px;
  color: #39739d;
  font-size: small;
  height: 15px;
  margin-right: 5px;
  padding: 5px 8px;
  width: max-content;
`;

export const QuestionFooter = styled.div`
  display: flex;
  justify-content: space-between;
  justify-items: stretch;
  margin-top: 2%;
  width: 100%;
`;

export const Profile = styled.div`
  display: flex;
  height: max-content;

  margin-left: auto;
`;

export const ProfileImg = styled.div`
  display: flex;
  height: 15px;
  padding-bottom: 5px;
  margin-right: 5px;
  width: 15px;
`;

export const ProfileName = styled.a`
  color: #39739d;
  font-size: small;
  padding-top: 2px;
  margin-right: 5px;
  text-decoration: none;
`;

export const ProfileLog = styled.p`
  color: #4c4c4c;
  font-size: small;
  margin: 0;
  padding-top: 2px;
`;
