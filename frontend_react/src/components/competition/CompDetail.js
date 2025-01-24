import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom"; // useParams 훅 import
import {
  deleteCompetition,
  fetchCompetitionByCompId,
} from "../../api/CompetitionApi";
import { Table } from "react-bootstrap";
import { useHistory } from "react-router-dom/cjs/react-router-dom";
//import { fetchCompJoinByCompIdAndUserId } from "../../api/CompJoinApi";
//import jwtDecode from "jwt-decode"; // jwt-decode 패키지 설치 필요

const CompDetail = () => {
  const history = useHistory();
  const { compId } = useParams(); // URL 파라미터에서 compId 가져오기
  const [competition, setCompetition] = useState(null);

  useEffect(() => {
    const loadCompetition = async () => {
      try {
        const response = await fetchCompetitionByCompId(compId); // API 호출
        console.log("fetchCompetitionByCompId API 응답:", response); // 응답 확인
        setCompetition(response.data); // 데이터 저장
      } catch (error) {
        console.error("공모전 상세 조회 오류:", error);
      }
    };

    // compId가 유효할 때만 API 호출
    if (compId) {
      loadCompetition();
    }
  }, [compId]); // compId가 변경될 때마다 실행

  // 데이터를 로드하기 전에 competition이 null인지 확인
  if (!competition) {
    return <div>로그인 다시!</div>; // 데이터 로딩 중 상태
  }

  const compUpdateForm = (compId) => {
    //공모전 수정 폼으로 이동
    history.push(`../competition-updateForm/${compId}`);
  };

  const handleDelete = async (compId) => {
    await deleteCompetition(compId);
    history.push(`../competition`); // 공모전 전체 페이지로 이동
  };

  // HTTP 요청을 위한 사용자 ID 확인
  // const getUserIdFromHeader = () => {
  //   // 예: JWT 토큰에서 사용자 정보를 추출하는 로직
  //   const token = localStorage.getItem("token"); // JWT 토큰 가져오기
  //   if (token) {
  //     const decodedToken = jwtDecode(token); // (설치 필요!)jwt-decode 패키지 사용

  //     return decodedToken.userId; // 사용자 ID 반환
  //   }
  //   return null; // 토큰이 없으면 null 반환
  // };
  // const autoChat = async (compId) => {
  //   const userId = getUserIdFromHeader(); // 헤더에서 userId 가져오기
  //   if (userId) {
  //     await fetchCompJoinByCompIdAndUserId(compId, userId);
  //   } else {
  //     console.error("User ID가 존재하지 않습니다.");
  //   }
  // };

  return (
    <>
      <h3>공모전 보기</h3>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th colSpan={2}>{competition.compTitle}</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>작성자 {competition.userId}</td>
            <td>조회수 {competition.count}</td>
          </tr>
          <tr rowSpan={10}>
            <td colSpan={2}>{competition.comp_content}</td>
          </tr>
        </tbody>
      </Table>
      <button onClick={() => compUpdateForm(competition.compId)}>수정</button>
      <button onClick={() => handleDelete(competition.compId)}>삭제</button>

      {/* <button onClick={() => autoChat(competition.compId)}>
        자동 채팅방 참여
      </button> */}
    </>
  );
};

export default CompDetail;
