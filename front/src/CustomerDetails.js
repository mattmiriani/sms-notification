import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, Link } from 'react-router-dom'; // Certifique-se de que esta importação esteja correta

function CustomerDetails() {
  const { id } = useParams();
  const [customer, setCustomer] = useState(null);

  useEffect(() => {
    axios.get(`http://localhost:8080/customers/${id}`)
      .then(response => {
        setCustomer(response.data);
      })
      .catch(error => console.error(error));
  }, [id]);

  if (!customer) return <div>Loading...</div>;

  return (
    <div>
      <h2>Detalhes do Cliente</h2>
      <p><strong>Nome:</strong> {customer.name}</p>
      <p><strong>Email:</strong> {customer.email}</p>
      <p><strong>Telefone:</strong> {customer.phone}</p>
      <p><strong>Empresa:</strong> {customer.companyName}</p>
      <Link to="/">Voltar</Link>
    </div>
  );
}

export default CustomerDetails;
