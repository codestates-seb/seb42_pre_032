import './Paging.css';
import Pagination from "react-js-pagination";

const Paging = ({page, itemsCount, totalItemCount, pageRange, setPage}) => {
  

  const handlePageChange = (page) => {
    setPage(page);
  };

  return (
    <Pagination
      activePage={page}
      itemsCountPerPage={itemsCount}
      totalItemsCount={totalItemCount}
      pageRangeDisplayed={pageRange}
      prevPageText={"‹"}
      nextPageText={"›"}
      onChange={handlePageChange}
    />
  );
};

export default Paging;