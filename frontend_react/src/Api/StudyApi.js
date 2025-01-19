import axios from "axios";
import { studyApi } from "./ApiInterceptor";

// const BASE_URL = "http://localhost:8080/api/study";

export const createStudy = (data) => {
  console.log(data);
  // return axios.post(BASE_URL, data);
  return studyApi.post("", data);
};

export const readStudy = (headers) => {
  console.log("채팅방리스트읽기 api!");
  return studyApi.get("", headers);
};

export const updateStudy = (StudyId, data) => {
  console.log("업데이트!!!");
  console.log(StudyId);
  console.log(data);
  return studyApi.put(`/${StudyId}`, data);
};

export const deleteStudy = (StudyId) => {
  console.log("삭제!!!");
  //추후 user_id추가
  return studyApi.delete(`/${StudyId}`);
};
