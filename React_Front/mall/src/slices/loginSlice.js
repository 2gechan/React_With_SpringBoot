import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { loginPost } from "../api/memberApi";
import { getCookie, removeCookie, setCookie } from "../util/cokkieUtil";

const initState = {
  email: "",
};

const loadMemberCookie = () => {
  const memberInfo = getCookie("member");

  return memberInfo;
};

// 비동기 작업을 생성하고 이를 redux 액션으로 처리할 수 있도록 도와주는 유틸리티
export const loginPostAsync = createAsyncThunk("loginPostAsync", (param) =>
  loginPost(param)
);

const loginSlice = createSlice({
  name: "loginslice",
  initialState: loadMemberCookie() || initState,
  reducers: {
    login: (state, action) => {
      console.log("login ........", action);
      console.log(action.payload);
      return { email: action.payload.email };
    },
    logout: () => {
      console.log("logout ........");

      removeCookie("member");
      return { ...initState };
    },
  },
  // extraReducers : createSlice 내에서 비동기 액션에 대한 리듀서를 정의할 때 사용
  extraReducers: (builder) => {
    builder
      .addCase(loginPostAsync.fulfilled, (state, action) => {
        console.log("login success");

        const payload = action.payload;

        console.log(payload);

        if (!payload.error) {
          setCookie("member", JSON.stringify(payload));
        }

        return payload;
      })
      .addCase(loginPostAsync.rejected, (state, action) => {
        console.log("login failed");
      })
      .addCase(loginPostAsync.pending, (state, action) => {
        console.log("pending...");
      });
  },
});

export const { login, logout } = loginSlice.actions;

export default loginSlice.reducer;
