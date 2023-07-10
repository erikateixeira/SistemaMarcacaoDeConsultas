import { useEffect, useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { Cabecalho } from "./Componentes/Cabecalho";
import "./Css/Medicos.css";
import { Service } from "../Service";
import { IMedico } from "../Models/IMedico";
import { VisualizarMedico } from "./VisualizarMedico";

const Medico = function () {
  const navigate = useNavigate();
  const [listaMedicos, setListaMedicos] = useState<IMedico[]>([]);
  const [nomePesquisado, setNomePesquisado] = useState<string>("");

  useEffect(() => {
    document.title = "Dados Médico";
    Service.getMedicos().then((res) => {
      setListaMedicos(res.data);
    });
  }, []);

  const handlePesquisarPorNome = async () => {
    debugger;
    const { data } = await Service.getMedicosPorNome(nomePesquisado);
    setListaMedicos(data);
  };

  useEffect(() => {
    const searchParams = new URLSearchParams(location.search);
    const nome = searchParams.get("nome");

    if (nome) {
      setNomePesquisado(nome);
      Service.getMedicosPorNome(nome)
        .then((response) => {
          setListaMedicos(response.data);
        })
        .catch((error) => {
          console.log("Erro ao obter os médicos por nome:", error);
        });
    }
  }, [location.search]);

  const encaminharParaCadastro = (infoMedico?: IMedico) => {
    return navigate("/CadastroMedico?", {
      state: { ...infoMedico, senha: undefined },
    });
  };

  const apagar = (idMedico?: Number) => {
    if (window.confirm("Deseja realmente deletar este médico? " + idMedico)) {
      Service.deleteMedicos(idMedico)
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
      <Cabecalho nomeTela="Dados Médico"></Cabecalho>

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
          <label htmlFor="nome">Nome do Médico </label>
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
            <th style={{ width: "50%" }}>Nome Médico</th>
            <th>Consultar</th>
            <th>Alterar</th>
            <th>Excluir </th>
          </tr>
        </thead>

        <tbody>
          {listaMedicos.map(function (Medico) {
            return (
              <tr key={Medico.id}>
                <td>{Medico.nome}</td>
                <td>
                  <button
                    onClick={() => navigate("/VisualizarMedico/" + Medico.nome)}
                  >
                    Consultar
                  </button>
                </td>
                <td>
                  <button onClick={() => encaminharParaCadastro(Medico)}>
                    Alterar
                  </button>
                </td>
                <td>
                  <button onClick={() => apagar(Medico.id)}>Excluir</button>
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </>
  );
};

export default Medico;
