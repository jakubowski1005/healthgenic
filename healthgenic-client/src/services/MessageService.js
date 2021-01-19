const URL = 'http://localhost:8080/messages';
const token = sessionStorage.getItem('token');
const bearerToken = 'Bearer ' + token;

// ta metoda powinna byc wywolywana po zaladowania komponentu messages w panelu lekarza lub pacjenta
export const getMessages = async () => {
    const response = await fetch(URL, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authentication': bearerToken
        }
    });
    return response.json();
}

// ta metoda powinna byc wywolywana po wcisnieciu przycisku send message w tych panelach
// przyjmuje ona obiekt body ktory powinien wygladac mniej wiecej tak
/*
    {
        from: lekarzLogin / pacjentLogin (zalezy kto wysyla),
        to: jak wyzej,
        sentAt: czas wyslania Date.now() czy cos takiego
        content: tresc wiadomosci w postaci stringa
    }
 */
export const sendMessage = async body => {
    const response = await fetch(URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authentication': bearerToken
        },
        body: JSON.stringify(body)
    });
    return response.json();
}

