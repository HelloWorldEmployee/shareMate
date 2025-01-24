// src/components/competition/CompList.js
import React, { useEffect, useState } from "react";
import Table from "react-bootstrap/Table";
import Pagination from "react-bootstrap/Pagination";

import {
  fetchCompetitionByCompId,
  fetchCompetitions,
} from "../../api/CompetitionApi";
import { useHistory } from "react-router-dom/cjs/react-router-dom.min";
import CompDetail from "./CompDetail";

const CompList = () => {
  const history = useHistory();
  const [competitions, setCompetitions] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [competitionsPerPage] = useState(5); // 페이지당 보여줄 항목 수

  useEffect(() => {
    loadCompetitions();
  }, []);

  const loadCompetitions = async () => {
    const response = await fetchCompetitions();
    console.log(response.data); // 데이터 구조 확인
    setCompetitions(response.data);
  };

  // 현재 페이지의 공모전 목록 가져오기
  const indexOfLastCompetition = currentPage * competitionsPerPage;
  const indexOfFirstCompetition = indexOfLastCompetition - competitionsPerPage;
  const currentCompetitions = competitions.slice(
    indexOfFirstCompetition,
    indexOfLastCompetition
  );

  // 페이지 수 계산
  const pageNumbers = [];
  for (
    let i = 1;
    i <= Math.ceil(competitions.length / competitionsPerPage);
    i++
  ) {
    pageNumbers.push(i);
  }

  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  const compView = (compId) => {
    // 상세 페이지로 이동
    //window.location.href = `/competition/${compId}`;
    history.push(`../competition/${compId}`); // 상세 페이지로 이동
  };

  return (
    <>
      <h3>전체 조회</h3>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>글번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>조회수</th>
          </tr>
        </thead>
        <tbody>
          {currentCompetitions.map((comp) => (
            <tr
              key={comp.compId}
              onClick={() => {
                compView(comp.compId);
                //<CompDetail competit/>
              }}
            >
              <td>{comp.compId}</td>
              <td>{comp.compTitle}</td>
              <td>{comp.userId}</td>
              <td>{comp.count}</td>
            </tr>
          ))}
        </tbody>
      </Table>

      <Pagination>
        {pageNumbers.map((number) => (
          <Pagination.Item
            key={number}
            active={number === currentPage}
            onClick={() => handlePageChange(number)}
          >
            {number}
          </Pagination.Item>
        ))}
      </Pagination>
    </>
  );
};

export default CompList;
