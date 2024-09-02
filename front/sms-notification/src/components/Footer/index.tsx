import React from 'react';
import style from './style.module.css';

const Footer: React.FC = () => {
  return (
    <footer className={style.footer}>
      <div className={style.footerContent}>
        <ul className={style.footerLinks}>
          <li><a href="#">Política de Privacidade</a></li>
          <li><a href="#">Termos de Uso</a></li>
          <li><a href="#">Contato</a></li>
        </ul>
      </div>
    </footer>
  );
}

export default Footer;
