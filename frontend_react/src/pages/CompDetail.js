import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom"; // useParams 훅 import
import {
  deleteCompetition,
  fetchCompetitionByCompId,
} from "../api/CompetitionApi";
import { Table } from "react-bootstrap";
import { useHistory } from "react-router-dom/cjs/react-router-dom";

const CompDetail = () => {
  const history = useHistory();
  const { compId } = useParams(); // URL 파라미터에서 compId 가져오기
  const [competition, setCompetition] = useState(null);

  useEffect(() => {
    const loadCompetition = async () => {
      try {
        const response = await fetchCompetitionByCompId(compId); // API 호출
        console.log("API 응답:", response); // 응답 확인
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

  //const handleEdit = async (compId, competition) => {
  //await updateCompetition(compId, competition);
  //history.push(`../competition/${compId}`); //상세 페이지로 이동
  //<button onClick={() => handleEdit(competition.compId, competition)}>
  //    수정
  //  </button>
  //};
  const compUpdateForm = (compId) => {
    //공모전 수정 폼으로 이동
    history.push(`../competition-updateForm/${compId}`);
  };

  const handleDelete = async (compId) => {
    await deleteCompetition(compId);
    history.push(`../competition`); // 공모전 전체 페이지로 이동
  };

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
            <td colSpan={2}>{competition.compTitle}</td>
          </tr>
        </tbody>
      </Table>
      <button onClick={() => compUpdateForm(competition.compId)}>수정</button>
      <button onClick={() => handleDelete(competition.compId)}>삭제</button>
    </>
  );
};

export default CompDetail;
