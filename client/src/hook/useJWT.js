import { useEffect, useState } from 'react';

const useJWT = () => {
  const [jwt, setJWT] = useState('');
  useEffect(() => {
    setJWT(localStorage.getItem('user'));
  }, []);

  return jwt;
};

export default useJWT;
