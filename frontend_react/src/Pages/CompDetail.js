import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom"; // useParams 훅 import
import { fetchCompetitionByCompId } from "../api/CompetitionApi";

const CompDetail = () => {
  const { compId } = useParams(); // URL 파라미터에서 compId 가져오기
  const [competition, setCompetition] = useState(null);

  useEffect(() => {
    const loadCompetition = async (compId) => {
      try {
        const response = await fetchCompetitionByCompId(compId); // API 호출
        console.log("API 응답:", response); // 응답 확인
        setCompetition(response.data); // 데이터 저장
        console.log("response : ", response);
      } catch (error) {
        console.error("공모전 상세 조회 오류:", error);
      }
    };

    loadCompetition();
  }, [compId]);

  return (
    <div>
      <h2>
        {() => {
          setCompetition(competition.compTitle);
        }}
      </h2>
      <p>작성자: {competition.userId}</p>
      <p>내용: {competition.content}</p>
      {/* 추가 정보 출력 가능 */}
    </div>
  );
};

export default CompDetail;
