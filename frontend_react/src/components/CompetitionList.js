// src/components/CompetitionList.js
import React, { useEffect, useState } from 'react';
import { fetchCompetitions, deleteCompetition } from '../api';

const CompetitionList = ({ onEdit }) => {
    const [competitions, setCompetitions] = useState([]);

    useEffect(() => {
        loadCompetitions();
    }, []);

    const loadCompetitions = async () => {
        const response = await fetchCompetitions();
        setCompetitions(response.data);
    };

    const handleDelete = async (id) => {
        await deleteCompetition(id);
        loadCompetitions(); // 삭제 후 다시 불러오기
    };

    return (
        <div>
            <h2>Competitions</h2>
            <ul>
                {competitions.map(comp => (
                    <li key={comp.comp_id}>
                        {comp.name}
                        <button onClick={() => onEdit(comp)}>Edit</button>
                        <button onClick={() => handleDelete(comp.comp_id)}>Delete</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default CompetitionList;
