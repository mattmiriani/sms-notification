import React from 'react';
import logoImage from '/notification-icon.svg'; 

import style from './style.module.css';

const Navbar: React.FC = () => {
  return (
    <nav className={style.navbar}>
      <img src={logoImage} alt="Logo" className={style.logoImage} />
      <ul className={style.navLinks}>
        <li><a href="/Customer">Clientes</a></li>
        <li><a href="/Plan">Planos</a></li>
        <li><a href="/Message">Enviar mensagens</a></li>
        <li><a href="/">Sair</a></li>
      </ul>
    </nav>
  );
}

export default Navbar;
