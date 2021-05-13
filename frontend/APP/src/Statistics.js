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
}

export default Statistics;