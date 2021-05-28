const heatmapContainer = document.getElementById('heatmap-page');

const county = 'Iasi';
const country = 'Romania';

async function fetchPage() {

    const response = await sendFetchRequest(`https://heatmap-dc0da.web.app/?location=${county}&country=${country}`)

    response.text().then(data => {
        heatmapContainer.innerHTML = data;
    });
}

fetchPage();

async function sendFetchRequest(url, method = 'GET', payload = {}) {
    const headers = {
        method: method
    }

    if (method !== 'GET') {
        headers['body'] = JSON.stringify(payload);
    }

    return await fetch(url, headers);
}

