import React, { useEffect, useState } from "react";
import { Card, Row, Col, Button, ButtonGroup } from "react-bootstrap";
import { deleteStudy, readStudy } from "../../Api/StudyApi";
import UpdateStudyChat from "./UpdateStudyChat";

const ChatCardBox = () => {
  const [studies, setStudies] = useState([]);
  const [showUpdateChat, setshowUpdateChat] = useState({});

  const readStudyChatRoom = async () => {
    try {
      const response = await readStudy();
      setStudies(response.data);
      console.log(response.data);
    } catch (error) {
      console.log("데이터 읽기 실패!");
    }
  };
  useEffect(() => {
    readStudyChatRoom();
  }, []);

  const deleteStudyChat = async (studyId, userId) => {
    try {
      deleteStudy(studyId, userId);
      setStudies((prevStudies) =>
        prevStudies.filter((study) => study.studyId !== studyId)
      );
      console.log("삭제완료!!!!");
      // window.location.reload();
    } catch (error) {
      console.log("삭제 실패 : " + error);
    }
  };

  const fixStudyChat = (studyId) => {
    console.log(studyId);
    setshowUpdateChat((prevshowUpdateChat) => ({
      ...prevshowUpdateChat,
      [studyId]: !prevshowUpdateChat[studyId],
    }));
  };
  return (
    <>
      {studies.length > 0 ? (
        <Row xs={1} md={2} className="g-4">
          {studies
            .filter((study) => study.study_name)
            .map((study) => (
              <Col key={study.studyId}>
                <Card>
                  <Card.Img variant="top" src="holder.js/100px160" />
                  <Card.Body>
                    <Card.Title>{study.study_name}</Card.Title>
                    <Card.Text>{study.study_content}</Card.Text>
                    <ButtonGroup className="mb-2">
                      <Button
                        onClick={() => fixStudyChat(study.studyId)}
                        variant="primary"
                      >
                        수정
                      </Button>
                      <Button
                        onClick={() =>
                          deleteStudyChat(study.studyId, study.userId)
                        }
                        variant="primary"
                      >
                        삭제
                      </Button>
                    </ButtonGroup>
                    {showUpdateChat[study.studyId] && (
                      <UpdateStudyChat study={study} />
                    )}
                  </Card.Body>
                </Card>
              </Col>
            ))}
        </Row>
      ) : studies.length === 0 ? (
        <div>채팅방 리스트 없음</div>
      ) : (
        <div>로딩중....</div>
      )}
    </>
  );
};

export default ChatCardBox;
