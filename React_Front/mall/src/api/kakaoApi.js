import axios from "axios";

const rest_api_key = "70338b5efe3f9a03089448f8f0dc35e0";
const redirect_uri = "http://localhost:3000/member/kakao";

const auth_code_path = `https://kauth.kakao.com/oauth/authorize`;

const access_token_uri = "https://kauth.kakao.com/oauth/token";

export const getKakaoLoginLink = () => {
  const kakaoURL = `${auth_code_path}?client_id=${rest_api_key}&redirect_uri=${redirect_uri}&response_type=code`;

  return kakaoURL;
};

export const getAccessToken = async (authCode) => {
  const header = {
    headers: {
      "Content-Type": "application/x-www-form-urlencoded;charset=utf-8",
    },
  };

  const params = {
    grant_type: "authorization_code",
    client_id: rest_api_key,
    redirect_uri: redirect_uri,
    code: authCode,
  };

  const res = await axios.post(access_token_uri, params, header);

  const accessToken = res.data.access_token;

  return accessToken;
};
