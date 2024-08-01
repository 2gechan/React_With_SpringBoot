import { Cookies } from "react-cookie";

const cookies = new Cookies();

export const setCookie = (name, value, days = 1) => {
  const expires = new Date();
  expires.setUTCDate(expires.getUTCDate() + days); // 보관 기한

  return cookies.set(name, value, { expires: expires, path: "/" }); // key, value, {보관기한, 경로부터 모두 사용 가능한 쿠키}
};

export const getCookie = (name) => {
  return cookies.get(name);
};

export const removeCookie = (name, path = "/") => {
  cookies.remove(name, { path: path });
};
