import FetchData from './FetchData';

class Statistics {

    static getCovidStatistics(country, startDate, endDate) {
        var data = {
            countries: country,
            start: startDate,
            end: endDate
        };

        var statistics = FetchData.makeRequest('https://euopendata.herokuapp.com/covid_statistics', 'GET', data);

        return statistics;
    }

    static getCriminalityStatistics(location) {
        var data = {
            locations: location
        };

        var statistics = FetchData.makeRequest('https://euopendata.herokuapp.com/criminality_statistics', 'GET', data);

        return statistics;
    }

    static getWeatherStatistics(findLocation, findDate) {
        var data = {
            location: findLocation,
            date: findDate
        };

        var statistics = FetchData.makeRequest('https://euopendata.herokuapp.com/statistical_weather', 'GET', data);

        return statistics;
    }

    static getPollutionStatistics(findLocation){
        var data={
            locations: findLocation
        };

        var statistics=FetchData.makeRequest('https://euopendata.herokuapp.com/pollution', 'GET', data);
    
        return statistics;
    }

    static getCovidNews(locationCovid,resultsCovid){
        var data = {
            locations:locationCovid,
            max_results:resultsCovid
        };

        var news = FetchData.makeRequest('https://euopendata.herokuapp.com/covid_news', 'GET', data);

        return news;
    }


    static getRestaurantStatistics(locationRestaurant){
        var data = {
            locations:locationRestaurant,
        };

        var restaurant = FetchData.makeRequest('https://euopendata.herokuapp.com/restaurants', 'GET', data);

        return restaurant;
    }
}

export default Statistics;