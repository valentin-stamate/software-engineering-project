
let pageURL = 'https://www.booking.com/hotel/ro/b-house-rooms.html'

const DESC_TAG = '<div id="property_description_content">';
const DESC_END_TAG = '</div>'

const IMAGE_TAG = 'background-image: url(';
const IMAGE_END_TAG = ');"';

const PRICE_TAG = '<span class="prco-valign-middle-helper">';
const PRICE_END_TAG = '</span>'

const FACILITIES_TAG = '<div class="facilitiesChecklist">'
const FACILITIES_END_TAG = '</div>'

axios({
    method: 'GET',
    url: pageURL
}).then((response) => {
    const htmlText = response.data;

    // WORKING
    console.log(getTextBetween(htmlText, DESC_TAG, DESC_END_TAG));
    console.log(getTextBetween(htmlText, IMAGE_TAG, IMAGE_END_TAG));

    // NOT WORKING
    console.log(getTextBetween(htmlText, PRICE_TAG, PRICE_END_TAG));

    let facilitiesTable = getTextBetween(htmlText, FACILITIES_TAG, FACILITIES_END_TAG, false)
    // facilitiesTable = facilitiesTable[0].replace(/id=[^0-9]/gi, "");

    // console.log(facilitiesTable);

});

function getTextBetween(rawHtml, start, end, clean = true) {

    const fetchedString = [];


    while (rawHtml.indexOf(start) !== -1) {
        const startIndex = rawHtml.indexOf(start) + start.length;
        const htmlCut = rawHtml.substring(startIndex, rawHtml.length);

        const endIndex = htmlCut.indexOf(end);

        let string = rawHtml.substring(
            startIndex,
            rawHtml.length - htmlCut.length + endIndex
        );

        if (clean === true) {
            string = cleanTags(string);
        }

        fetchedString.push(string);

        rawHtml = rawHtml.substr(startIndex, rawHtml.length);
    }

    return fetchedString;
}

function cleanTags(text) {
    return text.replace(/(<([^>]+)>)/gi, "");
}