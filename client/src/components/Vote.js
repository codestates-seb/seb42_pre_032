import {
  VotingComponentConatiner,
  VotingComponent,
  VotingButton,
  VotingCounter,
} from '../components/styled/Vote.styled';

const Vote = () => {
  return (
    <VotingComponentConatiner>
      <VotingComponent>
        <VotingButton>
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
        <VotingCounter>3</VotingCounter>
        <VotingButton>
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
