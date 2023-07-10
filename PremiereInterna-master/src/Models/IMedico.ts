export interface IMedico {
  senha?: string;
  id?: number;
  nome?: string;
  cnpj?: string;
  crm_estado?: string;
  crm_num?: string;
  telefone?: string;
  email?: string;
  especialidade?: string;
  sala?: string;
  login?: string;
  diasDisponiveis?: any[];
  hora_inicial?: any;
  hora_final?: any;
  valor_consulta?: number;
}
