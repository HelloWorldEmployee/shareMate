import axios from "axios";

const BASE_URL = "http://localhost:8080/api/competition"; // 백엔드 API URL

export const fetchCompetitions = () => {
  return axios.get(BASE_URL);
};

export const fetchCompetitionByCompId = (compId) => {
  return axios.get(`${BASE_URL}/${compId}`);
};

export const createCompetition = (data) => {
  return axios.post(BASE_URL, data);
};

export const updateCompetition = (compId, data) => {
  return axios.put(`${BASE_URL}/${compId}`, data);
};

export const deleteCompetition = (compId) => {
  return axios.delete(`${BASE_URL}/${compId}`);
};
