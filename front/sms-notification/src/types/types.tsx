export type Entity = {
  id: string;
  name: string;
  email?: string;
  phone?: string;
  cpf?: string;
  cnpj?: string;
  companyName?: string;
  currentFunds?: number;
  credit?: number;
  plan?: {
    id: string;
  };
  active?: boolean;
  price?: string;
};