const rest_api_key = "70338b5efe3f9a03089448f8f0dc35e0";
const redirect_uri = "http://localhost:3000/member/kakao";

const auth_code_path = `https://kauth.kakao.com/oauth/authorize`;

export const getKakaoLoginLink = () => {
  const kakaoURL = `${auth_code_path}?client_id=${rest_api_key}&redirect_uri=${redirect_uri}&response_type=code`;

  return kakaoURL;
};
