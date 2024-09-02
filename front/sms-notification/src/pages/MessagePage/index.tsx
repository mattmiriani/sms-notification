import React, { useState } from 'react';
import Navbar from '../../components/Navbar';
import Footer from '../../components/Footer';
import Button from '../../components/Button';

import style from './style.module.css';

export type MessageFormData = {
  phoneNumber: string;
  whatsApp: boolean;
  text: string;
  customerId: string;
};

const MessagePage: React.FC = () => {
  const [formData, setFormData] = useState<MessageFormData>({
    phoneNumber: '',
    whatsApp: false,
    text: '',
    customerId: ''  // Considerando que o customerId será preenchido automaticamente
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, type, value } = e.target as HTMLInputElement | HTMLTextAreaElement;
    const isCheckbox = type === 'checkbox';

    setFormData(prevState => ({
      ...prevState,
      [name]: isCheckbox ? (e.target as HTMLInputElement).checked : value
    }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // lógica de envio de dados...
  };

  return (
    <div className={style.container}>
      <Navbar />
      <div className={style.main}>
        <h2>Enviar Mensagem</h2>
        <form className={style.form} onSubmit={handleSubmit}>
          <div className={style.formGroup}>
            <label htmlFor="phoneNumber">Número de Telefone</label>
            <input
              id="phoneNumber"
              name="phoneNumber"
              type="text"
              value={formData.phoneNumber}
              onChange={handleChange}
              required
            />
          </div>
          <div className={style.formGroup}>
            <label htmlFor="whatsApp">WhatsApp</label>
            <input
              id="whatsApp"
              name="whatsApp"
              type="checkbox"
              checked={formData.whatsApp}
              onChange={handleChange}
            />
          </div>
          <div className={style.formGroup}>
            <label htmlFor="text">Mensagem</label>
            <textarea
              id="text"
              name="text"
              value={formData.text}
              onChange={handleChange}
              required
            />
          </div>
          <div className={style.buttonWrapper}>
            <Button text="Enviar Mensagem" type="submit" onClick={handleSubmit} />
          </div>
        </form>
      </div>
      <Footer />
    </div>
  );
};

export default MessagePage;
