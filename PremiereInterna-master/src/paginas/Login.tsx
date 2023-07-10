import "./Css/Login.css";
import Logo from "../assets/Première Santé.png";
import { ChangeEvent, useEffect, useState } from "react";
import { Service } from "../Service";
import { useNavigate } from "react-router-dom";

export const Login = () => {
  useEffect(() => {
    document.title = "Login";
  }, []);
  const navigate = useNavigate();
  const [Usuario, setUsuario] = useState<string>("");
  const [Senha, setSenha] = useState<string>("");

  const onChangeUsuario = (ev: ChangeEvent<HTMLInputElement>) => {
    const newValue = ev.target.value;
    setUsuario(newValue);
  };

  const onChangeSenha = (ev: ChangeEvent<HTMLInputElement>) => {
    const newValue = ev.target.value;
    setSenha(newValue);
  };

  const entrar = () => {
    sessionStorage.setItem("Usuario", Usuario);
    sessionStorage.setItem("Senha", Senha);
    Service.getMyUsuario()
      .then((res) => {
        if (res.data.funcao == "ADMIN") {
          return navigate("/ADMIN");
        }
        if (res.data.especialidade != null) {
          return navigate("/paginamedico");
        }
        if (res.data.funcao == "RECEPCIONISTA") {
          return navigate("/RECEPCIONISTA");
        }
      })
      .catch((err) =>
        window.alert(
          err?.response?.data
            .map((error: { message: any }) => error.message)
            .join("\n")
        )
      );
  };

  return (
    <>
      <div className="row">
        <div className="col-12">
          <img src={Logo} alt="Logo do Site" title="Logo do Site" />
        </div>

        <div className="col-12 navegacao">
          <h1>Login</h1>
        </div>
      </div>
      <div className="container mt-4">
        <div className="row align center">
          <div className="mx-auto col-1g-5" id="login">
            <form className="p-4 p-md-5">
              <div className="form-floating">
                <input
                  type="text"
                  className="form-control"
                  id="inputEmail"
                  name="email"
                  placeholder="Email"
                  value={Usuario}
                  onChange={onChangeUsuario}
                />
                <label htmlFor="inputEmail">Login</label>
              </div>
              <div className="form-floating">
                <input
                  type="password"
                  className="form-control"
                  id="inputPassword"
                  name="senha"
                  placeholder="Senha"
                  value={Senha}
                  onChange={onChangeSenha}
                />
                <label htmlFor="inputPassword">Senha</label>
              </div>

              <button
                id="btncontato"
                className="btn btn-info rounded-pill px-3"
                onClick={entrar}
                type="button"
              >
                Entrar
              </button>
            </form>
          </div>
        </div>
      </div>
    </>
  );
};
