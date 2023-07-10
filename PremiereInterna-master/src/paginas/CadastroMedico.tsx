import moment from "moment";
import {
  ChangeEvent,
  JSXElementConstructor,
  Key,
  ReactElement,
  ReactNode,
  ReactPortal,
  useEffect,
  useState,
} from "react";
import { Cabecalho } from "./Componentes/Cabecalho";
import { IMedico } from "../Models/IMedico";
import { useLocation, useNavigate } from "react-router-dom";
import { Service } from "../Service";

export const CadastroMedico = () => {
  const location = useLocation();
  const [medico, setMedico] = useState<IMedico>({
    diasDisponiveis: [] as string[],
  });

  useEffect(() => {
    document.title = "Cadastro de Médico";
    setMedico(location.state);
  }, []);

  const onChange = (ev: ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const objMedico = medico ?? {};
    const newValue = ev.target.value;
    const field = ev.target.name;

    const newObject = {
      ...objMedico,
      [field]: newValue,
    };

    setMedico(newObject);
  };

  const onChangeDiasDisponiveis = (ev: ChangeEvent<HTMLInputElement>) => {
    const newValue = ev.target.value;
    const diasDisponiveis = newValue.split(",").map((dia) => dia.trim());

    setMedico((prevMedico) => ({
      ...prevMedico,
      diasDisponiveis,
    }));
  };

  const handleChangeTel = (event: any) => {
    const Tel = event.target.value.replace(/\D/g, "");
    const telWithMask = Tel.replace(
      /(\d{2})(9)(\d{4})(\d{4})/,
      "($1) $2 $3-$4"
    );
    const newValue = telWithMask;
    const field = event.target.name;

    const newObject = {
      ...medico,
      [field]: newValue,
    };

    setMedico(newObject);
    // Use cpfWithMask as needed (e.g., update state or perform further operations)
  };

  const handleChangeCNPJ = (event: any) => {
    const cnpj = event.target.value.replace(/\D/g, "");
    const cnpjWithMask = cnpj.replace(
      /(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/,
      "$1.$2.$3/$4-$5"
    );
    const newValue = cnpjWithMask;
    const field = event.target.name;

    const newObject = {
      ...medico,
      [field]: newValue,
    };

    setMedico(newObject);
    // Use cpfWithMask as needed (e.g., update state or perform further operations)
  };

  const validate = () => {
    if (medico?.cnpj == "" || medico?.cnpj == null) {
      window.alert("O campo CNPJ é obrigatório");
      return false;
    }

    if (medico?.nome == "" || medico?.nome == null) {
      window.alert("O campo do nome é obrigatório");
      return false;
    }

    if (medico?.crm_estado == "" || medico?.crm_estado == null) {
      window.alert("O campo crm_estado é obrigatório");
      return false;
    }

    if (medico?.crm_num == "" || medico?.crm_num == null) {
      window.alert("O campo crm_num é obrigatório");
      return false;
    }

    if (medico?.telefone == "" || medico?.telefone == null) {
      window.alert("O campo do telefone é obrigatório");
      return false;
    }

    if (medico?.email == "" || medico?.email == null) {
      window.alert("O campo email é obrigatório");
      return false;
    }

    if (medico?.especialidade == "" || medico?.especialidade == null) {
      window.alert("O campo especialidade é obrigatório");
      return false;
    }

    if (medico?.sala == "" || medico?.sala == null) {
      window.alert("O campo sala é obrigatório");
      return false;
    }

    if (medico?.login == "" || medico?.login == null) {
      window.alert("O campo login é obrigatório");
      return false;
    }

    if (medico?.diasDisponiveis == null) {
      window.alert("O campo dias disponíveis é obrigatório");
      return false;
    }

    if (medico?.hora_inicial == "" || medico?.hora_inicial == null) {
      window.alert("O campo hora inicial é obrigatório");
      return false;
    }

    if (medico?.hora_final == "" || medico?.hora_final == null) {
      window.alert("O campo hora final é obrigatório");
      return false;
    }

    if (medico?.valor_consulta == null) {
      window.alert("O campo valor consulta é obrigatório");
      return false;
    }

    if (medico?.id ?? 0 > 0) {
      return true;
    }

    return true;
  };

  const navigate = useNavigate();

  const registrar = () => {
    if (medico && validate()) {
      Service.getMedicos().then(() => {
        navigate("/Medico");
      });
    }
    if (
      medico &&
      validate() &&
      window.confirm(
        "Deseja realmente cadastrar este médico? " + JSON.stringify(medico)
      )
    ) {
      const postMedico = {
        ...medico,
        diasDisponiveis: Array.isArray(medico.diasDisponiveis)
          ? medico.diasDisponiveis
          : [medico.diasDisponiveis],
      };
      if (medico?.id ?? 0 > 0) {
        Service.PutMedicos(postMedico)
          .then(() => {
            window.alert("Atualizado com sucesso");
            navigate("/Medico");
          })
          .catch((err) =>
            window.alert(
              err?.response?.data
                .map((error: { message: any }) => error.message)
                .join("\n")
            )
          );
      } else {
        Service.PostMedicos(postMedico)
          .then(() => {
            window.alert("Cadastrado com sucesso");
            navigate("/Medico");
          })
          .catch((err) =>
            window.alert(
              err?.response?.data
                .map((error: { message: any }) => error.message)
                .join("\n")
            )
          );
      }
    }
  };

  return (
    <>
      <Cabecalho nomeTela="Dados Médico"></Cabecalho>
      <div className="col-12 navegacao">
        <h1>Cadastro do Médico</h1>
      </div>
      <form className="row g-3">
        <div className="col-md-6">
          <label htmlFor="inputNome" className="form-label">
            Nome completo
          </label>
          <input
            maxLength={80}
            minLength={10}
            name="nome"
            type="text"
            className="form-control"
            id="inputNome"
            value={medico?.nome}
            onChange={onChange}
          />
        </div>
        <div className="col-md-2">
          <label htmlFor="inputCnpj" className="form-label">
            CNPJ
          </label>
          <input
            maxLength={14}
            minLength={14}
            name="cnpj"
            type="text"
            className="form-control"
            id="inputCnpj"
            value={medico?.cnpj}
            onChange={handleChangeCNPJ}
          />
        </div>
        <div className="col-md-2">
          <label htmlFor="inputCrm" className="form-label">
            Número CRM
          </label>
          <input
            maxLength={6}
            minLength={6}
            name="crm_num"
            type="text"
            value={medico?.crm_num}
            className="form-control"
            id="inputCrm"
            onChange={onChange}
          />
        </div>

        <div className="col-md-2">
          <label htmlFor="inputCrmEstado" className="form-label">
            Estado CRM
          </label>

          <select
            name="crm_estado"
            value={medico?.crm_estado}
            className="form-control"
            id="inputCrmEstado"
            onChange={onChange}
          >
            <option value="">Selecionar...</option>
            <option>AC</option>
            <option>AL</option>
            <option>AP</option>
            <option>AM</option>
            <option>BA</option>
            <option>CE</option>
            <option>DF</option>
            <option>ES</option>
            <option>GO</option>
            <option>MA</option>
            <option>MT</option>
            <option>MS</option>
            <option>MG</option>
            <option>PA</option>
            <option>PB</option>
            <option>PR</option>
            <option>PE</option>
            <option>PI</option>
            <option>RJ</option>
            <option>RN</option>
            <option>RS</option>
            <option>RO</option>
            <option>RR</option>
            <option>SC</option>
            <option>SP</option>
            <option>SE</option>
            <option>TO</option>
          </select>
        </div>
        <div className="col-2">
          <label htmlFor="inputelefone" className="form-label">
            Telefone
          </label>
          <input
            maxLength={11}
            minLength={11}
            name="telefone"
            type="text"
            value={medico?.telefone}
            className="form-control"
            id="inputtelefone"
            onChange={handleChangeTel}
          />
        </div>

        <div className="col-5">
          <label htmlFor="inputemail" className="form-label">
            Email
          </label>
          <input
            name="email"
            type="email"
            value={medico?.email}
            className="form-control"
            id="inputemail"
            onChange={onChange}
          />
        </div>

        <div className="col-md-3">
          <label htmlFor="inputEspecialidade" className="form-label">
            Especialidade
          </label>
          <select
            name="especialidade"
            value={medico?.especialidade}
            className="form-control"
            id="inputEspecialidade"
            onChange={onChange}
          >
            <option value="">Selecionar...</option>
            <option>CARDIOLOGISTA</option>
            <option>DERMATOLOGISTA</option>
            <option>GINECOLOGISTA</option>
          </select>
        </div>

        <div className="col-2">
          <label htmlFor="inputNumeroSala" className="form-label">
            Número Sala
          </label>
          <select
            name="sala"
            value={medico?.sala}
            className="form-control"
            id="inputNumeroSala"
            onChange={onChange}
          >
            <option value="">Selecionar...</option>
            <option>01</option>
            <option>02</option>
            <option>03</option>
            <option>04</option>
            <option>05</option>
            <option>06</option>
          </select>
        </div>
        <div className="col-3">
          <label htmlFor="inputLogin" className="form-label">
            Login
          </label>
          <input
            maxLength={30}
            name="login"
            type="text"
            value={medico?.login}
            className="form-control"
            id="inputLogin"
            onChange={onChange}
          />
        </div>

        <div className="col-2">
          <label htmlFor="inputSenha" className="form-label">
            Senha
          </label>
          <input
            maxLength={15}
            name="senha"
            type="password"
            className="form-control"
            id="inputSenha"
            onChange={onChange}
          />
        </div>

        <div className="col-2">
          <label htmlFor="inputHoraDisponivelInicial" className="form-label">
            Hora Disponível Inicial{" "}
          </label>
          <input
            name="hora_inicial"
            type="time"
            value={medico?.hora_inicial}
            step="1"
            className="form-control"
            id="inputHoraDisponivelInicial"
            onChange={onChange}
          />
        </div>
        <div className="col-3">
          <label htmlFor="inputHoraDisponivelFinal" className="form-label">
            Hora Disponível Final
          </label>
          <input
            name="hora_final"
            type="time"
            value={medico?.hora_final}
            step="1"
            className="form-control"
            id="inputHoraDisponivelFinal"
            onChange={onChange}
          />
        </div>
        <div className="col-md-2">
          <label htmlFor="inputValorConsulta" className="form-label">
            Valor Consulta
          </label>
          <input
            name="valor_consulta"
            type="number"
            value={medico?.valor_consulta}
            className="form-control"
            id="inputValorConsulta"
            onChange={onChange}
          />
        </div>
        <div className="col-12">
          <label htmlFor="inputDiasDisponiveis" className="form-label">
            Dias das Semanas Disponíveis
          </label>
          <input
            name="diasDisponiveis"
            type="text"
            value={
              medico?.diasDisponiveis ? medico.diasDisponiveis.join(", ") : ""
            }
            className="form-control"
            id="inputDiasDisponiveis"
            onChange={onChangeDiasDisponiveis}
            placeholder="SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA"
          />
        </div>
        <div className="col-md-2" />
        <div className="col-12">
          <button
            id="btncontato"
            className="btn btn-info rounded-pill px-3"
            onClick={registrar}
            type="button"
          >
            Registrar
          </button>
        </div>
      </form>
    </>
  );
};
