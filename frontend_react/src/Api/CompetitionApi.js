import axios from "axios";

const BASE_URL = "http://localhost:8080/api/competition"; // 백엔드 API URL

//JWT 토큰을 가져오는 함수
const getToken = () => {
  return localStorage.getItem("jwt"); //로컬 스토리지에서 JWT 토큰 가져오기
};

export const fetchCompetitions = () => {
  return axios.get(BASE_URL);
  //, {
  //headers: {
  //  Authorization: `Bearer ${getToken()}`, // JWT 토큰을 헤더에 추가
  //},
  //});
};

export const fetchCompetitionByCompId = (compId) => {
  return axios.get(`${BASE_URL}/${compId}`);
  //, {
  //headers: {
  //  Authorization: `Bearer ${getToken()}`, // JWT 토큰을 헤더에 추가
  //},
  //});
};

export const createCompetition = (data) => {
  return axios.post(BASE_URL, data);
  //, {
  //headers: {
  //  Authorization: `Bearer ${getToken()}`,
  //},
  //});
};

export const updateCompetition = (compId, data) => {
  return axios.put(`${BASE_URL}/${compId}`, data);
  //, {
  //headers: {
  //  Authorization: `Bearer ${getToken()}`,
  //},
  //});
};

export const deleteCompetition = (compId) => {
  return axios.delete(`${BASE_URL}/${compId}`);
  //, {
  //headers: {
  //  Authorization: `Bearer ${getToken()}`,
  //},
  //});
};
