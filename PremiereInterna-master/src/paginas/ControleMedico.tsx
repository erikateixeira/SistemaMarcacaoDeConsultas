import { useEffect, useState } from "react";
import { Cabecalho } from "./Componentes/Cabecalho";
import { Linha } from "./Componentes/Linha";
import { useNavigate, useLocation } from "react-router-dom";
import { Service } from "../Service";
import { IConsulta } from "../Models/IConsulta";
import { DataSimplificada } from "../Models/DataSimplificada";

const ControleMedico = function () {
  const navigate = useNavigate();
  const location = useLocation();
  const [listaControleConsulta, setListaControleConsulta] = useState<
    IConsulta[]
  >([]);
  const [dataPesquisada, setDataPesquisada] = useState<Date>(new Date());
  const [dataString, setDataString] = useState<string>("");
  const [nome, setNome] = useState<string>("");

  useEffect(() => {
    const obterNomeUsuario = async () => {
      const res = await Service.getMyUsuario();
      const { nome } = res.data;
      setNome(nome);
    };
    obterNomeUsuario();
  }, []);

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
    document.title = "Controle Médico";
    Service.getConsultaLista(nome, dataFormatada).then((res) => {
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

    const { data } = await Service.getConsultaLista(nome, dataString);
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

      Service.getConsultaLista(nome, dia)
        .then((response) => {
          setListaControleConsulta(response.data);
        })
        .catch((error) => {
          console.log("Erro ao obter as consultas por data:", error);
        });
    }
  }, [location.search]);

  const formatarData = (data: string) => {
    const partesData = data.split("-");
    const dataFormatada =
      partesData.length === 3
        ? `${partesData[2]}/${partesData[1]}/${partesData[0]}`
        : data;
    return dataFormatada;
  };

  const encaminharParaCadastro = (infoConsulta?: IConsulta) => {
    return navigate("/CadastroProntuario?", {
      state: { ...infoConsulta, id_consulta: 1 },
    });
  };

  return (
    <>
      <Cabecalho nomeTela="Controle Médico"></Cabecalho>

      <br />
      <br />

      <div className="row">
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
            <th>Data da Consulta</th>
            <th>Hora da Consulta</th>
            <th>Iniciar Consulta</th>
          </tr>
        </thead>

        <tbody>
          {listaControleConsulta
            .sort((a, b) => a.hora_consulta.localeCompare(b.hora_consulta))
            .map(function (ControleConsulta) {
              return (
                <tr key={ControleConsulta.id_consulta}>
                  <td>{ControleConsulta.nome_paciente}</td>
                  <td>
                    {ControleConsulta.data_consulta
                      ? formatarData(ControleConsulta.data_consulta)
                      : ""}
                  </td>

                  <td>{ControleConsulta.hora_consulta}</td>

                  <td>
                    <button
                      onClick={() =>
                        navigate(
                          "/CadastroProntuario/" + ControleConsulta.id_consulta
                        )
                      }
                    >
                      Iniciar Consulta
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

export default ControleMedico;
