import React from 'react';
import style from './style.module.css';

type EntityModalProps = {
  title: string;
  fields: { label: string; name: string; type: string }[];
  formData: { [key: string]: any };
  isOpen: boolean;
  onClose: () => void;
  onChange: (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => void;
  onSubmit: (e: React.FormEvent) => void;
};

const EntityModal: React.FC<EntityModalProps> = ({
  title,
  fields,
  formData,
  isOpen,
  onClose,
  onChange,
  onSubmit,
}) => {
  return (
    <div className={`${style.modalOverlay} ${isOpen ? style.open : ''}`}>
      <div className={style.modalContent}>
        <button onClick={onClose} className={style.closeButton}>x</button>
        <h2>{title}</h2>
        <form onSubmit={onSubmit}>
          {fields.map((field) => (
            <div key={field.name}>
              <label>{field.label}</label>
              {field.type === 'checkbox' ? (
                <div className={style.switch}>
                  <input
                    type="checkbox"
                    name={field.name}
                    checked={formData[field.name] || false}
                    onChange={onChange}
                  />
                  <span className={`${style.slider} ${style.round}`}></span>
                </div>
              ) : (
                <input
                  type={field.type}
                  name={field.name}
                  value={field.type === 'number' ? formData[field.name] || '' : formData[field.name] || ''}
                  onChange={onChange}
                  required
                />
              )}
            </div>
          ))}
          <button type="submit">Salvar</button>
        </form>
      </div>
    </div>
  );
};

export default EntityModal;
