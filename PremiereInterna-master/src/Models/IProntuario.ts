import { IConsulta } from "./IConsulta";

export interface IProntuario {
  id_prontuario?: number;
  id_consulta?: number;
  nomeMedico?: string;
  data_consulta?: any;
  anamnese?: string;
  prescricaoMedica?: string;
  examePrescritos?: string;
  file?: Blob;
  consulta?: IConsulta;
}
