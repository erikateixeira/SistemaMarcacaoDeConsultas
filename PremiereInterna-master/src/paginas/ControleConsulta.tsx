import { useEffect, useState } from "react";
import { useNavigate, useLocation, useParams } from "react-router-dom";
import { Cabecalho } from "./Componentes/Cabecalho";
import "./Css/ControleConsulta.css";
import "./Css/Botao.css";
import { Service } from "../Service";
import { IConsulta } from "../Models/IConsulta";
import { DataSimplificada } from "../Models/DataSimplificada";
import { VisualizarConsulta } from "./VisualizarConsulta";
import { useConfirmacao } from "../ConfirmacaoContext";

/*import moment from "moment";*/

const ControleConsulta = function () {
  const navigate = useNavigate();
  const location = useLocation();
  const { id_consulta } = useParams();
  const [listaControleConsulta, setListaControleConsulta] = useState<
    IConsulta[]
  >([]);
  const [dataPesquisada, setDataPesquisada] = useState<Date>(new Date());
  const [dataString, setDataString] = useState<string>("");
  const { confirmacao, setConfirmacaoIdConsulta } = useConfirmacao();

  const dataAtual = new Date();
  const [dataSimplificada, setDataSimplificada] = useState<DataSimplificada>({
    dia: dataAtual.getUTCDate(),
    mes: dataAtual.getUTCMonth() + 1,
    ano: dataAtual.getUTCFullYear(),
  });

  const obterDataFormatada = (data: DataSimplificada) => {
    const { dia, mes, ano } = data;
    const diaFormatado = dia.toString().padStart(2, "0");
    const mesFormatado = mes.toString().padStart(2, "0");
    return `${diaFormatado}/${mesFormatado}/${ano}`;
  };

  const dataFormatada = obterDataFormatada(dataSimplificada);

  useEffect(() => {
    document.title = "Controle Consulta";
    Service.getConsultaDia(dataFormatada).then((res) => {
      setListaControleConsulta(res.data);
    });
  }, []);

  const handlePesquisarPorData = async () => {
    const dia = dataPesquisada.getUTCDate();
    const mes = dataPesquisada.getUTCMonth() + 1;
    const ano = dataPesquisada.getUTCFullYear();
    const dataString = `${dia.toString().padStart(2, "0")}/${mes
      .toString()
      .padStart(2, "0")}/${ano}`;

    const { data } = await Service.getConsultaDia(dataString);
    setListaControleConsulta(data);
  };

  useEffect(() => {
    const searchParams = new URLSearchParams(location.search);
    const dia = searchParams.get("dia");

    if (dia) {
      setDataString(dia);

      const [diaValue, mesValue, anoValue] = dia.split("/");
      const dataSelecionada = new Date(
        parseInt(anoValue),
        parseInt(mesValue) - 1,
        parseInt(diaValue)
      );
      setDataPesquisada(dataSelecionada);

      Service.getConsultaDia(dia)
        .then((response) => {
          setListaControleConsulta(response.data);
        })
        .catch((error) => {
          console.log("Erro ao obter as consultas por data:", error);
        });
    }
  }, [location.search]);

  const encaminharParaCriarConsulta = (infoConsulta?: IConsulta) => {
    return navigate("/CriarConsulta", {
      state: {
        ...infoConsulta,
      },
    });
  };

  const apagar = (id_consulta?: Number) => {
    if (window.confirm("Deseja realmente apagar esta consulta?")) {
      Service.deleteConsulta(id_consulta)
        .then(() => {
          window.alert("Excluido com sucesso");
          window.location.reload();
        })
        .catch((err) =>
          window.alert("Erro:" + JSON.stringify(err?.response?.data))
        );
    }
  };

  /*useEffect(() => {
    const obterConfirmacao = async () => {
      const res = await Service.getConfirmacaoBoolean(Number(id_consulta));
      const { confirmacao } = res.data;
      setConfirmacao(confirmacao);
    };
    obterConfirmacao();
  }, [id_consulta]);*/

  const formatarData = (data: string) => {
    const partesData = data.split("-");
    const dataFormatada =
      partesData.length === 3
        ? `${partesData[2]}/${partesData[1]}/${partesData[0]}`
        : data;
    return dataFormatada;
  };

  return (
    <>
      <Cabecalho nomeTela=""></Cabecalho>
      <div className="col-12 navegacao">
        <h1>Controle de Consulta</h1>
      </div>
      <br />
      <br />
      <div className="row">
        <div className="col-md-3">
          <button onClick={() => encaminharParaCriarConsulta()}>
            {" "}
            Nova Consulta
          </button>
        </div>
        <div className="col-md-3">
          <label htmlFor="data">Data</label>
          <input
            id="data"
            type="date"
            value={dataPesquisada.toISOString().split("T")[0]}
            onChange={(e) => setDataPesquisada(new Date(e.target.value))}
          />
        </div>
        <div className="col-md-1">
          <button onClick={handlePesquisarPorData}>Pesquisar</button>
        </div>
      </div>
      <br></br>
      <br></br>
      <br></br>

      <table border={1}>
        <thead>
          <tr>
            <th>Nome Paciente</th>
            <th>Nome do Médico</th>
            <th> Data da Consulta</th>
            <th> Hora da Consulta</th>
            <th> Confirmação </th>
            <th> Abrir atendimento</th>
            <th> Cancelar </th>
          </tr>
        </thead>

        <tbody>
          {listaControleConsulta
            .sort((a, b) => a.hora_consulta.localeCompare(b.hora_consulta))
            .map(function (ControleConsulta) {
              return (
                <tr key={ControleConsulta.id_consulta}>
                  <td>{ControleConsulta.nome_paciente}</td>
                  <td>{ControleConsulta.nome_medico}</td>
                  <td>
                    {ControleConsulta.data_consulta
                      ? formatarData(ControleConsulta.data_consulta)
                      : ""}
                  </td>
                  <td>{ControleConsulta.hora_consulta}</td>

                  <td>
                    <button
                      className={`${
                        ControleConsulta.confirmacao !== null &&
                        ControleConsulta.confirmacao
                          ? "button.green-button"
                          : "button-default"
                      }`}
                      onClick={() =>
                        navigate(
                          "/VisualizarConsulta/" + ControleConsulta.id_consulta
                        )
                      }
                    >
                      Confirmação
                    </button>
                  </td>

                  <td>
                    <button
                      onClick={() =>
                        navigate(
                          "/AbrirAtendimento/" + ControleConsulta.id_consulta
                        )
                      }
                    >
                      Abrir atendimento
                    </button>
                  </td>

                  <td>
                    <button
                      onClick={() => apagar(ControleConsulta?.id_consulta)}
                    >
                      Cancelar
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

export default ControleConsulta;
