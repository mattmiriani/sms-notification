import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom'; // Certifique-se de que esta importação esteja correta

function CustomerList() {
  const [customers, setCustomers] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/customers')
      .then(response => {
        setCustomers(response.data.content);
      })
      .catch(error => console.error(error));
  }, []);

  return (
    <div>
      <h2>Lista de Clientes</h2>
      <ul>
        {customers.map(customer => (
          <li key={customer.id}>
            <Link to={`/customer/${customer.id}`}>{customer.name}</Link>
          </li>
        ))}
      </ul>
      <Link to="/add-customer">Adicionar Novo Cliente</Link>
    </div>
  );
}

export default CustomerList;
