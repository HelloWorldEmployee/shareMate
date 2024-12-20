import axios from "axios";

const BASE_URL = "http://localhost:8080/api/user";

export const createUser = (data1) => {
  return axios.post(BASE_URL, data1);
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
