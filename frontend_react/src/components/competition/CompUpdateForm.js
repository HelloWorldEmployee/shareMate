import React, { useEffect, useState } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import {
  fetchCompetitionByCompId,
  updateCompetition,
} from "../../api/CompetitionApi";
import { useHistory, useParams } from "react-router-dom/cjs/react-router-dom";

function CompUpdateForm() {
  const history = useHistory(); // history 객체 생성
  const { compId } = useParams();
  const [competition, setCompetition] = useState(null);
  const [compTitle, setCompTitle] = useState(""); // 제목 상태
  const [compContent, setCompContent] = useState(""); // 내용 상태

  useEffect(() => {
    const loadCompetition = async () => {
      try {
        const response = await fetchCompetitionByCompId(compId); // API 호출
        console.log("fetchCompetitionByCompId API 응답:", response); // 응답 확인
        setCompTitle(response.data.compTitle); // 제목 초기화
        setCompContent(response.data.comp_content); // 내용 초기화
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

  // compTitle과 compContent가 변경될 때마다 competition 객체 업데이트
  useEffect(() => {
    if (competition) {
      setCompetition((prev) => ({
        ...prev,
        compTitle,
        comp_content: compContent,
      }));
    }
  }, [compTitle, compContent]); // compTitle과 compContent가 변경될 때마다 실행

  // 데이터를 로드하기 전에 competition이 null인지 확인
  if (!competition) {
    return <div>로그인 다시!</div>; // 데이터 로딩 중 상태
  }

  const handleEdit = async (e) => {
    e.preventDefault();
    // 폼 데이터 수집
    console.log("handelEdit compId :", compId);

    try {
      const response = await updateCompetition(compId, {
        compTitle,
        comp_content: compContent,
      });
      console.log(response.data); // 데이터 구조 확인

      // 성공적으로 게시글이 생성되면 competition 페이지로 이동
      if (response.status === 200) {
        // 상태 코드 확인
        history.push(`../competition/${compId}`); // competition 페이지로 이동
      }
    } catch (error) {
      console.error("Error creating competition:", error);
    }
  };

  return (
    <Form onSubmit={handleEdit}>
      <Form.Group className="mb-3" controlId="compForm.ControlInput1">
        <Form.Label>공모전 - 게시글 제목</Form.Label>
        <Form.Control
          type="text"
          value={compTitle}
          onChange={(e) => setCompTitle(e.target.value)}
          required
        />
      </Form.Group>
      <Form.Group className="mb-3" controlId="compForm.ControlTextarea1">
        <Form.Label>공모전 - 게시글 내용</Form.Label>
        <Form.Control
          as="textarea"
          rows={3}
          value={compContent}
          onChange={(e) => setCompContent(e.target.value)}
          required
        />
      </Form.Group>
      <Button variant="primary" type="submit">
        수정
      </Button>
    </Form>
  );
}

export default CompUpdateForm;
