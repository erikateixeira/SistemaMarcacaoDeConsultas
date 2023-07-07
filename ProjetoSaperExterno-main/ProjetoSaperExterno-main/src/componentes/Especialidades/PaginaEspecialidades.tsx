import './Especialidades.css';

export const PaginaEspecialidades = () => {
  return (
    <div className="especialidades">
      <h2>Nossas especialidades</h2>
      <p className="p2">
        Nossa equipe é formada por profissionais qualificados comprometidos com
        um atendimento eficiente.
      </p>
      <div class="container">
        <div class="row">
          <div class="col-sm">
            <h3>Cardiologia</h3>
            <p>
              Cardiologia é a especialidade médica que ocupa do diagnóstico e
              tratamento das doenças que acometem o coração bem como os outros
              componentes do sistema circulatório.
            </p>
          </div>
          <div class="col-sm">
            <h3>Dermatologia</h3>
            <p>
              O dermatologista é o médico que se especializou na prevenção, no
              diagnóstico e no tratamento de doenças e condições que afetam a
              pele, o cabelo, as unhas e as mucosas, totalizando mais de 3 mil
              problemas diferentes.
            </p>
          </div>
          <div class="col-sm">
            <h3>Ginecologia</h3>
            <p>
              A ginecologia é um ramo da medicina. Sendo uma especialidade
              própria para estudar, cuidar e tratar a saúde do aparelho
              reprodutor feminino.
            </p>
          </div>
        </div>
      </div>
      <div class="container">
        <div class="row">
          <div class="col-sm">
            <p className="destaque">
              Dr. Bruno Andrade dos Santos <br />
              CRM CE-257456
            </p>
            <img className="doc1" src="src/assets/img/doctor1.png" />

            <p className="destaque">
              Dra. Mariana Bezerra Araújo Freitas <br /> CRM MA-145782
            </p>
            <img className="doc1" src="src/assets/img/doctor3.png" />
          </div>
          <div class="col-sm">
            <p className="destaque">
              Dra. Marisa Aguiar Oliveira
              <br />
              CRM CE-472843
            </p>
            <img className="doc1" src="src/assets/img/doctor7.png" />

            <p className="destaque">
              Dr. Paulo Nogueira de Costa Lima
              <br /> CRM PB-758142
            </p>
            <img className="doc1" src="src/assets/img/doctor2.png" />
          </div>
          <div class="col-sm">
            <p className="destaque">
              Dr. Adriano de Mello Soares
              <br />
              CRM PI-785426
            </p>
            <img className="doc1" src="src/assets/img/doctor4.png" />

            <p className="destaque">
              Dra. Bianca Medeiros Fontes
              <br /> CRM CE-123456
            </p>
            <img className="doc1" src="src/assets/img/doctor6.png" />
          </div>
        </div>
      </div>

      <h3>
        Depoimentos sobre os profissionais da{' '}
        <img src="src/assets/img/fundo.png" className="logo" />
      </h3>
      <div class="container">
        <div class="row">
          <div class="col-sm">
            <h6>Francisco Mauro Teixeira</h6>
            <p>
              O Dr. Bruno foi muito atencioso durante a consulta e observou meus
              sintomas com precisão. Com certeza farei acompanhamento com ele.
            </p>
          </div>
          <div class="col-sm">
            <h6>Pedro Jorge da Silva</h6>
            <p>
              Minha experiência foi excepcional! Tratamento humanizado,
              eficiente e pontual pela Dra. Marisa Aguiar Oliveira
            </p>
          </div>
          <div class="col-sm">
            <h6>Pamela da Costa Martins</h6>
            <p>
              A Dra. Bianca demonstrou muito profissionalismo, empatia, e foi
              muito atenciosa, me deixando muito confortável e passando
              segurança!
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};
