import './Empresa.css';
export const Empresa = () => {
  return (
    <div className="empresa">
      <h2>Sobre a Empresa</h2>
      <p>
        A Clínica Première Santé foi fundada em julho de 2023, com um excelente
        corpo clínico formado por médicos de diferentes estados, atendendo as
        especialidades Cardiologia, Dermatologia e Ginecologia e possibilitando
        o acesso a diversos convênios. Localizada em Fortaleza, os consultórios
        trazem conforto e acolhimento aos pacientes, pois temos como principal
        objetivo oferecer bem-estar a todos que entram por nossa porta.
      </p>

      <p>
        Em toda consulta, o paciente terá acesso ao prontuário eletrônico, de
        forma segura, para que possa gerenciar todas as informações fornecidas
        no ato do atendimento, seguindo os conformes da Lei Geral de Proteção de
        Dados. Prezamos pela integridade de seus dados e damos atenção a todos
        os detalhes, para garantir que sua experiência seja a mais agradável
        possível.
      </p>

      <div class="container">
        <div class="row">
          <div class="col-sm">
            <h3>Missão</h3>
            <p>
              Atender com qualidade e excelência, prezando pelo bem-estar e
              privacidade de todos e utilizando a tecnologia para agregar.
            </p>
          </div>
          <div class="col-sm">
            <h3>Visão</h3>
            <p>
              Expandir o quadro de especialidades médicas e ser referência em
              atendimento humanizado, colocando as necessidades e bem-estar dos
              nossos clientes em primeiro lugar.{' '}
            </p>
          </div>
          <div class="col-sm">
            <h3>Valores</h3>
            <ul>
              <li>Excelência no atendimento</li>
              <li>Privacidade e ética</li>
              <li>Bem-estar</li>
              <li>Respeito à diversidade</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  );
};
