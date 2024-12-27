import React, { useState } from "react";
import { Form, Button } from "react-bootstrap";
import { createStudy } from "../../Api/StudyApi";

const CreateStudyChat = (props) => {
  const [study, setStudy] = useState({
    name: "",
    content: "",
    userId: "",
  });
  const changeValue = (e) => {
    setStudy({
      ...study,
      [e.target.name]: e.target.value,
    });
  };
  const addStudyChat = async (e) => {
    e.preventDefault();
    try {
      const response = await createStudy({
        study_name: study.name,
        study_content: study.content,
      });
      console.log(response.data);
      // props.history.push("/api/studyBoard");
      window.location.href = "/studyBoard";
    } catch (error) {
      console.log(error);
    }
  };
  return (
    <>
      <Form onSubmit={addStudyChat}>
        {/* 채팅방 이름 입력 */}
        <Form.Group className="mb-3" controlId="formId">
          <Form.Label>스터디 이름</Form.Label>
          <Form.Control
            type="text"
            name="name"
            value={study.name}
            onChange={changeValue}
            required
          />
        </Form.Group>

        {/* 내용 입력 */}
        <Form.Group className="mb-3" controlId="formPassword">
          <Form.Label>내용</Form.Label>
          <Form.Control
            type="text"
            placeholder="내용"
            name="content"
            value={study.content}
            onChange={changeValue}
            required
          />
        </Form.Group>

        {/* 스터디 추가완료 버튼 */}
        <div className="d-grid">
          <Button variant="primary" type="submit">
            추가 완료
          </Button>
        </div>
      </Form>
    </>
  );
};
export default CreateStudyChat;
