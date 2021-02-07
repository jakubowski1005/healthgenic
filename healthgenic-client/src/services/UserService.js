const URL = "http://localhost:8080";
const token = sessionStorage.getItem("token");
const bearerToken = "Bearer " + token;

// ta metoda zwraca informacje o zalogowanym userze
export const getUserInfo = async () => {
  return fetch(URL + "/current", {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      authorization: bearerToken,
    },
  });
};

// ta metoda zwraca informacje o pacjentach (dla lekarza) lub lekarzach (dla pacjenta)
export const getRelatedUsers = async () => {
  return fetch(URL + "/related", {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      authorization: bearerToken,
    },
  });
};

// usuwa konto zalogowanego uzytkownika
export const deleteAccount = async () => {
  return fetch(URL + "/users", {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      authorization: bearerToken,
    },
  });
};
