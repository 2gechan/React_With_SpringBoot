import {
  createSearchParams,
  useNavigate,
  useParams,
  useSearchParams,
} from "react-router-dom";

const ReadPage = (props) => {
  const navigate = useNavigate();
  const { id } = useParams();
  const [queryParams] = useSearchParams();

  // queryString 값 추출하는 방법 ?page=10&size=30 같은 형식을 추출
  const page = queryParams.get("page") ? parseInt(queryParams.get("page")) : 1;
  const size = queryParams.get("size") ? parseInt(queryParams.get("size")) : 10;

  // 이전 페이지에 존재했던 쿼리스트링을 그대로 사용하기 위해 쿼리스트링 생성
  const queryStr = createSearchParams({ page: page, size: size }).toString();

  const moveToModify = () => {
    // search를 사용하게 되면 이전 페이지에서 사용했던 쿼리스트링을 그대로 사용하게 된다.
    navigate({ pathname: `/todo/modify/${id}`, search: queryStr });
  };

  const moveToList = () => {
    navigate({ pathname: "/todo/list", search: queryStr });
  };

  return (
    <div className="text-3xl">
      Read Page {id}
      <div>
        <button onClick={moveToModify}>Test modify</button>
        <button onClick={moveToList}>Test List</button>
      </div>
    </div>
  );
};

export default ReadPage;
