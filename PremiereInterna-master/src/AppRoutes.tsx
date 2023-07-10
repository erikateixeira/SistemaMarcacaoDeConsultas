import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Login } from "./paginas/Login";
import { PaginaMedico } from "./paginas/PaginaMedico";
import ControleMedico from "./paginas/ControleMedico";
import { CadastroProntuario } from "./paginas/CadastroProntuario";
import { CadastroPaciente } from "./paginas/CadastroPaciente";
import { CadastroFuncionario } from "./paginas/CadastroFuncionario";
import { CadastroMedico } from "./paginas/CadastroMedico";
import { CriarConsulta } from "./paginas/CriarConsulta";
import { Admin } from "./paginas/Admin";
import Funcionarios from "./paginas/Funcionarios";
import Medico from "./paginas/Medico";
import Paciente from "./paginas/Paciente";
import ControleConsulta from "./paginas/ControleConsulta";
import { AbrirAtendimento } from "./paginas/AbrirAtendimento";
import { Recepcionista } from "./paginas/Recepcionista";
import { VisualizarFuncionario } from "./paginas/VisualizarFuncionario";
import { VisualizarMedico } from "./paginas/VisualizarMedico";
import { VisualizarPaciente } from "./paginas/VisualizarPaciente";
import { VisualizarConsulta } from "./paginas/VisualizarConsulta";
import { RemarcarConsulta } from "./paginas/RemarcarConsulta";
import { DadosConsulta } from "./paginas/Componentes/DadosConsulta";

const AppRoutes = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route Component={Login} path="/" />
        <Route Component={PaginaMedico} path="/PaginaMedico" />
        <Route Component={DadosConsulta} path="/DadosConsulta/:id_consulta" />
        <Route Component={ControleMedico} path="/ControleMedico" />
        <Route
          Component={CadastroProntuario}
          path="/CadastroProntuario/:id_consulta"
        />
        <Route Component={CadastroPaciente} path="/CadastroPaciente" />
        <Route Component={CadastroFuncionario} path="/CadastroFuncionario" />
        <Route Component={CadastroMedico} path="/CadastroMedico" />
        <Route Component={CriarConsulta} path="/CriarConsulta" />

        <Route
          Component={RemarcarConsulta}
          path="/RemarcarConsulta/:id_consulta"
        />
        <Route Component={Funcionarios} path="/Funcionarios" />
        <Route
          Component={VisualizarFuncionario}
          path="/VisualizarFuncionario/:nome"
        />
        <Route Component={Medico} path="/Medico" />
        <Route Component={VisualizarMedico} path="/VisualizarMedico/:nome" />

        <Route Component={Paciente} path="/Paciente" />
        <Route
          Component={VisualizarPaciente}
          path="/VisualizarPaciente/:nome"
        />
        <Route
          Component={VisualizarConsulta}
          path="/VisualizarConsulta/:id_consulta"
        />

        <Route Component={Admin} path="/Admin" />
        <Route Component={ControleConsulta} path="/ControleConsulta" />
        <Route
          Component={AbrirAtendimento}
          path="/AbrirAtendimento/:id_consulta"
        />
        <Route Component={Recepcionista} path="/Recepcionista" />
      </Routes>
    </BrowserRouter>
  );
};

export default AppRoutes;
