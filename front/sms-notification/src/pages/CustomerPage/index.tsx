import React, { useState, useEffect } from 'react';
import axios from 'axios';
import EntityList from '../../components/EntityList';
import EntityModal from '../../components/EntityModal';
import Navbar from '../../components/Navbar';
import Footer from '../../components/Footer';
import Button from '../../components/Button';
import style from './style.module.css';

export type Entity = {
  id: string;
  name: string;
  email: string;
  phone: string;
  cpf: string;
  cnpj: string;
  companyName: string;
  currentFunds: number;
  credit: number;
  plan: {
    id: string;
  };
  active: boolean;
};

const CustomerPage: React.FC = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedCustomer, setSelectedCustomer] = useState<Entity | null>(null);
  const [customers, setCustomers] = useState<Entity[]>([]);
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    phone: '',
    cpf: '',
    cnpj: '',
    companyName: '',
    currentFunds: '',
    credit: '',
    plan: '',
    active: false,
  });

  useEffect(() => {
    // Função para buscar os clientes
    const fetchCustomers = async () => {
      try {
        const response = await axios.get('http://localhost:8080/customers');
        const data = response.data.content;
        setCustomers(data);
      } catch (error) {
        console.error('Erro ao buscar clientes:', error);
      }
    };

    fetchCustomers();
  }, []);

  const handleOpenModal = (id: string) => {
    if (id) {
      const customer = customers.find((c) => c.id === id) || null;
      setSelectedCustomer(customer);
      if (customer) {
        setFormData({
          name: customer.name,
          email: customer.email,
          phone: customer.phone,
          cpf: customer.cpf,
          cnpj: customer.cnpj,
          companyName: customer.companyName,
          currentFunds: customer.currentFunds.toString(),
          credit: customer.credit.toString(),
          plan: customer.plan.id,
          active: customer.active,
        });
      }
    } else {
      setSelectedCustomer(null);
      setFormData({
        name: '',
        email: '',
        phone: '',
        cpf: '',
        cnpj: '',
        companyName: '',
        currentFunds: '',
        credit: '',
        plan: '',
        active: false,
      });
    }
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setSelectedCustomer(null);
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const target = e.target as HTMLInputElement | HTMLSelectElement;
    const { name, type } = target;
    const value = type === 'checkbox' ? (target as HTMLInputElement).checked : target.value;

    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // lógica de envio de dados...
    handleCloseModal();
  };

  return (
    <div className={style.container}>
      <Navbar />
      <div className={style.main}>
        <EntityList entities={customers} onEntityClick={handleOpenModal} />
        <div className={style.buttonWrapper}>
          <Button text="Cadastrar Cliente" onClick={() => handleOpenModal('')} />
        </div>
        {isModalOpen && (
          <EntityModal
            title={selectedCustomer ? 'Editar Cliente' : 'Cadastrar Cliente'}
            fields={[
              { label: 'Nome', name: 'name', type: 'text' },
              { label: 'Email', name: 'email', type: 'email' },
              { label: 'Telefone', name: 'phone', type: 'text' },
              { label: 'CPF', name: 'cpf', type: 'text' },
              { label: 'CNPJ', name: 'cnpj', type: 'text' },
              { label: 'Nome da empresa', name: 'companyName', type: 'text' },
              { label: 'Saldo', name: 'currentFunds', type: 'number' },
              { label: 'Crédito', name: 'credit', type: 'number' },
              { label: 'Plano', name: 'plan', type: 'text' },
              { label: 'Ativo', name: 'active', type: 'checkbox' },
            ]}
            formData={formData}
            isOpen={isModalOpen}
            onClose={handleCloseModal}
            onChange={handleChange}
            onSubmit={handleSubmit}
          />
        )}
      </div>
      <Footer />
    </div>
  );
};

export default CustomerPage;
