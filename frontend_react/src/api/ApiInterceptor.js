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

const handle401Error = (error) => {
  if (error.response && error.response.state === 401) {
    localStorage.removeItem("token");
    alert("세션이 만료되었습니다. 다시 로그인 해주세요!");
    window.location.href = "/login";
  }
  return Promise.reject(error);
};

const studyApi = axios.create({
  baseURL: "http://localhost:8080/api/study",
});

const competitionApi = axios.create({
  baseURL: "http://localhost:8080/api/competition",
});

const compJoinApi = axios.create({
  baseURL: "http://localhost:8080/api/compJoin",
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
studyApi.interceptors.response.use((response) => response, handle401Error);

competitionApi.interceptors.request.use(addAuthHeader, (error) =>
  Promise.reject(error)
);
competitionApi.interceptors.response.use(
  (response) => response,
  handle401Error
);

compJoinApi.interceptors.request.use(addAuthHeader, (error) =>
  Promise.reject(error)
);
compJoinApi.interceptors.response.use((response) => response, handle401Error);

export { studyApi, competitionApi, compJoinApi };
