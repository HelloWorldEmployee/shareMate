import { competitionApi } from "./ApiInterceptor";

// const BASE_URL = "http://localhost:8080/api/competition"; // 백엔드 API URL

export const fetchCompetitions = () => {
  return competitionApi.get("");
};

export const fetchCompetitionByCompId = (compId) => {
  return competitionApi.get(`/${compId}`);
};

export const createCompetition = (data) => {
  console.log(">> createCompetition data:", data);
  return competitionApi.post("", data);
};

export const updateCompetition = (compId, data) => {
  return competitionApi.put(`/${compId}`, data);
};

export const deleteCompetition = (compId) => {
  return competitionApi.delete(`/${compId}`);
};
