import axios from "axios";

const addAuthHeader = (config) => {
  console.log("config : ", config);
  const token = localStorage.getItem("token");
  console.log("토큰 : " + token);
  if (token) {
    config.headers["Authorization"] = `Bearer ${token}`;
  }
  return config;
};

const handleError = (error) => {
  if (error.response) {
    console.error("에러 응답:", error.response);
    const status = error.response.status;
    if (status === 401 || status === 403) {
      localStorage.removeItem("token");
      alert("세션이 만료되었거나 권한이 없습니다. 다시 로그인 해주세요!");
      window.location.href = "/login";
    }
  } else {
    console.error("응답이 없는 에러:", error);
  }
  return Promise.reject(error);
};

const studyApi = axios.create({
  baseURL: "http://localhost:8080/api/study",
});

const competitionApi = axios.create({
  baseURL: "http://localhost:8080/api/competition",
});

studyApi.interceptors.request.use(
  //   (config) => {
  //     if (config.method == 'post') 로 개별 가능
  //     return addAuthHeader(config);
  //   },
  addAuthHeader,
  (error) => {
    Promise.reject(error);
  }
);
studyApi.interceptors.response.use((response) => response, handleError);

competitionApi.interceptors.request.use(addAuthHeader, (error) =>
  Promise.reject(error)
);
competitionApi.interceptors.response.use((response) => response, handleError);

export { studyApi, competitionApi };
