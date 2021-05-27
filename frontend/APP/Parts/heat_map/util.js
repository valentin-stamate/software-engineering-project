// TAKEN FROM ReDat PROJECT
async function sendFetchRequest(url, method, payload = {}) {
    const headers = {
        method: method,
        mode: 'cors', // DEFAULT 'cors'
        cache: 'no-cache',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json',
        },
        redirect: 'follow',
        referrerPolicy: 'no-referrer'
    }

    if (method !== 'GET') {
        headers['body'] = JSON.stringify(payload);
    }

    return await fetch(url, headers);
}