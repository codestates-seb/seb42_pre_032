import { useRef } from 'react';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import {
  StyledWrraper,
  StyledForm,
  StyledTitle,
  StyledBlock,
} from './styled/Editor.styled';
import StyledInput from '../components/styled/Input.styled';
import { BlueBigButton } from '../components/Buttons';

const Editor = ({
  path,
  title = '',
  setTitle,
  content = '',
  setContent,
  onSubmit,
}) => {
  const quillEl = useRef(null);
  const onContentChange = (value) => {
    setContent(value);
  };

  const onLabelClick = () => {
    quillEl.current.focus();
  };

  return (
    <StyledWrraper>
      <StyledTitle>
        <h2>
          {path === 'create' ? 'Ask a public question' : 'Update a question'}
        </h2>
      </StyledTitle>

      <div className="notice">
        <div className="d-flex w100 ai-center mb16 md:mt0">
          <div className="s-notice s-notice__info p24 w70 lg:w100">
            <h2 className="fs-title fw-normal mb8">Writing a good question</h2>
            <p className="fs-body2 mb0">
              You’re ready to{' '}
              <a href="https://stackoverflow.com/help/how-to-ask">ask</a> a{' '}
              <a href="https://stackoverflow.com/help/on-topic">
                programming-related question
              </a>{' '}
              and this form will help guide you through the process.
            </p>
            <p className="fs-body2 mt0">
              Looking to ask a non-programming question? See{' '}
              <a href="https://stackexchange.com/sites#technology-traffic">
                the topics here
              </a>{' '}
              to find a relevant site.
            </p>
            <h5 className="fw-bold mb8">Steps</h5>
            <ul className="mb0">
              <li>Summarize your problem in a one-line title.</li>
              <li>Describe your problem in more detail.</li>
              <li>Describe what you tried and what you expected to happen.</li>
              <li>
                Add “tags” which help surface your question to members of the
                community.
              </li>
              <li>Review your question and post it to the site.</li>
            </ul>
          </div>
        </div>
      </div>

      <StyledForm onSubmit={onSubmit}>
        <StyledBlock className="title">
          <h3>Title</h3>
          <label htmlFor="title">
            Be specific and imagine you’re asking a question to another person.
          </label>
          <StyledInput
            type="text"
            id="title"
            value={title}
            onChange={setTitle}
            placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
          />
        </StyledBlock>

        <StyledBlock className="content">
          <h3>What are the details of your problem?</h3>
          <label
            role="presentation"
            onKeyDown={() => {}}
            htmlFor="content"
            onClick={onLabelClick}
          >
            Introduce the problem and expand on what you put in the title.
            Minimum 20 characters.
          </label>
          <ReactQuill
            ref={quillEl}
            theme="snow"
            value={content}
            onChange={onContentChange}
          />
        </StyledBlock>
        <div className="buttons">
          <button>Discard draft</button>
          <BlueBigButton value="Submit" className="submit" />
        </div>
      </StyledForm>
    </StyledWrraper>
  );
};

export default Editor;
