import axios from "axios";

const BASE_URL = "http://localhost:8080/api/study";

export const createStudy = (data) => {
  return axios.post(BASE_URL, data);
};

export const readStudy = () => {
  return axios.get(BASE_URL);
};

export const updateStudy = (StudyId, data) => {
  return axios.put(BASE_URL, StudyId, data);
};

export const deleteStudy = (StudyId, UserId) => {
  console.log("삭제!!!");

  return axios.delete(BASE_URL, StudyId, UserId);
};
