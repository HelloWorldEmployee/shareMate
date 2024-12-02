// src/components/CompetitionForm.js
import React, { useEffect, useState } from 'react';
import { createCompetition, updateCompetition } from '../api';

const CompetitionForm = ({ selectedCompetition, onClose }) => {
    const [name, setName] = useState('');

    useEffect(() => {
        if (selectedCompetition) {
            setName(selectedCompetition.name);
        } else {
            setName('');
        }
    }, [selectedCompetition]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (selectedCompetition) {
            await updateCompetition(selectedCompetition.comp_title, { name });
        } else {
            await createCompetition({ name });
        }
        onClose(); // 폼 닫기
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                value={name}
                onChange={(e) => setName(e.target.value)}
                placeholder="Competition Name"
                required
            />
            <button type="submit">Submit</button>
            <button type="button" onClick={onClose}>Cancel</button>
        </form>
    );
};

export default CompetitionForm;
