import LocationInfo from './LocationInfo';
import WheaterInfo from './WheatherInfo';

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