class FetchHotelInfo{
    static fetchInfo(pageURL){
        const DESC_TAG = '<div id="property_description_content">';
        const DESC_END_TAG = '</div>'

        const IMAGE_TAG = 'background-image: url(';
        const IMAGE_END_TAG = ');"';

        const PRICE_TAG = '<span class="prco-valign-middle-helper">';
        const PRICE_END_TAG = '</span>';

        const FACILITIES_TAG = '<div class="hp_desc_important_facilities clearfix hp_desc_important_facilities--bui ">';
        const FACILITIES_END_TAG = '</div>'

        var response=this.makeAuthRequest(pageURL,"GET",{},"");

        const htmlText = response;

        // WORKING
        var desc=this.getTextBetween(htmlText, DESC_TAG, DESC_END_TAG);
        var img=this.getTextBetween(htmlText, IMAGE_TAG, IMAGE_END_TAG);

        // NOT WORKING
        var price=this.getTextBetween(htmlText, PRICE_TAG, PRICE_END_TAG);

        let facilitiesTable = this.getTextBetween(htmlText, FACILITIES_TAG, FACILITIES_END_TAG, false);
        //var facilities = facilitiesTable[0].replace(/id=[^0-9]/gi, "");

        return {
            "desc":desc,
            "img":img,
            "price":price,
            "facilities":null
        };

    }

    static getTextBetween(rawHtml, start, end, clean = true) {

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
                string = this.cleanTags(string);
            }
    
            fetchedString.push(string);
    
            rawHtml = rawHtml.substr(startIndex, rawHtml.length);
        }

        return fetchedString;
    }
    
    static cleanTags(text) {
        return text.replace(/(<([^>]+)>)/gi, "");
    }
    


    static makeAuthRequest(url,method,data,authorizationToken){
        var api="https://euopendata-proxy.herokuapp.com/get";
        var response=null;
        let req=new XMLHttpRequest();
        req.open('GET',api+"?url="+url+"&method="+method+"&data="+JSON.stringify(data)+"&auth="+authorizationToken,false);
        req.send();

        if(req.readyState === 4 && req.status === 200){
            response=req.responseText;
        }
        else{
            alert("Error");
        }

        return response;
    }
}

export default FetchHotelInfo;