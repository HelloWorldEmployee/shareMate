import axios from "axios";

const BASE_URL = "http://localhost:8080/api/study";

export const createStudy = (data) => {
  console.log(data);
  return axios.post(BASE_URL, data);
};

export const readStudy = () => {
  return axios.get(BASE_URL);
};

export const updateStudy = (StudyId, data) => {
  console.log("업데이트!!!");
  console.log(StudyId);
  console.log(data);
  return axios.put(`${BASE_URL}/${StudyId}`, data);
};

export const deleteStudy = (StudyId) => {
  console.log("삭제!!!");
  //추후 user_id추가
  return axios.delete(`${BASE_URL}/${StudyId}`);
};
