const URL_REGISTER = "http://localhost:8080/auth/register";
const URL_LOGIN = "http://localhost:8080/auth/login";
const URL_LOGOUT = "http://localhost:3000/";

class AuthService {} //!!!! Musiałam to dodać tymczasowo bo błędy wyrzucało - W

//metoda powinna byc wywolywana po nacisnieciu sign up button
// cialem metody powinno byc:
/*
    {
        login: login,
        email: email,
        password: pass,
        name: imie,
        surname: nazwisko
    }
 */
export const register = async (body) => {
  const response = await fetch(URL_REGISTER, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(body),
  });
  return response.json();
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
  const response = await fetch(URL_LOGIN, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(body),
  });

  // nie wiem czy to tak zadziala, powinno, response.json() zwroci obiekt js na podstawie response, ktory powinien
  // byc stringiem 'Bearer <token>' a substring powinien odciac to 'Bearer '
  sessionStorage.setItem("token", response.json().substring(7));
  return true;
};

// metoda wywolywana przy wylogowaniu, czysci sessionStorage i przenosi uzytkownika na strone glowna
export const logout = () => {
  sessionStorage.clear();
  window.location.replace(URL_LOGOUT);
};

//!!!!! musiałam dodać bo nie chciało działać - W
export default new AuthService();
