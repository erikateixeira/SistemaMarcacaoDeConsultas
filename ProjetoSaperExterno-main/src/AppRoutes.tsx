import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { Home } from './paginas/Home';
import { Sobre } from './paginas/Sobre';
import { Especialidades } from './paginas/Especialidades';
import { EspecialidadesCardiologia } from './paginas/EspecialidadesCardiologia';
import { EspecialidadesDermatologia } from './paginas/EspecialidadesDermatologia';
import { EspecialidadesGinecologia } from './paginas/EspecialidadesGinecologia';
import { Convenios } from './paginas/Convenios';
import { Contato } from './paginas/Contato';

const AppRoutes = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route Component={Home} path="/" />
        <Route Component={Sobre} path="/sobre" />
        <Route Component={Especialidades} path="/especialidades" />
        <Route Component={EspecialidadesCardiologia} path="/cardiologia" />
        <Route Component={EspecialidadesDermatologia} path="/dermatologia" />
        <Route Component={EspecialidadesGinecologia} path="/ginecologia" />
        <Route Component={Convenios} path="/convenios" />
        <Route Component={Contato} path="/contato" />
      </Routes>
    </BrowserRouter>
  );
};

export default AppRoutes;
