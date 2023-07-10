import { createContext, useContext, useState } from "react";

type ConfirmacaoContextType = {
  confirmacao: boolean | null;
  setConfirmacao: (confirmacao: boolean | null) => void;
  confirmacaoIdConsulta: number | null;
  setConfirmacaoIdConsulta: (idConsulta: number | null) => void;
};

const ConfirmacaoContext = createContext<ConfirmacaoContextType>({
  confirmacao: null,
  setConfirmacao: () => {},
  confirmacaoIdConsulta: null,
  setConfirmacaoIdConsulta: () => {},
});

export const ConfirmacaoProvider: React.FC = ({ children }) => {
  const [confirmacao, setConfirmacao] = useState<boolean | null>(null);
  const [confirmacaoIdConsulta, setConfirmacaoIdConsulta] = useState<
    number | null
  >(null);

  return (
    <ConfirmacaoContext.Provider
      value={{
        confirmacao,
        setConfirmacao,
        confirmacaoIdConsulta,
        setConfirmacaoIdConsulta,
      }}
    >
      {children}
    </ConfirmacaoContext.Provider>
  );
};

export const useConfirmacao = () => useContext(ConfirmacaoContext);
