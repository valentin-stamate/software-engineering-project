// EXAMPLE INSPIRED FROM FROM https://developers.google.com/maps/documentation/javascript/examples/layer-heatmap

let map, heatmap;

async function initCovidMap() {
    await sleep(500);

    const location = 'Iasi';
    const country = 'Romania';

    const locationCoords = await getLocationCoordinates(location);

    // const pollutionIndex = await getLocationPollutionIndex(location);
    const covidCases = await getCovidCases(country);

    /* TODO, do something if undefined, aka location/country was not found */

    const locationPoint = new google.maps.LatLng(locationCoords.lat, locationCoords.lng);
    const gradientIndex = calculateIndex(covidCases.minCases, covidCases.cases, covidCases.maxCases);


    heatmap.setOptions({data: [{location: locationPoint, weight: 1}]});
    map.setOptions({center: locationPoint});
    pollutionHeatmap(gradientIndex);
}

async function initPollutionMap() {
    console.log("Comming soon");
}

initCovidMap();

function initMap() {

    map = new google.maps.Map(document.getElementById("map"), {
        zoom: 13,
        // center: locationPoint,
        mapTypeId: "satellite",
    });
    heatmap = new google.maps.visualization.HeatmapLayer({
        // data: [{location: locationPoint, weight: 1}],
        map: map,
        radius: getHeatmapRadius(37.774546, map.getZoom())
    });

    google.maps.event.addListener(map, 'zoom_changed', function () {
        heatmap.setOptions({radius: getHeatmapRadius(37.774546, map.getZoom())});
    });

    // changeGradient(gradientIndex);
}

function calculateIndex(minValue, value, maxValue, n) {
    value -= minValue;
    maxValue -= minValue;

    const dist = maxValue / n;

    for (let i = 0; i < n; i++) {
        if (dist * i <= value && dist * (i + 1) < value) {
            return i;
        }
    }

    return 0;
}

// https://stackoverflow.com/questions/12291459/google-maps-heatmap-layer-point-radius
const desiredRadiusInMeters = 3000; // meters
function getHeatmapRadius(latitude, zoomLevel) {
    const metersPerPx = 156543.03392 * Math.cos(latitude * Math.PI / 180) / Math.pow(2, zoomLevel);
    return desiredRadiusInMeters / metersPerPx;
}

const DEPLOY = "http://localhost:8082";
async function getLocationCoordinates(location) {
    const response = await sendFetchRequest(DEPLOY + `/weather?locations=${location}`, 'GET');
    let payload = {};

    await response.json().then(data => {
        payload = data;
    });

    let coords = undefined;
    if (payload.length > 0) {
        coords = payload[0].coord; // {lon: x, lat: y} -> {lng: 0, lat: 0}
        coords['lng'] = coords.lon;
    }

    return coords;
}

async function getLocationPollutionIndex(location) {
    const response = await sendFetchRequest(DEPLOY + `/pollution?locations=${location}`, 'GET');
    let payload = {};

    await response.json().then(data => {
        payload = data;
    });

    let pollutionIndex = undefined;

    if (payload.length > 0) {
        pollutionIndex = payload[0].pollutionIndex;
    }

    return pollutionIndex;
}

async function getCovidCases(country) {
    const dayMs = 86400000;

    const currentDate = new Date(new Date().getTime() - dayMs);
    const dayBefore = new Date(currentDate.getTime() - 30 * dayMs);

    const response = await sendFetchRequest(DEPLOY + `/covid_statistics?countries=${country}&start=${dateToString(dayBefore)}&end=${dateToString(currentDate)}`, 'GET');
    let payload = {};

    await response.json().then(data => {
        payload = data;
    });

    let lastCovidCases = undefined;

    if (payload.length > 0) {
        const items = payload[0].items;
        lastCovidCases = [];

        let minCases = 10000000;
        let maxCases = 0;
        for (let i = 0; i < items.length; i++) {
            const item = items[i];
            lastCovidCases.push({cases: item.newCases});
            minCases = Math.min(minCases, item.newCases);
            maxCases = Math.max(maxCases, item.newCases);
        }

        const lastCases = items[items.length - 1].newCases;

        lastCovidCases = {minCases: minCases, cases: lastCases, maxCases: maxCases};
    }
    return lastCovidCases;
}

function dateToString(date) {
    return `${date.getFullYear()}-${date.getMonth()}-${date.getDate()}`;
}

function covidHeatmap() {
    heatmap.setMap(heatmap.getMap() ? null : map);
}

function pollutionHeatmap(index) {
    const low = [
        "rgba(0, 255, 255, 0)",
        "rgba(0, 255, 255, 1)",
        "rgba(0, 191, 255, 1)",
        "rgba(47, 123, 144, 1)",
    ];

    const medium = [
        "rgba(0, 255, 255, 0)",
        "rgba(49, 49, 182, 1)",
        "rgba(44, 44, 194, 1)",
        "rgba(18, 18, 180, 1)"
    ];

    const high = [
        "rgba(0, 255, 255, 0)",
        "rgba(18, 18, 180, 1)",
        "rgba(63, 0, 91, 1)",
        "rgba(127, 0, 63, 1)"
    ];

    const veryHigh = [
        "rgba(0, 255, 255, 0)",
        "rgba(191, 0, 31, 1)",
        "rgba(255, 0, 0, 1)"
    ];

    const gradientList = [
        low,
        medium,
        high,
        veryHigh
    ];

    heatmap.set("gradient", gradientList[index]);
}

function changeRadius() {
    heatmap.set("radius", heatmap.get("radius") ? null : 20);
}

function changeOpacity() {
    heatmap.set("opacity", heatmap.get("opacity") ? null : 0.2);
}

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}