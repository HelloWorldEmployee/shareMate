// src/Components/Competition/CompetitionForm.js
import React, { useEffect, useState } from "react";
import { createCompetition, updateCompetition } from "../../api/CompetitionApi";

const CompetitionForm = ({ selectedCompetition, onClose, onSubmit }) => {
  const [title, setTitle] = useState(""); // compTitle을을 위한 상태 추가
  const [content, setContent] = useState(""); // compContent를 위한 상태 추가

  useEffect(() => {
    if (selectedCompetition) {
      //console.log(selectedCompetition); // compId와 다른 속성을 확인
      setTitle(selectedCompetition.comp_title);
      setContent(selectedCompetition.comp_content);
    } else {
      setTitle("");
      setContent("");
    }
  }, [selectedCompetition]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const userId = selectedCompetition ? selectedCompetition.userId : null;

      // if (userId === null || userId === "null") {
      //   // userId가 없을 경우 경고 메시지 표시
      //   alert("userId가 없음으로 로그인 후 이용하세요!");
      //   return; // 이후 코드를 실행하지 않음
      // }

      if (selectedCompetition) {
        //const userId = selectedCompetition.userId; // 선택된 공모전에서 userId를 가져옴
        await updateCompetition(selectedCompetition.compId, {
          userId,
          comp_title: title,
          comp_content: content,
        });
      } else {
        await createCompetition({ comp_title: title, comp_content: content });
      }
      onClose(); // 폼 닫기
    } catch (error) {
      console.error("Error creating competition:", error);
      alert("공모전 생성 및 수정 중 오류가 발생했습니다."); // 사용자에게 오류 메시지 표시
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
        placeholder="공모전 제목"
        required
      />
      <br></br>
      <textarea
        value={content}
        onChange={(e) => setContent(e.target.value)}
        placeholder="공모전 내용"
        required
      />
      <button type="submit">제출</button>
      <button type="button" onClick={onClose}>
        취소
      </button>
    </form>
  );
};

export default CompetitionForm;
