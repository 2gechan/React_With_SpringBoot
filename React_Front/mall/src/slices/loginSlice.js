import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { loginPost } from "../api/memberApi";

const initState = {
  email: "",
};

export const loginPostAsync = createAsyncThunk("loginPostAsync", (param) =>
  loginPost(param)
);

const loginSlice = createSlice({
  name: "loginslice",
  initialState: initState,
  reducers: {
    login: (state, action) => {
      console.log("login ........", action);
      console.log(action.payload);
      return { email: action.payload.email };
    },
    logout: () => {
      console.log("logout ........");
      return { ...initState };
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(loginPostAsync.fulfilled, (state, action) => {
        console.log("login success");

        const payload = action.payload;

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
