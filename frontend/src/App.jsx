import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import GameDashboard from './pages/GameDashboard';
import AdminAddGame from './pages/AdminAddGame';
import GameDetailPage from './pages/GameDetailPage';

import FibonacciChecker from './pages/FibonacciChecker';
import JackEnPoy from './pages/JackEnPoy';
import Multiplication from './pages/Multiplication';
import FactorialCalculator from './pages/FactorialCalculator';
import PasswordValidation from './pages/PasswordValidation';



function App() {
 
  return (
     <Router>
      <nav style={{ padding: '20px 40px', background: '#222', color: 'white', display: 'flex', gap: '20px' }}>
        <Link to="/" style={{ color: 'white', textDecoration: 'none', fontWeight: 'bold' }}>🎮 User Dashboard</Link>
        <Link to="/admin/add-game" style={{ color: '#ffc107', textDecoration: 'none', fontWeight: 'bold' }}>🔒 Admin Portal</Link>
      </nav>

      <div style={{ padding: '40px', fontFamily: 'Arial, sans-serif', maxWidth: '800px', margin: '0 auto' }}>
        <Routes>
          <Route path="/" element={<GameDashboard />} />
          <Route path="/admin/add-game" element={<AdminAddGame />} />
          <Route path="/games/:id" element={<GameDetailPage />} />

          <Route path="/simulator/fibonacci" element={<FibonacciChecker />} />
          <Route path="/simulator/jack-en-poy" element={<JackEnPoy />} />
          <Route path="/simulator/factorial" element={<FactorialCalculator />} />
          <Route path="/simulator/multiplication" element={<Multiplication />} />
          <Route path="/simulator/password-validation" element={<PasswordValidation />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;