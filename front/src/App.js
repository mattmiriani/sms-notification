import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import CustomerList from './CustomerList';
import CustomerDetails from './CustomerDetails';
import AddCustomer from './AddCustomer';

function App() {
  return (
    <Router>
      <div>
        <h1>Notificação API</h1>
        <Routes>
          <Route path="/" element={<CustomerList />} />
          <Route path="/customer/:id" element={<CustomerDetails />} />
          <Route path="/add-customer" element={<AddCustomer />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
