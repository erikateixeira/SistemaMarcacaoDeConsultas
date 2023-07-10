import { useState } from "react";
import { IProntuario } from "../Models/IProntuario";
import { Service } from "../Service";
import { Cabecalho } from "./Componentes/Cabecalho";
import { DadosConsulta } from "./Componentes/DadosConsulta";
import { DadosPaciente } from "./Componentes/DadosPaciente";

export const PesquisarProntuario = () => {
  const [prontuario, setProntuario] = useState<IProntuario>();
  const registrar = () => {
    if (
      prontuario &&
      window.confirm(
        "Deseja realmente registrar este Paciente? " +
          JSON.stringify(prontuario)
      )
    ) {
      Service.PostProntuario(prontuario)
        .then(() => window.alert("Atualizado com sucesso"))
        .catch((err) =>
          window.alert("Erro:" + JSON.stringify(err?.response?.data))
        );
    }
  };
  return (
    <>
      <Cabecalho nomeTela="Pesquisar Protuário"> </Cabecalho>
      <fieldset>
        <legend> Dados da Consulta </legend>
        <DadosConsulta></DadosConsulta>
      </fieldset>

      <fieldset>
        <legend> Dados do Paciente </legend>
        <DadosPaciente></DadosPaciente>
      </fieldset>

      <fieldset>
        <legend> Anamnese </legend>
        <textarea name="" id="" cols={100} rows={10}></textarea>
      </fieldset>

      <fieldset>
        <legend> Prescrição Médica </legend>
        <textarea name="" id="" cols={100} rows={10}></textarea>
      </fieldset>

      <fieldset>
        <legend> Exames Prescritos/Laudos de Exames </legend>
        <textarea name="" id="" cols={100} rows={10}></textarea>
      </fieldset>

      <div className="col-12">
        <button id="btncontato" className="btn btn-info rounded-pill px-3">
          <a href="PesquisarProntuario">Registrar Prontuário</a>
        </button>
      </div>
    </>
  );
};
function validate() {
  throw new Error("Function not implemented.");
}
