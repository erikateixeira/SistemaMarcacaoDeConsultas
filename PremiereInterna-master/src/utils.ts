export const Utils = {
  displayErrorMessage: (err: any) => {
    debugger;

    if (!err?.response?.data) {
      window.alert("Ocorreu um erro desconhecido.");
      return;
    }

    if (Array.isArray(err?.response?.data)) {
      window.alert(
        err?.response?.data
          .map(
            (error: { error: any; message: any }) =>
              `${error.error}: ${error.message}`
          )
          .join("\n")
      );
      return;
    }

    window.alert(
      `${err?.response?.data.error}: ${err?.response?.data.message}`
    );
    return;
  },
};
