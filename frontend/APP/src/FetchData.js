/*
    Realizeaza comunicarea din client si un server anume
*/

class FetchData{
    //api-ul creat ce serveste drept proxy pentru a evita anumite erori
    static api="http://localhost:3001/";
    //raspunsul dat de server
    static response;
    //NOTE: nu e nevoie folosirea atributelor de mai sus (sunt folosite doar in metodele de mai jos)


    //face un request la url-ul specificat,folosind metoda 'method' (GET,POST etc)
    //parametrii dati request-ului sunt stocati in data
    //pentru anumite operatii e nevoie sa trimitem si un Authorization Token (generat dupa logare de backend)
    //returneaza raspunsul dat de server sub forma unui dictionar
    static makeAuthRequest(url,method,data,authorizationToken){
        this.response=null;
        let req=new XMLHttpRequest();
        req.open('GET',this.api+"?url="+url+"&method="+method+"&data="+JSON.stringify(data)+"&auth="+authorizationToken,false);
        req.send();

        if(req.readyState === 4 && req.status === 200){
            this.response=JSON.parse(req.responseText);
        }
        else{
            alert("Error");
        }

        return this.response;
    }

    //acelasi lucru ca makeAuthRequest ,doar ca nu folosim Authorization Token (unele operatii nu au nevoie de el ,ex login,signup)
    static makeRequest(url,method,data){
        return this.makeAuthRequest(url,method,data,"null");
    }
}

export default FetchData;