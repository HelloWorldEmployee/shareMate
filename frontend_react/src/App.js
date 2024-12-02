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

//import "./App.css";
//import { Container, Button } from "react-bootstrap";
//import { Route } from "react-router-dom";
//import Login from "./pages/user/Login";
//import Profile from "./pages/user/Profile";
//import Join from "./pages/user/Join";
//import Header from "./components/Header";
// import Header from
//function App() {
//  return (
//    <>
//      <Container>
//        <Header />
//        <Route path="/Login" exact component={Login}></Route>
//        <Route path="/Join" exact component={Join}></Route>
//        <Route path="/Profile" exact component={Profile}></Route>
//      </Container>
//    </>
//  );
//}

export default App;

