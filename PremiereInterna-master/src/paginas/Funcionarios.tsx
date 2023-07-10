import { useEffect, useState } from "react";
import moment from "moment";
import { useLocation, useNavigate } from "react-router-dom";
import { Cabecalho } from "./Componentes/Cabecalho";
import "./Css/Funcionarios.css";
import { Service } from "../Service";
import { IFuncionario } from "../Models/IFuncionario";
import { VisualizarFuncionario } from "./VisualizarFuncionario";

const Funcionarios = function () {
  const navigate = useNavigate();
  const location = useLocation();
  const [listaFuncionarios, setListaFuncionarios] = useState<IFuncionario[]>(
    []
  );
  const [nomePesquisado, setNomePesquisado] = useState<string>("");

  useEffect(() => {
    document.title = "Dados Funcionário";
    Service.getFuncionarios().then((res) => {
      setListaFuncionarios(res.data);
    });
  }, []);

  const handlePesquisarPorNome = async () => {
    const { data } = await Service.getFuncionariosPorNome(nomePesquisado);
    setListaFuncionarios(data);
  };

  useEffect(() => {
    const searchParams = new URLSearchParams(location.search);
    const nome = searchParams.get("nome");

    if (nome) {
      setNomePesquisado(nome);
      Service.getFuncionariosPorNome(nome)
        .then((response) => {
          setListaFuncionarios(response.data);
        })
        .catch((error) => {
          console.log("Erro ao obter os funcionários por nome:", error);
        });
    }
  }, [location.search]);

  const encaminharParaCadastro = (infoFuncionario?: IFuncionario) => {
    return navigate("/CadastroFuncionario", {
      state: {
        ...infoFuncionario,
        senha: undefined,
        data_nascimento: infoFuncionario?.data_nascimento
          .split("/")
          .reverse()
          .join("-"),
      },
    });
  };

  const apagar = (Idfuncionario?: Number) => {
    if (
      window.confirm(
        "Deseja realmente deletar este Funcionário? " + Idfuncionario
      )
    ) {
      Service.deleteFuncionarios(Idfuncionario)
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
      <Cabecalho nomeTela="Dados Funcionários"></Cabecalho>

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
          <label htmlFor="nome">Nome do Funcionário</label>
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
            <th style={{ width: "50%" }}>Nome Funcionário</th>
            <th> Consultar </th>
            <th> Alterar </th>
            <th> Excluir </th>
          </tr>
        </thead>

        <tbody>
          {listaFuncionarios.map(function (funcionario) {
            return (
              <tr key={funcionario.id}>
                <td>{funcionario.nome}</td>
                <td>
                  <button
                    onClick={() =>
                      navigate("/VisualizarFuncionario/" + funcionario.nome)
                    }
                  >
                    Consultar
                  </button>
                </td>
                <td>
                  <button onClick={() => encaminharParaCadastro(funcionario)}>
                    Alterar
                  </button>
                </td>
                <td>
                  <button onClick={() => apagar(funcionario?.id)}>
                    Excluir
                  </button>
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </>
  );
};

export default Funcionarios;
