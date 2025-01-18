import axios from "axios";

const BASE_URL = "http://localhost:8080/api/user";

export const createUser = (data) => {
  console.log("api실행 성공!!");
  return axios.post(BASE_URL, data);
};

export const checkUserId = (userId) => {
  return axios.get(`${BASE_URL}/${userId}`);
};

export const checkLoginUser = (userId, userPassword) => {
  const formData = new FormData();
  formData.append("userId", userId);
  formData.append("userPassword", userPassword);
  return axios.post(`${BASE_URL}/login`, formData);
};

export const readUser = () => {
  return axios.get(BASE_URL);
};

export const updateUser = (userId, data) => {
  return axios.put(`${BASE_URL}/${userId}`, data);
};

export const deleteUser = (userId, userPassword) => {
  return axios.delete(`${BASE_URL}/${userId}/${userPassword}`);
};
