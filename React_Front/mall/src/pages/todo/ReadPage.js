import { useParams } from "react-router-dom";

const ReadPage = (props) => {
  const { id } = useParams();

  console.log(id);

  return <div className="text-3xl">Read Page</div>;
};

export default ReadPage;
