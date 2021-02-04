const URL = 'http://localhost:8080/measurements';
const token = sessionStorage.getItem('token');
const bearerToken = 'Bearer ' + token;

// ta metoda powinna byc wywolywana podczas ladowania componentu measurementList przez PACJENTA
export const getMeasurements = async () => {
    return fetch(URL, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'authorization': bearerToken
        }
    });
}

// ta metoda powinna byc wywolywana podczas ladowania componentu measurementList przez LEKARZA dla konkretnego PACJENTA
export const getPatientsMeasurements = async id => {
    console.log('id', id)
    console.log(URL + `/${id}`)
    console.log(bearerToken)
    console.log({
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'authorization': bearerToken
        }});
    return fetch(URL + `/${id}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'authorization': bearerToken
        }
    });
}

// ta metoda powinna byc wywolywana po wcisnieciu przycisku add measurement w panelu pacjenta
// przyjmuje ona obiekt body ktory powinien wygladac mniej wiecej tak
/*
    {
        owner: pacjentLogin,
        date: czas dodania Date.now() czy cos takiego
        value: wartosc pomiaru jako STRING! np "2.33",
        unit: jednostka w postaci stringa,
        type: co to za pomiar cisniecie, tetno cos takiego
    }
 */
export const addMeasurement = async body => {
    return fetch(URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'authorization': bearerToken
        },
        body: JSON.stringify(body)
    });
}