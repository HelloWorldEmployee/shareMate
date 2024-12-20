// src/Components/Competition/CompetitionList.js
import React, { useEffect, useState } from "react";
import { fetchCompetitions, deleteCompetition } from "../../api";

const CompetitionList = ({ onEdit }) => {
  const [competitions, setCompetitions] = useState([]);

  useEffect(() => {
    loadCompetitions();
  }, []);

  const loadCompetitions = async () => {
    const response = await fetchCompetitions();
    console.log(response.data); // 데이터 구조 확인
    setCompetitions(response.data);
  };

  const handleDelete = async (compId) => {
    await deleteCompetition(compId);
    loadCompetitions(); // 삭제 후 다시 불러오기
  };

  return (
    <div>
      <h2>공모전 조회</h2>
      <ul>
        {competitions.map((comp) => (
          <li key={comp.compId}>
            {comp.comp_title} {comp.comp_content}
            <button onClick={() => onEdit(comp)}>수정</button>
            <button onClick={() => handleDelete(comp.compId)}>삭제</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default CompetitionList;
