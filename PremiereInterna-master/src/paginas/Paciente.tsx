import { useEffect, useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { Cabecalho } from "./Componentes/Cabecalho";
import "./Css/CadastroPaciente.css";
import { Service } from "../Service";
import { IPaciente } from "../Models/IPaciente";
import { Medico } from "./Medico";
import { VisualizarPaciente } from "./VisualizarPaciente";

const Paciente = function () {
  const navigate = useNavigate();
  const location = useLocation();
  const [listaPacientes, setListaPacientes] = useState<IPaciente[]>([]);
  const [nomePesquisado, setNomePesquisado] = useState<string>("");

  useEffect(() => {
    document.title = "Dados Paciente";
    Service.getPacientes()
      .then((res) => {
        setListaPacientes(res.data);
      })
      .catch((err) => window.alert("Erro:" + JSON.stringify(err)));
  }, []);

  const handlePesquisarPorNome = async () => {
    debugger;
    const { data } = await Service.getPacientesPorNome(nomePesquisado);
    setListaPacientes(data);
  };

  useEffect(() => {
    const searchParams = new URLSearchParams(location.search);
    const nome = searchParams.get("nome");

    if (nome) {
      setNomePesquisado(nome);
      Service.getPacientesPorNome(nome)
        .then((response) => {
          setListaPacientes(response.data);
        })
        .catch((error) => {
          console.log("Erro ao obter os pacientes por nome:", error);
        });
    }
  }, [location.search]);

  const encaminharParaCadastro = (infoPaciente?: IPaciente) => {
    return navigate("/CadastroPaciente?", {
      state: {
        ...infoPaciente,
        data_nascimento: infoPaciente?.data_nascimento
          .split("/")
          .reverse()
          .join("-"),
        validade_plano: infoPaciente?.validade_plano
          .split("/")
          .reverse()
          .join("-"),
      },
    });
  };

  const apagar = (idPaciente?: Number) => {
    if (
      window.confirm("Deseja realmente deletar este paciente? " + idPaciente)
    ) {
      Service.deletePacientes(idPaciente)
        .then(() => {
          window.alert("Excluido com sucesso");
          window.location.reload();
        })
        .catch((err) =>
          window.alert("Erro:" + JSON.stringify(err?.response?.data))
        );
    }
  };

  return (
    <>
      <Cabecalho nomeTela="Dados Paciente"></Cabecalho>

      <br />
      <br />

      <div className="row">
        <div className="col-md-3">
          <button onClick={() => encaminharParaCadastro()}>
            {" "}
            Novo Cadastro
          </button>
        </div>
        <div className="col-md-4">
          <label htmlFor="nome">Nome do Paciente</label>
          <input
            id="nome"
            type="text"
            value={nomePesquisado}
            onChange={(e) => setNomePesquisado(e.target.value)}
          />
        </div>
        <div className="col-md-1">
          <button onClick={handlePesquisarPorNome}>Pesquisar</button>
        </div>
      </div>

      <br></br>
      <br></br>
      <br></br>

      <table border={1}>
        <thead>
          <tr>
            <th style={{ width: "50%" }}>Nome Paciente</th>
            <th>Consultar</th>
            <th>Alterar</th>
            <th>Excluir Paciente</th>
          </tr>
        </thead>

        <tbody>
          {listaPacientes.map(function (Paciente) {
            return (
              <tr key={Paciente.id}>
                <td>{Paciente.nome}</td>
                <td>
                  <button
                    onClick={() =>
                      navigate("/VisualizarPaciente/" + Paciente.nome)
                    }
                  >
                    Consultar
                  </button>
                </td>
                <td>
                  <button onClick={() => encaminharParaCadastro(Paciente)}>
                    Alterar
                  </button>
                </td>
                <td>
                  <button onClick={() => apagar(Paciente.id)}>Excluir</button>
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </>
  );
};

export default Paciente;
