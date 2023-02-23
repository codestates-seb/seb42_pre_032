import styled from 'styled-components';

const PageButton = () => {
  const Button = styled.button`
    background-color: white;
    border: 1px solid #c3c3c3;
    border-radius: 2px;
    color: #3b4045;
    margin: 0 2px;
    padding: 4px 7px;

    &.current {
      background-color: #f69000;
      border: 0;
      color: white;
    }

    &:hover {
      cursor: pointer;
      background-color: #cdcdcd;
    }
  `;
  return (
    <div>
      <Button>1</Button>
    </div>
  );
};

export default PageButton;
