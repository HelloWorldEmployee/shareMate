import { compJoinApi } from "./ApiInterceptor";

// 모든 댓글 전체 조회는 사실상 필요 없음.
// export const fetchCompJoin = () => {
//   return compJoinApi.get("");
// };

// compId에 해당하는 댓글 전체 조회
export const fetchCompJoinByCompId = (compId) => {
  return compJoinApi.get(`/${compId}`);
};

// compId, userId에 해당하는 댓글 1개 조회
// -> POST userId 중복확인 및 DEL 삭제
export const fetchCompJoinByCompIdAndUserId = (compId, userId) => {
  return compJoinApi.get(`/${compId}/${userId}`);
};

export const createCompJoin = (data) => {
  console.log(">> createCompetition data:", data);
  return compJoinApi.post("", data);
};

export const deleteCompJoin = (compId, userId) => {
  return compJoinApi.delete(`/${compId}/${userId}`);
};
