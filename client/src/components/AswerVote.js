import {
  VotingComponentConatiner,
  VotingComponent,
  VotingButton,
  VotingCounter,
} from '../components/styled/Vote.styled';
// import { useParams } from 'react-router-dom';

const Vote = ({ answers }) => {
  // const param = useParams()
  const jwt = localStorage.getItem('user');
  console.log(answers)

  const postLike = async (jwt) => {
    try {
      const response = await fetch(`/answers/${answers.answerId}/like`, {
        method: 'POST',
        headers: {
          Authorization: jwt,
          'Content-Type': 'application/json',
        },
        // body: JSON.stringify({ title: 'aaa', body: content }),
        credentials: 'include',
      });
      if (!response.ok) {
        throw Error(response.status);
      }
      console.log(response);
    } catch (e) {
      throw Error(e);
    }
  };

  const onLike = (e) => {
    e.preventDefault();
      try {
        postLike(jwt)
        window.location.reload()
      } catch (e) {
        console.log(e);
      }
  }

  const postDislike = async (jwt) => {
    try {
      const response = await fetch(`/answers/${answers.answerId}/dislike`, {
        method: 'POST',
        headers: {
          Authorization: jwt,
          'Content-Type': 'application/json',
        },
        // body: JSON.stringify({ title: 'aaa', body: content }),
        credentials: 'include',
      });
      if (!response.ok) {
        throw Error(response.status);
      }
      console.log(response);
    } catch (e) {
      throw Error(e);
    }
  };

  const onDisLike = (e) => {
    e.preventDefault();
      try {
        postDislike(jwt)
        window.location.reload()
      } catch (e) {
        console.log(e);
      }
  }
  return (
    <VotingComponentConatiner>
      <VotingComponent>
        <VotingButton onClick={onLike}>
          <svg
            width="36"
            height="36"
            viewBox="0 0 36 36"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path d="M2 25H34L18 9L2 25Z" fill="#BABFC3" />
          </svg>
        </VotingButton>
        <VotingCounter>{answers.like}</VotingCounter>
        <VotingButton onClick={onDisLike}>
          <svg
            width="36"
            height="36"
            viewBox="0 0 36 36"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path d="M2 11H34L18 27L2 11Z" fill="#BABFC3" />
          </svg>
        </VotingButton>
      </VotingComponent>
    </VotingComponentConatiner>
  );
};

export default Vote;
