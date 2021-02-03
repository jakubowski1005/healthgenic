const URL_REGISTER = "http://localhost:8080/auth/register";
const URL_LOGIN = "http://localhost:8080/auth/login";
const URL_LOGOUT = "http://localhost:3000/";

//metoda powinna byc wywolywana po nacisnieciu sign up button
// cialem metody powinno byc:
/*
    {
        login: login,
        email: email,
        password: pass,
        name: imie,
        surname: nazwisko,
        role: "DOCTOR" / "PATIENT"
    }
 */
export const register = async (body) => {
  return fetch(URL_REGISTER, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(body),
  });
};

//metoda powinna byc wywolywana po nacisnieciu sign in button
// cialem metody powinno byc:
/*
    {
        login: login,
        password: pass
    }
 */

export const login = async (body) => {
  console.log("aaa");

  return fetch(URL_LOGIN, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(body),
  });

  // nie wiem czy to tak zadziala, powinno, response.json() zwroci obiekt js na podstawie response, ktory powinien
  // byc stringiem 'Bearer <token>' a substring powinien odciac to 'Bearer '

  // to nizej powinno byc w bloku then [ login({ body }).then(() => { sessionStorage.setItem('token', response.json().substring(7)) ...
  //sessionStorage.setItem('token', response.json().substring(7));
};

// metoda wywolywana przy wylogowaniu, czysci sessionStorage i przenosi uzytkownika na strone glowna
export const logout = () => {
  sessionStorage.clear();
  window.location.replace(URL_LOGOUT);
};
