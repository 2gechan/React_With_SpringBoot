import {
  createSearchParams,
  useNavigate,
  useParams,
  useSearchParams,
} from "react-router-dom";
import ReadComponent from "../../components/todo/ReadComponent";

const ReadPage = (props) => {
  const navigate = useNavigate();
  const { tno } = useParams();
  const [queryParams] = useSearchParams();

  // queryString 값 추출하는 방법 ?page=10&size=30 같은 형식을 추출
  const page = queryParams.get("page") ? parseInt(queryParams.get("page")) : 1;
  const size = queryParams.get("size") ? parseInt(queryParams.get("size")) : 10;

  // 이전 페이지에 존재했던 쿼리스트링을 그대로 사용하기 위해 쿼리스트링 생성
  const queryStr = createSearchParams({ page: page, size: size }).toString();

  const moveToModify = () => {
    // search를 사용하게 되면 이전 페이지에서 사용했던 쿼리스트링을 그대로 사용하게 된다.
    navigate({ pathname: `/todo/modify/${tno}`, search: queryStr });
  };

  const moveToList = () => {
    navigate({ pathname: "/todo/list", search: queryStr });
  };

  return (
    <div className="font-extrabold w-full bg-white mt-6">
      <div className="text-2xl "> Todo Read Page Component {tno} </div>
      <ReadComponent tno={tno}></ReadComponent>
    </div>
  );
};

export default ReadPage;
