import LocationInfo from './LocationInfo.js';
import WheaterInfo from './WheatherInfo.js';

export default class Preference {
    constructor (location, wheather)
    {
        this.locationInfo = location;
        this.wheaterInfo = wheater;
    }

    getLovationInfo()
    {
        return this.locationInfo;
    }

    getWheatherInfo ()
    {
        return this.wheaterInfo;
    }
}