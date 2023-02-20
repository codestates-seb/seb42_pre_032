import styled from 'styled-components';
import React, { useState, useEffect } from 'react'; // eslint-disable-line no-unused-vars

const LoadingCom = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  width: 100%;
  height: calc(100vh - 50px);
`;

const Loading = () => {
  const [loading, setLoading] = useState(true);
  useEffect(() => {
    setTimeout(() => {
      setLoading(false);
    }, 1000);
  }, []);

  return (
    <div>
      <LoadingCom>
        {loading ? (
          <img
            src="https://upload.wikimedia.org/wikipedia/commons/b/b1/Loading_icon.gif?20151024034921"
            alt="loading icon"
            style={{ maxWidth: '100%' }}
          />
        ) : (
          <p></p>
        )}
      </LoadingCom>
    </div>
  );
};

export default Loading;
