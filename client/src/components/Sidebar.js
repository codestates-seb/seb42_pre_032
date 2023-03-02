import styled from 'styled-components';
import {Link} from 'react-router-dom';

const SiderbarCom = styled.div`
  background-color: ${({ theme }) => theme.color.common.default_bg};
  border-radius: 5px;
  /* margin-top: 50px; */
  width: 164px;
  height: 100vh;
  display: flex;
  flex-direction: column;
  border-right: 3px solid ${({ theme }) => theme.color.sidebar.focus_bg};
  font-size: ${({ theme }) => theme.size.common.default_font};
  font-weight: 600;
  color: ${({ theme }) => theme.color.common.default_font};
  line-height: 40px;

  .menu_title {
    margin-left: 30px;
    padding-top: 30px;
  }
  .menu_tab {
    letter-spacing: 1px;
    text-indent: 30px;
    margin-left: 10px;
  }

  .menu_tab li:hover {
    background-color: ${({ theme }) => theme.color.sidebar.focus_bg};
    border-right: 3px solid ${({ theme }) => theme.color.header.orange};
    font-size: ${({ theme }) => theme.size.sidebar.focus_font};
    font-weight: bold;
  }

  .menu_question img {
    margin-right: 3px;
    width: 16px;
    height: 16px;
  }
  a {
    text-decoration: none;
    color: ${({ theme }) => theme.color.common.default_font};
  }
`;

const Sidebar = () => {
  return (
    <div>
      <SiderbarCom>
        <div className="Sidebar_Menu">
          <ul>
            <div className="menu_title">
              <div className="menu_home">
                <Link href="/">HOME</Link>
              </div>
              <div className="menu_public">PUBLIC</div>
            </div>
            <div className="menu_tab">
              <li>
                <div className="menu_question">
                  <Link to="/create">
                    <img
                      src="data:image/svg+xml;charset=utf-8,%3Csvg%20ariahidden%3D%22true%22%20width%3D%2219%22%20height%3D%2219%22%20viewBox%3D%220%200%2018%2018%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%3E%3Cpath%20fill%3D%22%23C4C8CC%22%20d%3D%22M9%201C4.64%201%201%204.64%201%209c0%204.36%203.64%208%208%208%204.36%200%208-3.64%208-8%200-4.36-3.64-8-8-8ZM8%2015.32a6.46%206.46%200%200%201-4.3-2.74%206.46%206.46%200%200%201-.93-5.01L7%2011.68v.8c0%20.88.12%201.32%201%201.32v1.52Zm5.72-2c-.2-.66-1-1.32-1.72-1.32h-1v-2c0-.44-.56-1-1-1H6V7h1c.44%200%201-.56%201-1V5h2c.88%200%201.4-.72%201.4-1.6v-.33a6.45%206.45%200%200%201%203.83%204.51%206.45%206.45%200%200%201-1.51%205.73v.01Z%22%3E%3C%2Fpath%3E%3C%2Fsvg%3E"
                      alt="지구본이미지"
                    ></img>
                    <span>Questions</span>
                  </Link>
                </div>
              </li>
              <li>
                <div className="menu_tags">
                  <Link to="/create">
                    <span>Tags</span>
                  </Link>
                </div>
              </li>
            </div>
          </ul>
        </div>
      </SiderbarCom>
    </div>
  );
};

export default Sidebar;
