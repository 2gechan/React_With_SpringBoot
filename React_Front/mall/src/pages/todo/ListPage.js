import { useSearchParams } from "react-router-dom";

const ListPage = () => {
  const [queryParams] = useSearchParams();

  // queryString 값 추출하는 방법 ?page=10&size=30 같은 형식을 추출
  const page = queryParams.get("page") ? parseInt(queryParams.get("page")) : 1;
  const size = queryParams.get("size") ? parseInt(queryParams.get("size")) : 10;

  return (
    <div className="p-4 w-full bg-orange-200 ">
      <div className="text-3xl font-extrabold">
        Todo List Page Component ---- {page} ---- {size}
      </div>
    </div>
  );
};

export default ListPage;
