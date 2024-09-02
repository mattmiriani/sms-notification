import React, { useState } from 'react';
import EntityList from '../../components/EntityList';
import EntityModal from '../../components/EntityModal';
import Navbar from '../../components/Navbar';
import Footer from '../../components/Footer';
import Button from '../../components/Button';

import style from './style.module.css';

export type Plan = {
  id: string;
  name: string;
  price: string;
};

const PlanPage: React.FC = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState<{
    name: string;
    price: string;
  }>({
    name: '',
    price: '',
  });
  const [selectedPlan, setSelectedPlan] = useState<Plan | null>(null);

  const handleOpenModal = (id?: string) => {
    if (id) {
      const plan = plans.find(p => p.id === id) || null;
      setSelectedPlan(plan);
      if (plan) {
        setFormData({
          name: plan.name,
          price: plan.price,
        });
      }
    } else {
      setFormData({
        name: '',
        price: '',
      });
      setSelectedPlan(null);
    }
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setSelectedPlan(null);
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
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

  const plans: Plan[] = [
    { id: '1', name: 'Plano A', price: 'R$ 100,00' },
    // outros planos...
  ];

  return (
    <div className={style.container}>
      <Navbar />
      <div className={style.main}>
        <EntityList entities={plans} onEntityClick={handleOpenModal} />
        <div className={style.buttonWrapper}>
          <Button text="Cadastrar Plano" onClick={() => handleOpenModal()} />
        </div>
        {isModalOpen && (
          <EntityModal
            title={selectedPlan ? "Editar Plano" : "Cadastrar Plano"}
            fields={[
              { label: 'Nome', name: 'name', type: 'text' },
              { label: 'Preço', name: 'price', type: 'text' }
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

export default PlanPage;
