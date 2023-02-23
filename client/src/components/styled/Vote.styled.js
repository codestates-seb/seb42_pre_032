import styled from 'styled-components';

export const VotingComponentConatiner = styled.div`
  display: flex;
  height: 100%;
  margin-right: 16px;
`;

export const VotingComponent = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  height: max-content;
`;

export const VotingButton = styled.button`
  all: unset;
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const VotingCounter = styled.div`
  display: flex;
  width: 100%;
  height: 28px;
  align-items: center;
  justify-content: center;
  font-size: large;
  color: #6a737c;
`;
