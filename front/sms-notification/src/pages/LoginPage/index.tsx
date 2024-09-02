import React from 'react';
import { useNavigate } from 'react-router-dom';

import style from './style.module.css'; 

const LoginPage: React.FC = () => {
  const navigate = useNavigate();

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    
    navigate('/Customer');
  };

  return (
    <div className={style.container}>
      <div className={style.card}>
        <h1 className={style.title}>Login</h1>
        <form className={style.form} onSubmit={handleSubmit}>
          <div className={style.formGroup}>
            <label htmlFor="email">Email</label>
            <input type="email" id="email" name="email" placeholder="Email" required />
          </div>
          <div className={style.formGroup}>
            <label htmlFor="password">Senha</label>
            <input type="password" id="password" name="password" placeholder="Senha" required />
          </div>
          <button type="submit" className={style.submitButton}>Entrar</button>
        </form>
      </div>
    </div>
  );
};

export default LoginPage;
