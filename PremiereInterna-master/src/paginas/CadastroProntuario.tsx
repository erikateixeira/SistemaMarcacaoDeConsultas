import { ChangeEvent, useEffect, useState } from "react";
import { IProntuario } from "../Models/IProntuario";
import { Service } from "../Service";
import { Cabecalho } from "./Componentes/Cabecalho";
import { CadastroPaciente } from "./CadastroPaciente";
import { Route, useParams, useNavigate } from "react-router-dom";
import { DadosConsulta } from "./Componentes/DadosConsulta";
import ReactPDF, {
  Page,
  View,
  Text,
  Document,
  StyleSheet,
  PDFViewer,
  PDFDownloadLink,
  BlobProvider,
} from "@react-pdf/renderer";
import { useLocation } from "react-router-dom";
import moment from "moment";
import { IConsulta } from "../Models/IConsulta";

type IniciarAtendimento = {
  id_consulta: number;
  nome_medico: string;
  crm_estado: string;
  crm_num: string;
  especialidade: string;
  data_consulta: any;
  hora_consulta: any;
  nome_paciente: string;
  cpf: string;
  passaporte: string;
  data_nascimento: any;
  telefone: string;
  email: string;
};

export const CadastroProntuario = () => {
  const navigate = useNavigate();
  const MyDocument = ({ styles, prontuario }) => (
    <Document>
      <Page size="A4" style={styles.page}>
        <View style={styles.section}>
          <Cabecalho nomeTela="ffffff"></Cabecalho>
          <Text style={{ marginBottom: 10 }}>PRIMIÈRE SANTÉ</Text>
          <Text style={{ marginBottom: 10 }}></Text>
          <Text style={{ marginBottom: 10 }}>PRONTUÁRIO</Text>
          <Text style={{ marginBottom: 5 }}>
            {" "}
            Nome Paciente: {ControleConsulta?.nome_paciente}
          </Text>
          <Text style={{ marginBottom: 5 }}> CPF: {ControleConsulta?.cpf}</Text>
          <Text style={{ marginBottom: 5 }}>
            {" "}
            Passaporte: {ControleConsulta?.passaporte}
          </Text>
          <Text style={{ marginBottom: 5 }}>
            {" "}
            Data de Nascimento: {ControleConsulta?.data_nascimento}
          </Text>
          <Text style={{ marginBottom: 5 }}>
            {" "}
            Telefone: {ControleConsulta?.telefone}
          </Text>
          <Text style={{ marginBottom: 5 }}>
            {" "}
            Email: {ControleConsulta?.email}
          </Text>
          <Text style={{ marginBottom: 5 }}>
            {" "}
            Data da Consulta: {ControleConsulta?.data_consulta}
          </Text>
          <Text style={{ marginBottom: 5 }}>
            {" "}
            Hora da Consulta: {ControleConsulta?.hora_consulta}
          </Text>
          <Text style={{ marginBottom: 5 }}>
            {" "}
            Especialidade: {ControleConsulta?.especialidade}
          </Text>
          <Text style={{ marginBottom: 5 }}>
            {" "}
            Nome do Médico: {ControleConsulta?.nome_medico}
          </Text>
          <Text style={{ marginBottom: 5 }}>
            {" "}
            CRM-UF: {ControleConsulta?.crm_estado}
          </Text>
          <Text style={{ marginBottom: 10 }}>
            {" "}
            N° CRM: {ControleConsulta?.crm_num}
          </Text>
          <Text style={{ marginBottom: 10 }}></Text>
          <Text style={{ marginBottom: 10 }}>
            Anamnese: {prontuario?.anamnese}{" "}
          </Text>
          <Text style={{ marginBottom: 10 }}></Text>
          <Text style={{ marginBottom: 10 }}>
            Prescrição Médica: {prontuario?.prescricaoMedica}{" "}
          </Text>
          <Text style={{ marginBottom: 10 }}></Text>
          <Text style={{ marginBottom: 10 }}>
            Exames Prescritos e Laudos de Exames: {prontuario?.examePrescritos}
          </Text>
        </View>
        <View style={styles.section}>
          <Text></Text>
        </View>
      </Page>
    </Document>
  );

  const { id_consulta } = useParams();
  const [ControleConsulta, setControleConsulta] =
    useState<IniciarAtendimento>();
  const location = useLocation();

  useEffect(() => {
    document.title = "Dados da Consulta";
    Service.getIniciar(Number(id_consulta)).then((result) => {
      setControleConsulta(result.data);
    });
  }, []);

  /*useEffect(() => {
    document.title = "Cadastro de Prontuário";
    setProntuario({
      ...prontuario,
      consulta: {
        ...location.state,
        data_consulta: moment(
          location.state.data_consulta,
          "DD/MM/YYYY"
        ).toDate(),
      },
    });
  }, []);*/

  const onChange = (
    ev: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    const objProntuario = prontuario ?? {};

    const newValue = ev.target.value;
    const field = ev.target.name;

    const newObject = {
      ...objProntuario,
      [field]: newValue,
    };

    setProntuario(newObject);
  };

  const [prontuario, setProntuario] = useState<IProntuario>();
  const [pdf, setPdf] = useState<Blob | null>(null);
  const [visivel, setVisivel] = useState<Boolean>(false);
  const [mostrarPDF, setMostrarPDF] = useState(false);

  const registrar = async () => {
    if (
      prontuario &&
      window.confirm(
        "Deseja realmente registrar este Paciente? " +
          JSON.stringify(prontuario)
      )
    ) {
      Service.PostProntuario({
        ...prontuario,
        file: pdf as Blob,
      });

      /*
      const pdf = await ReactPDF.renderToStream(<MyDocument />);

      const chunks: any[] = [];

      pdf.on("data", (chunk: any) => {
        chunks.push(chunk);
      });

      pdf.on("end", () => {
        debugger;
        //const buffer = Buffer.concat(chunks);
        const base64String = buffer.toString("base64");
        console.log(base64String); // ou faça o que desejar com a string Base64
      });*/
      /*
      const p = {
        ...prontuario,
        file: ,
      };
      const res = await Service.PostProntuario(prontuario);

      if (res.status == 200)
        window.alert("Atualiazado com sucesso");
      else
          window.alert("Erro:" + JSON.stringify(err?.response?.data))
        */
    }
  };
  const styles = StyleSheet.create({
    page: {
      flexDirection: "row",
      backgroundColor: "#E4E4E4",
    },
    section: {
      margin: 10,
      padding: 10,
      flexGrow: 1,
    },
  });

  return (
    <>
      <Cabecalho nomeTela="Cadastro de Prontuário"> </Cabecalho>
      <div className="row">
        <div className="col-md-6" style={{ overflow: "scroll", height: 600 }}>
          <fieldset>
            <legend> Dados da Consulta </legend>
            <form className="row g-3">
              <div className="col-md-6">
                <label htmlFor="inputNomePaciente" className="form-label">
                  Nome do Paciente
                </label>
                <input
                  type="text"
                  name="nome_paciente"
                  value={ControleConsulta?.nome_paciente}
                  className="form-control"
                  id="inputNomePaciente"
                  disabled
                />
              </div>

              <div className="col-md-3">
                <label htmlFor="inputCpfPaciente" className="form-label">
                  CPF
                </label>
                <input
                  type="text"
                  name="cpf_paciente"
                  value={ControleConsulta?.cpf}
                  className="form-control"
                  id="inputCpfPaciente"
                  disabled
                />
              </div>

              <div className="col-md-3">
                <label htmlFor="inputPassaporte" className="form-label">
                  Passaporte
                </label>
                <input
                  type="text"
                  name="passaporte_paciente"
                  value={ControleConsulta?.passaporte}
                  className="form-control"
                  id="inputPassaporte"
                  disabled
                />
              </div>

              <div className="col-md-4">
                <label htmlFor="inputDataNascimento" className="form-label">
                  Data de Nascimento
                </label>
                <input
                  type="text"
                  name="data_nascimento"
                  value={ControleConsulta?.data_nascimento}
                  className="form-control"
                  id="inputDataNascimento"
                  disabled
                />
              </div>

              <div className="col-md-4">
                <label htmlFor="inputTelefone" className="form-label">
                  Telefone
                </label>
                <input
                  type="text"
                  name="telefone"
                  value={ControleConsulta?.telefone}
                  className="form-control"
                  id="inputTelefone"
                  disabled
                />
              </div>

              <div className="col-md-4">
                <label htmlFor="inputEmail" className="form-label">
                  Email
                </label>
                <input
                  type="text"
                  name="email"
                  value={ControleConsulta?.email}
                  className="form-control"
                  id="inputEmail"
                  disabled
                />
              </div>

              <div className="col-md-4">
                <label htmlFor="inputDataConsulta" className="form-label">
                  Data da Consulta
                </label>
                <input
                  type="text"
                  name="data_consulta"
                  value={ControleConsulta?.data_consulta}
                  className="form-control"
                  id="inputDataConsulta"
                  disabled
                />
              </div>

              <div className="col-md-4">
                <label htmlFor="inputHoraConsulta" className="form-label">
                  Hora da Consulta
                </label>
                <input
                  type="text"
                  name="hora_consulta"
                  value={ControleConsulta?.hora_consulta}
                  className="form-control"
                  id="inputHoraConsulta"
                  disabled
                />
              </div>

              <div className="col-md-4">
                <label htmlFor="inputEspecialidade" className="form-label">
                  Especialidade
                </label>
                <input
                  type="text"
                  name="especialidade"
                  value={ControleConsulta?.especialidade}
                  className="form-control"
                  id="inputEspecialidade"
                  disabled
                />
              </div>

              <div className="col-md-6">
                <label htmlFor="inputNomeMedico" className="form-label">
                  Nome do Médico
                </label>
                <input
                  type="text"
                  name="nome_medico"
                  value={ControleConsulta?.nome_medico}
                  className="form-control"
                  id="inputNomeMedico"
                  disabled
                />
              </div>

              <div className="col-md-3">
                <label htmlFor="inputCrmEstado" className="form-label">
                  CRM-UF
                </label>
                <input
                  type="text"
                  name="crm_estado"
                  value={ControleConsulta?.crm_estado}
                  className="form-control"
                  id="inputCrmEstado"
                  disabled
                />
              </div>

              <div className="col-md-3">
                <label htmlFor="inputCrmNum" className="form-label">
                  nº CRM
                </label>
                <input
                  type="text"
                  name="crm_numero"
                  value={ControleConsulta?.crm_num}
                  className="form-control"
                  id="inputCrmNum"
                  disabled
                />
              </div>
            </form>
          </fieldset>

          <fieldset>
            <legend> Anamnese </legend>
            <textarea
              id=""
              name="anamnese"
              onChange={onChange}
              cols={100}
              rows={10}
            ></textarea>
          </fieldset>

          <fieldset>
            <legend> Prescrição Médica </legend>
            <textarea
              name="prescricaoMedica"
              onChange={onChange}
              id=""
              cols={100}
              rows={10}
            ></textarea>
          </fieldset>

          <fieldset>
            <legend> Exames Prescritos/Laudos de Exames </legend>
            <textarea
              name="examePrescritos"
              id=""
              cols={100}
              rows={10}
              onChange={onChange}
            ></textarea>
          </fieldset>

          <div className="col-12">
            <button
              id="btncontato"
              className="btn btn-info rounded-pill px-3"
              onClick={() => {
                registrar();
              }}
              type="button"
              disabled={!pdf || !visivel}
            >
              Registrar Prontuário
            </button>
            <button
              id="btncontato"
              className="btn btn-info rounded-pill px-3"
              onClick={() => {
                setVisivel(true);
              }}
              type="button"
            >
              Visualizar Prontuário
            </button>
          </div>
        </div>
        <div className="col-md-6">
          <PDFViewer>
            <MyDocument styles={styles} prontuario={prontuario} />
          </PDFViewer>
        </div>

        {visivel ? (
          <>
            <PDFDownloadLink
              document={<MyDocument styles={styles} prontuario={prontuario} />}
              fileName={"prontuario.pdf"}
            >
              {({ blob, url, loading, error }) =>
                loading ? "Download documento!" : "Download documento!"
              }
            </PDFDownloadLink>

            <div>
              <BlobProvider
                document={
                  <MyDocument styles={styles} prontuario={prontuario} />
                }
              >
                {({ blob, url, loading, error }) => {
                  setPdf(blob);
                  // Do whatever you need with blob here
                  return <div></div>;
                }}
              </BlobProvider>
            </div>
          </>
        ) : null}
      </div>
    </>
  );
};
