import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom'; // Corrigido: importação do Link

function AddCustomer() {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [companyName, setCompanyName] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();

    const newCustomer = {
      name,
      email,
      phone,
      cpf: '12345678910',
      cnpj: '12345678910111',
      companyName,
      active: true,
      balance: 1000.00,
      creditLimit: 5000.00,
      plan: {
        id: 'ed5b1f91-f37f-4201-81fb-d949421ab67b'
      }
    };

    console.log(newCustomer);

    axios.post('http://localhost:8080/customers', newCustomer)
      .then(() => {
        navigate('/'); // Redirecionar após a criação do cliente
      })
      .catch(error => console.error(error));
  };

  return (
    <div>
      <h2>Adicionar Novo Cliente</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Nome:</label>
          <input type="text" value={name} onChange={(e) => setName(e.target.value)} required />
        </div>
        <div>
          <label>Email:</label>
          <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
        </div>
        <div>
          <label>Telefone:</label>
          <input type="text" value={phone} onChange={(e) => setPhone(e.target.value)} required />
        </div>
        <div>
          <label>Nome da Empresa:</label>
          <input type="text" value={companyName} onChange={(e) => setCompanyName(e.target.value)} required />
        </div>
        <button type="submit">Adicionar Cliente</button>
      </form>
      <Link to="/">Voltar</Link> {/* Corrigido: Adicionado Link */}
    </div>
  );
}

export default AddCustomer;
