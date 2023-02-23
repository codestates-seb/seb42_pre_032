import styled from 'styled-components';

export const StyledHeader = styled.h1`
  font-size: 2rem;
`;

export const HeaderRow = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
  width: 100%;
`;

export const QuestionsNum = styled.div`
  padding: 1% 0% 0% 0%;
`;

export const MainbarSortButtonContainer = styled.div`
  display: flex;
  height: max-content;
  padding-bottom: 15px;
  width: 200px;
`;

export const SortButton = styled.button`
  border: 1px solid gray;
  border-radius: ${(props) => (props.isLeft ? '5px 0 0 5px' : '0 5px 5px 0')};
  border-width: ${(props) => (props.isLeft ? '1px 0 1px 1px' : '1px')};
  height: 30px;
  margin: 0;
  padding: 5px;
  width: 50%;

  &.selected {
    background-color: #b2b2b2;
    color: #3a3a3a;
  }
  &:hover {
    background-color: #a2a2a2;
    cursor: pointer;
  }
`;
