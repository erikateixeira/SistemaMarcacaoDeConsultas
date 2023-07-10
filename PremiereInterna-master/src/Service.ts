import axios from "axios";
import { IPaciente } from "./Models/IPaciente";
import { IMedico } from "./Models/IMedico";
import { IFuncionario } from "./Models/IFuncionario";
import { IProntuario } from "./Models/IProntuario";
import { IConsulta } from "./Models/IConsulta";

export const Service = {
  api: () =>
    axios.create({
      baseURL: "/api/",
      auth: {
        username: sessionStorage.getItem("Usuario") ?? "",
        password: sessionStorage.getItem("Senha") ?? "",
      },
    }),
  getNanana: () => {
    return Service.api().get("/posts");
  },
  getMedicos: () => {
    return Service.api().get("/medico/dados?nome=");
  },

  getMedicosPorNome: (nome: string) => {
    return Service.api().get("/medico/dados?nome=" + nome);
  },

  getMedicosPorEspecialidade: (especialidade: string) => {
    return Service.api().get(
      "/consulta/medicos-disponiveis?especialidade=" + especialidade
    );
  },

  getDatasDisponiveisPorMedico: (nome_medico: string) => {
    return Service.api().get(
      "/consulta/datas-disponiveis?nome_medico=" + nome_medico
    );
  },

  getHorasValidas: (nome_medico: string, data_consulta: string) => {
    return Service.api().get(
      "/consulta/horas-validas?nome_medico=" +
        nome_medico +
        "&data_consulta=" +
        data_consulta
    );
  },

  getFuncionarios: () => {
    return Service.api().get("/funcionario/dados");
  },
  getFuncionariosPorNome: (nome: string) => {
    return Service.api().get("/funcionario/dados?nome=" + nome);
  },
  getPacientes: () => {
    return Service.api().get("/paciente/dados");
  },

  getPacientesPorNome: (nome: string) => {
    return Service.api().get("/paciente/dados?nome=" + nome);
  },

  PostPacientes: (paciente: IPaciente) => {
    return Service.api().post("/paciente", paciente);
  },

  PostMedicos: (medico: IMedico) => {
    return Service.api().post("/medico", medico);
  },

  PostFuncionarios: (funcionario: IFuncionario) => {
    return Service.api().post("/funcionario", funcionario);
  },

  deletePacientes: (idPaciente?: Number) => {
    return Service.api().delete("/paciente?id_paciente=" + idPaciente);
  },

  deleteMedicos: (idMedico?: Number) => {
    return Service.api().delete("/medico?id_medico=" + idMedico);
  },

  deleteFuncionarios: (idFuncionario?: Number) => {
    return Service.api().delete("/funcionario?id_funcionario=" + idFuncionario);
  },

  PutPacientes: (paciente: IPaciente) => {
    return Service.api().put("/paciente?id_paciente=" + paciente.id, paciente);
  },

  PutMedicos: (medico: IMedico) => {
    return Service.api().put("/medico?id_medico=" + medico.id, medico);
  },

  PutFuncionarios: (funcionario: IFuncionario) => {
    return Service.api().put(
      "/funcionario?id_funcionario=" + funcionario.id,
      funcionario
    );
  },

  getProntuario: () => {
    return Service.api().get("/prontuario/paciente");
  },

  PostProntuario: (prontuario: IProntuario) => {
    return Service.api().post(
      "/prontuario?id_consulta=" + prontuario.id_prontuario,
      prontuario,
      { headers: { "Content-type": "multipart/form-data" } }
    );
  },

  PutConsulta: (ControleConsulta: IConsulta) => {
    return Service.api().put(
      "/consulta?id_consulta=" + ControleConsulta.id_consulta,
      ControleConsulta
    );
  },

  PostConsulta: (consulta: IConsulta) => {
    return Service.api().post("/consulta", consulta);
  },

  /*getConsulta: (data: Date) => {
    return Service.api().get("/consulta/lista?data=" + data);
  },*/

  getConsultaAtendimento: (id_consulta: number) => {
    return Service.api().get(
      "/consulta/atendimento?id_consulta=" + id_consulta
    );
  },

  getConsultaLista: (nome: string, data: string) => {
    return Service.api().get("/consulta/lista?nome=" + nome + "&data=" + data);
  },

  getConsultaDia: (dia: string) => {
    return Service.api().get("/consulta/dia?data=" + dia);
  },

  getIniciarConsulta: (id_consulta: string) => {
    return Service.api().get("/iniciar=" + id_consulta);
  },

  getIniciar: (id_consulta: number) => {
    return Service.api().get("/consulta/iniciar?id_consulta=" + id_consulta);
  },

  getConsultaConfirmacao: (id_consulta: number) => {
    return Service.api().get(
      "/consulta/confirmacao?id_consulta=" + id_consulta
    );
  },

  getIniciarAtendimento: (id_consulta: number) => {
    return Service.api().get("/consulta/iniciar?id_consulta=" + id_consulta);
  },

  getConfirmacaoBoolean: (id_consulta: number) => {
    return Service.api().get("/consulta/ligacao?id_consulta=" + id_consulta);
  },

  PutConsultaConfirmacao: (id_consulta?: number, confirmacao: boolean) => {
    return Service.api().put(
      `/consulta/confirmacao?id_consulta=${id_consulta}&confirmacao=${confirmacao}`
    );
  },

  getMyUsuario: () => {
    return Service.api().get("/my/usuario");
  },

  deleteConsulta: (idConsulta?: Number) => {
    return Service.api().delete("/consulta?id_consulta=" + idConsulta);
  },
};
