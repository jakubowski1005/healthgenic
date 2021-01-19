
const URL = 'http://localhost:8080/measurements';
const token = sessionStorage.getItem('token');
const bearerToken = 'Bearer ' + token;

// ta metoda powinna byc wywolywana podczas ladowania componentu measurementList
export const getMeasurements = async () => {
    const response = await fetch(URL, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authentication': bearerToken
        }
    });
    return response.json();
}

// ta metoda powinna byc wywolywana po wcisnieciu przycisku add measurement w panelu pacjenta
// przyjmuje ona obiekt body ktory powinien wygladac mniej wiecej tak
/*
    {
        owner: pacjentLogin,
        date: czas dodania Date.now() czy cos takiego
        value: wartosc pomiaru jako liczba np 2.33,
        unit: jednostka w postaci stringa,
        type: co to za pomiar cisniecie, tetno cos takiego
    }
 */
export const addMeasurement = async body => {
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

// usuwanie pomiaru -> przyjmuje stringa ktory jest ID tego pomiaru
export const deleteMeasurement = async id => {
    const response = await fetch(URL, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Authentication': bearerToken
        },
        body: id
    });
    return response.json();
}