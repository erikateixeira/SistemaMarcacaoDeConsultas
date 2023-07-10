export interface IConsulta {
  id_consulta?: number;
  nome_funcionario?: string;
  nome_medico?: string;
  nome_paciente?: string;
  data_consulta?: any;
  hora_consulta?: any;
  retorno_consulta?: any;
  especialidade?: string;
  confirmacao: boolean;
  autorizacao: any;
  pagamento: any;
}
