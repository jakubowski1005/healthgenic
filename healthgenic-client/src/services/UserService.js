const URL = 'http://localhost:8080/users';
const token = sessionStorage.getItem('token');
const bearerToken = 'Bearer ' + token;

// NA RAZIE ZOSTAWIC, MUSZE POPRAWIC

// ta metoda powinna byc wywolywana po tym jak uzytkownik chce zmienic swoje dane
// przyjmuje ona cialo:
/*
    {

    }
 */
export const updateUser = async body => {
    const response = await fetch(URL, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authentication': bearerToken
        },
        body: JSON.stringify(body)
    });
}