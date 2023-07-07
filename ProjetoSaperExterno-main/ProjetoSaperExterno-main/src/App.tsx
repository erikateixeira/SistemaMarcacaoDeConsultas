import { useState } from 'react';
import reactLogo from './assets/react.svg';
import viteLogo from '/vite.svg';
import './App.css';
import { Navegacao } from './componentes/Navegacao/Navegacao';
import { Footer } from './componentes/Footer/Footer';
import { Container } from 'react-bootstrap';
import AppRoutes from './AppRoutes';

function App() {
  return (
    <>
      <Navegacao></Navegacao>
      <AppRoutes></AppRoutes>
      <Footer></Footer>
    </>
  );
}

export default App;
