import React, { useState } from "react";
import { ButtonGroup, Button } from "react-bootstrap";
import ChatCardBox from "../components/Study/ChatCardBox";
import CreateStudyChat from "../components/Study/CreateStudyChat";

const StudyBoard = () => {
  const [showCreateChat, setshowCreateChat] = useState(false);
  const handleAddClick = () => {
    setshowCreateChat(!showCreateChat);
  };
  return (
    <>
      <h1>스터디 방 게시판</h1>
      <div className="button">
        <ButtonGroup className="mb-2">
          <Button onClick={handleAddClick}>추가</Button>
        </ButtonGroup>
        {showCreateChat && <CreateStudyChat />}
      </div>
      <div className="chatCardBox">
        채팅방 카드 위치
        <ChatCardBox />
      </div>
    </>
  );
};

export default StudyBoard;
