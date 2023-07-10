import './App.css';
import { Navegacao } from './componentes/Navegacao/Navegacao';
import { Footer } from './componentes/Footer/Footer';
import AppRoutes from './AppRoutes';

function App() {
  return (
    <>
      <div className='app-container'>
        <Navegacao></Navegacao>
        <AppRoutes></AppRoutes>
        <Footer></Footer>
      </div>
    </>
  );
}

export default App;
