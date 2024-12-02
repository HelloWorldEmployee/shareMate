// src/App.js
import React, { useState } from 'react';
import CompetitionList from './components/CompetitionList';
import CompetitionForm from './components/CompetitionForm';

const App = () => {
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

    return (
        <div>
            <h1>Competition Board</h1>
            <button onClick={() => { setSelectedCompetition(null); setShowForm(true); }}>Add Competition</button>
            {showForm && (
                <CompetitionForm 
                    selectedCompetition={selectedCompetition} 
                    onClose={handleCloseForm} 
                />
            )}
            <CompetitionList onEdit={handleEdit} />
        </div>
    );
};

export default App;

