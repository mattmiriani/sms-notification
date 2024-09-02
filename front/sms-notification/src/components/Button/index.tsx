import React from 'react';
import style from './style.module.css';

export type ButtonProps = {
  text: string;
  type?: 'button' | 'submit' | 'reset';
  onClick: (event: React.MouseEvent<HTMLButtonElement>) => void;
};

const Button: React.FC<ButtonProps> = ({ text, type = 'button', onClick }) => {
  return (
    <button type={type} onClick={onClick} className={style.futuristicButton}>
      {text}
    </button>
  );
};

export default Button;
