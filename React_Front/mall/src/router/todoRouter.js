import { Suspense, lazy } from "react";
import { Navigate } from "react-router-dom";

const Loading = <div className="bg-red-500">Loading...</div>;

const TodoList = lazy(() => import("../pages/todo/ListPage"));

const TodoRead = lazy(() => import("../pages/todo/ReadPage"));

const todoRouter = () => {
  return [
    {
      path: "list",
      element: (
        <Suspense fallback={Loading}>
          <TodoList />
        </Suspense>
      ),
    },
    {
      path: "",
      element: <Navigate replace={true} to={"list"} />,
    },
    {
      path: "read/:id",
      element: (
        <Suspense fallback={Loading}>
          <TodoRead />
        </Suspense>
      ),
    },
  ];
};

export default todoRouter;
