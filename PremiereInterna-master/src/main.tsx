import React from "react";
import ReactDOM from "react-dom/client";
import { ConfirmacaoProvider } from "./ConfirmacaoContext";
import App from "./App.tsx";
import "./index.css";

ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
  <React.StrictMode>
    <ConfirmacaoProvider>
      <App />
    </ConfirmacaoProvider>
  </React.StrictMode>
);
