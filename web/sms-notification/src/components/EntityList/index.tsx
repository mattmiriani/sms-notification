import React from 'react';
import { Entity } from '../../types/types';

import style from './style.module.css';

type EntityListProps = {
  entities: Entity[];
  onEntityClick: (id: string) => void;
};

const EntityList: React.FC<EntityListProps> = ({ entities, onEntityClick }) => {
  return (
    <div className={style.entityList}>
      {entities.map((entity) => (
        <div
          key={entity.id}
          className={style.entityItem}
          onClick={() => onEntityClick(entity.id)}
        >
          <h3>{entity.name}</h3>
          {entity.email && <p>{entity.email}</p>}
          {entity.phone && <p>{entity.phone}</p>}
          {entity.active !== undefined && (
            <span className={style.status}>
              {entity.active ? 'Ativo' : 'Inativo'}
            </span>
          )}
        </div>
      ))}
    </div>
  );
};

export default EntityList;
