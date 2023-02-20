import { ThemeProvider } from 'styled-components';
import GlobalStyle from './components/styled/global.styled';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Main from './components/Main';
import UpdatePage from './page/UpdatePage';
import NotFoundPage from './page/NotFoundPage';
import QuestionDetailPage from './page/QuestionDetailPage';
import SignUpPage from './page/SignUpPage';
import LoginPage from './page/LoginPage';
import CreatePage from './page/CreatePage';
import Header from './components/Header';
import Footer from './components/Footer';
import SearchResultPage from './page/SearchResultPage';

function App() {
  const theme = {
    color: {
      header: {
        orange: '#F48225',
        login_btn_bg: '#E1ECF4',
        bg: '#F8F9F9',
      },
      sidebar: {
        focus_bg: '#F1F2F3',
      },
      main: {
        answer_bg: '#2F6F44',
        button_focus_bg: '#0A95FF',
        views_font: '#922024 ',
        tag_bg: '#E1ECF4',
      },
      answer: {
        bg_tag: '#E1ECF4',
        pagenation_btn_bg: '#F48225',
      },
      sign_up: {
        signup_bg: '#F1F2F3',
        pwd_bottom_text: '#6A737C',
        bg_font: '#949BA1',
      },
      common: {
        blue_btn_bg: '#0A95FF',
        blue_btn_font: '#39739D',
        font_a: '#F1F2F3',
        default_bg: '#FFFFF',
        default_font: '#797E84',
      },
    },
    size: {
      sidebar: {
        focus_font: '14px',
      },
      main: {
        main_title: '24px',
        answer_title: '16px',
      },
      answer: {
        title_font: '28px',
        recommend_font: '20px',
        answers_font: '16px',
        pagenation_btn_font: '15px',
      },
      login: {
        label: '16px',
      },
      sign_up: {
        signup_font: '16px',
      },
      input: {
        title: '17px',
        description: '10px',
        place_holder: '13px',
      },
      common: {
        default_font: '13px',
      },
    },
  };

  return (
    <ThemeProvider theme={theme}>
      <GlobalStyle />
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path="/" element={<Main />} />
          <Route path="/searchreasult" element={<SearchResultPage />} />
          <Route path="/questiondetail">
            <Route path=":id/:title" element={<QuestionDetailPage />} />
          </Route>
          <Route path="/update/:id" element={<UpdatePage />} />
          <Route path="/create" element={<CreatePage />} />
          <Route path="/signup" element={<SignUpPage />} />
          <Route path="login" element={<LoginPage />} />
          <Route path="*" element={<NotFoundPage />} />
        </Routes>
        <Footer />
      </BrowserRouter>
    </ThemeProvider>
  );
}

export default App;
