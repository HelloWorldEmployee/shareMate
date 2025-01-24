import React from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { createCompetition } from "../../api/CompetitionApi";
import { useHistory } from "react-router-dom/cjs/react-router-dom";

function CompForm() {
  const history = useHistory(); // history 객체 생성

  const handleSubmit = async (e) => {
    e.preventDefault();

    // 폼 데이터 수집
    const compTitle = e.target[0].value; // 제목
    const comp_content = e.target[1].value; // 내용
    console.log(
      "CompForm()_handleSubmit compTitle :",
      compTitle,
      "comp_content : ",
      comp_content
    );

    try {
      const response = await createCompetition({ compTitle, comp_content });
      console.log(response.data); // 데이터 구조 확인

      // 성공적으로 게시글이 생성되면 competition 페이지로 이동
      if (response.status === 201) {
        // 상태 코드 확인
        history.push("/competition"); // competition 페이지로 이동
      }
    } catch (error) {
      console.error("CompForm : Error creating competition:", error);
    }
  };

  return (
    <Form onSubmit={handleSubmit}>
      <Form.Group className="mb-3" controlId="compForm.ControlInput1">
        <Form.Label>공모전 - 게시글 제목</Form.Label>
        <Form.Control type="text" placeholder="제목을 입력하세요." required />
      </Form.Group>
      <Form.Group className="mb-3" controlId="compForm.ControlTextarea1">
        <Form.Label>공모전 - 게시글 내용</Form.Label>
        <Form.Control
          as="textarea"
          rows={3}
          placeholder="내용을 입력하세요."
          required
        />
      </Form.Group>
      <Button variant="primary" type="submit">
        제출
      </Button>
    </Form>
  );
}

export default CompForm;
