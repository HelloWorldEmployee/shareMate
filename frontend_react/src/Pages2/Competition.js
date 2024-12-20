import React, { useState } from "react";
import CompetitionList from "../Components2/Competition/CompetitionList";
import CompetitionForm from "../Components2/Competition/CompetitionForm";
import { fetchCompetitions } from "../api";

function Competition() {
  const [competitions, setCompetitions] = useState([]); // 공모전 목록 상태 추가
  const [selectedCompetition, setSelectedCompetition] = useState(null);
  const [showForm, setShowForm] = useState(false);

  const handleEdit = (competition) => {
    setSelectedCompetition(competition);
    setShowForm(true);
  };

  const handleCloseForm = () => {
    setSelectedCompetition(null);
    setShowForm(false);
  };

  const loadCompetitions = async () => {
    const response = await fetchCompetitions();
    setCompetitions(response.data); // API로부터 받은 데이터로 상태 업데이트
  };
  const handleFormSubmit = async () => {
    await loadCompetitions(); // 게시글 추가 후 목록 갱신
    handleCloseForm(); // 폼 닫기
  };

  return (
    <>
      <h2>공모전 게시글</h2>
      <button
        onClick={() => {
          setSelectedCompetition(null);
          setShowForm(true);
        }}
      >
        게시글 추가
      </button>
      <br></br>
      {showForm && (
        <CompetitionForm
          selectedCompetition={selectedCompetition}
          onClose={handleCloseForm}
          onSubmit={handleFormSubmit}
        />
      )}
      <br></br>
      <br></br>
      {/* 공모전 게시글 수정은 login 완료 이후, 확인! */}
      <CompetitionList onEdit={handleEdit} />
    </>
  );
}

export default Competition;
