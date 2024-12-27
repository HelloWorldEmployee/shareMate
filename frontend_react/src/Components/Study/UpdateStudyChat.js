import React, { useState } from "react";
import { Form, Button } from "react-bootstrap";
import { updateStudy } from "../../Api/StudyApi";

const UpdateStudyChat = ({ study }) => {
  console.log(study);
  const [studyData, setStudyData] = useState({
    id: study.studyId,
    name: study.study_name,
    content: study.study_content,
  });

  const changeValue = (e) => {
    setStudyData({
      ...studyData,
      [e.target.name]: e.target.value,
    });
  };

  const fixStudyChat = async (e) => {
    e.preventDefault();
    try {
      console.log("업데이트");
      console.log(studyData);
      const response = await updateStudy(
        studyData.id,
        {
          study_name: studyData.name,
          study_content: studyData.content,
        },
        {
          headers: {
            "Content-type": "application/json",
          },
        }
      );
      console.log("업데이트 : " + response.data);
      window.location.href = "/studyBoard";
    } catch (error) {
      console.log("업데이트 오류" + error);
    }
  };
  return (
    <>
      <Form onSubmit={fixStudyChat}>
        {/* 채팅방 이름 입력 */}
        <Form.Group className="mb-3" controlId="formId">
          <Form.Label>스터디 이름</Form.Label>
          <Form.Control
            type="text"
            name="name"
            value={studyData.name}
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
            value={studyData.content}
            onChange={changeValue}
            required
          />
        </Form.Group>

        {/* 스터디 업데이트완료 버튼 */}
        <div className="d-grid">
          <Button variant="primary" type="submit">
            수정 완료
          </Button>
        </div>
      </Form>
    </>
  );
};
export default UpdateStudyChat;
