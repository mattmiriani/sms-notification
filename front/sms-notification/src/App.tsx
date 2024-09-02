import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import CustomerPage from './pages/CustomerPage';
import LoginPage from './pages/LoginPage';
import MessagePage from './pages/MessagePage';
import PlanPage from './pages/PlanPage';

import './styles/global.css';

const App: React.FC = () => {
  return (
    <Router>
      <Routes>
        <Route path="/customer" element={<CustomerPage />} />
        <Route path="/" element={<LoginPage />} />
        <Route path="/message" element={<MessagePage />} />
        <Route path="/plan" element={<PlanPage />} />
      </Routes>
    </Router>
  );
};

export default App;