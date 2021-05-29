# EU Open Data (Smart Booking) Backend

## About
API Host Url: ` https://smartbooking-app.herokuapp.com`


## How to run?

If you want to run this project locally you have to create a package called `secrets` that will contain
a class called `Secrets`. There you should put your own api keys. 

In application.properties you have `=` with nothing after it. There you have to put your credentials
regarding database connection and email account for using smtp. 

Also, there are some datasets in `resources/static/statistics` that are not in the repository. All of them under [this](https://drive.google.com/drive/folders/1WwM0rzAdXeilLiSOF7Hgq6tdMEV2gy_C?usp=sharing)
link.

## API Endpoints
In order to access user data, the client must log in first and for every request it should have an "Authorization" header, with the value containing the jwt token.

For example, the header could be "`Authorization: YOUR_JWT_TOKEN`" if the token received from logging in is `YOUR_JWT_TOKEN`.

The api documentation can be found below.

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/12026756-642d65c4-1d99-4a70-b5fd-ea392ac1084c?action=collection%2Ffork&collection-url=entityId%3D12026756-642d65c4-1d99-4a70-b5fd-ea392ac1084c%26entityType%3Dcollection)

## External API's

We used the following api's in our integration:

* [OpenWeather](https://openweathermap.org/api) : weather, forecast
* [Google Custom Search](https://developers.google.com/custom-search?) : covid news, hotel search
* [Covid Dataset](https://covid.ourworldindata.org/data/owid-covid-data.csv) : [covid statistics](https://covid.ourworldindata.org/data/owid-covid-data.csv), [co2 emissions](https://ourworldindata.org/grapher/annual-co2-emissions-per-country)
* [WorldBank](https://data.worldbank.org) : [pump gasoline price](https://ourworldindata.org/grapher/annual-co2-emissions-per-country)  
* [Numbeo](https://www.numbeo.com) : criminality, pollution, restaurants, healthcare, cost of living, food prices
* [Weatherbit](https://api.weatherbit.io) : statistical weather
