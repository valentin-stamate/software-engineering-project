import react from 'react';
import './statistics.css';
import Statistics from './Statistics.js';
import { Helmet } from 'react-helmet';
import {GoogleMap,withScriptjs,withGoogleMap} from 'react-google-maps';
 
function Map(){
    return(
        <GoogleMap defaultZoom={10} defaultCenter={{lat:45,lng:45}}/>
    );
}

class StatisticsPage extends react.Component{
    constructor(props){
        super(props);

        this.state={
            searchLoc:'',
            covidSearch:'',
            covidStartDate:'',
            covidEndDate:'',
            covidNewCases:0,
            covidTotalCases:0,
            covidNewDeaths:0,
            covidTotalDeaths:0,
            wheaterSearch:'',
            wheaterDate:'',
            windSpeed:0,
            solarRad:0,
            clouds:0,
            prec:0,
            pressure:0,
            maxTemp:0,
            minTemp:0,
            maxUV:0,
            snow:0,
            pollutionLocation:'',
            airQualityIndex:'',
            pm10Value:'',
            airPressure:'',
            airHumidity:'',
            crimeSearch:'',
            safetyIndex:0,
            brokenHomesIndex:0,
            robbingIndex:0,
            stolenCarsIndex:0,
            attackedIndex:0,
            nightWalkingSafetyIndex:0,
            racismIndex:0,
            covidNewsSearch:'',
            covidNewsMaxRes:0,
            covidNews:[],
            covidNewsHtml:'',
            restaurantLocation:'',
            simpleMeal1PersonPrice:0,
            fullMeal2PersonsPrice:0,
            beerDraughtPrice:0,
            beerBottlePrice:0,
            cappuccinoPrice:0,
            waterPrice:0,
            cokePrice:0,
            mcMealPrice:0

        };
    }

    search = (event) =>{
        if(event.keyCode!=13){
            return;
        }
        window.location="/search?location="+this.state.searchLoc;
    }

    myChangeHandler = (event) => {
        let nam = event.target.name;
        let val = event.target.value;
        this.setState({[nam] : val});
    }

    covidStats = () => {
        var stats=Statistics.getCovidStatistics(this.state.covidSearch,this.state.covidStartDate,this.state.covidEndDate);
        stats=stats[0].items;
        /*var stats={
              items: [
                  {
                      "date": "2021-02-24",
                      "newCases": 18,
                      "totalCases": 55664,
                      "newDeaths": 1,
                      "totalDeaths": 2436
                  },
                  {
                      "date": "2021-02-25",
                      "newCases": 16,
                      "totalCases": 55680,
                      "newDeaths": 2,
                      "totalDeaths": 2438
                  },
                  {
                      "date": "2021-02-26",
                      "newCases": 16,
                      "totalCases": 55696,
                      "newDeaths": 4,
                      "totalDeaths": 2442
                  },
                  {
                      "date": "2021-02-27",
                      "newCases": 11,
                      "totalCases": 55707,
                      "newDeaths": 1,
                      "totalDeaths": 2443
                  },
                  {
                      "date": "2021-02-28",
                      "newCases": 7,
                      "totalCases": 55714,
                      "newDeaths": 0,
                      "totalDeaths": 2443
                  },
                  {
                      "date": "2021-03-01",
                      "newCases": 19,
                      "totalCases": 55733,
                      "newDeaths": 1,
                      "totalDeaths": 2444
                  },
                  {
                      "date": "2021-03-02",
                      "newCases": 26,
                      "totalCases": 55759,
                      "newDeaths": 2,
                      "totalDeaths": 2446
                  },
                  {
                      "date": "2021-03-03",
                      "newCases": 11,
                      "totalCases": 55770,
                      "newDeaths": 0,
                      "totalDeaths": 2446
                  },
                  {
                      "date": "2021-03-04",
                      "newCases": 5,
                      "totalCases": 55775,
                      "newDeaths": 0,
                      "totalDeaths": 2446
                  },
                  {
                      "date": "2021-03-05",
                      "newCases": 52,
                      "totalCases": 55827,
                      "newDeaths": 3,
                      "totalDeaths": 2449
                  },
                  {
                      "date": "2021-03-06",
                      "newCases": 13,
                      "totalCases": 55840,
                      "newDeaths": 0,
                      "totalDeaths": 2449
                  },
                  {
                      "date": "2021-03-07",
                      "newCases": 7,
                      "totalCases": 55847,
                      "newDeaths": 0,
                      "totalDeaths": 2449
                  },
                  {
                      "date": "2021-03-08",
                      "newCases": 29,
                      "totalCases": 55876,
                      "newDeaths": 2,
                      "totalDeaths": 2451
                  },
                  {
                      "date": "2021-03-09",
                      "newCases": 0,
                      "totalCases": 55876,
                      "newDeaths": 0,
                      "totalDeaths": 2451
                  },
                  {
                      "date": "2021-03-10",
                      "newCases": 18,
                      "totalCases": 55894,
                      "newDeaths": 0,
                      "totalDeaths": 2451
                  },
                  {
                      "date": "2021-03-11",
                      "newCases": 23,
                      "totalCases": 55917,
                      "newDeaths": 0,
                      "totalDeaths": 2451
                  },
                  {
                      "date": "2021-03-12",
                      "newCases": 42,
                      "totalCases": 55959,
                      "newDeaths": 3,
                      "totalDeaths": 2454
                  },
                  {
                      "date": "2021-03-13",
                      "newCases": 0,
                      "totalCases": 55959,
                      "newDeaths": 0,
                      "totalDeaths": 2454
                  },
                  {
                      "date": "2021-03-14",
                      "newCases": 26,
                      "totalCases": 55985,
                      "newDeaths": 3,
                      "totalDeaths": 2457
                  },
                  {
                      "date": "2021-03-15",
                      "newCases": 0,
                      "totalCases": 55985,
                      "newDeaths": 2,
                      "totalDeaths": 2459
                  },
                  {
                      "date": "2021-03-16",
                      "newCases": 10,
                      "totalCases": 55995,
                      "newDeaths": 1,
                      "totalDeaths": 2460
                  },
                  {
                      "date": "2021-03-17",
                      "newCases": 21,
                      "totalCases": 56016,
                      "newDeaths": 0,
                      "totalDeaths": 2460
                  },
                  {
                      "date": "2021-03-18",
                      "newCases": 28,
                      "totalCases": 56044,
                      "newDeaths": 2,
                      "totalDeaths": 2462
                  },
                  {
                      "date": "2021-03-19",
                      "newCases": 25,
                      "totalCases": 56069,
                      "newDeaths": 0,
                      "totalDeaths": 2462
                  },
                  {
                      "date": "2021-03-20",
                      "newCases": 24,
                      "totalCases": 56093,
                      "newDeaths": 0,
                      "totalDeaths": 2462
                  },
                  {
                      "date": "2021-03-21",
                      "newCases": 10,
                      "totalCases": 56103,
                      "newDeaths": 1,
                      "totalDeaths": 2463
                  },
                  {
                      "date": "2021-03-22",
                      "newCases": 50,
                      "totalCases": 56153,
                      "newDeaths": 1,
                      "totalDeaths": 2464
                  },
                  {
                      "date": "2021-03-23",
                      "newCases": 24,
                      "totalCases": 56177,
                      "newDeaths": 2,
                      "totalDeaths": 2466
                  },
                  {
                      "date": "2021-03-24",
                      "newCases": 15,
                      "totalCases": 56192,
                      "newDeaths": 0,
                      "totalDeaths": 2466
                  }
              ]
          };*/
        var deaths=0;
        var cases=0;

        for(const[index,stat] of stats.entries()){
          if(stat["date"]<this.state.covidStartDate || stat["date"]>this.state.covidEndDate){
            continue;
          }
            deaths+=stat["newDeaths"];
            cases+=stat["newCases"];
        }

        this.setState({covidNewCases:cases,covidNewDeaths:deaths});
    }

    wheaterStats = () => {
        var stats=Statistics.getWeatherStatistics(this.state.wheaterSearch,this.state.wheaterDate);
        var data=stats.data[0];
        this.setState({windSpeed:data.wind_spd,clouds:data.clouds,prec:data.precip,pressure:data.pres,snow:data.snow,maxTemp:data.max_temp,minTemp:data.min_temp,maxUV:data.max_uv});
    }

    pollutionStats = () => {
    var stats=Statistics.getPollutionStatistics(this.state.pollutionLocation);
    stats=stats[0];
    /*var stats={
        "Iasi":{
          "location": "Iasi",
          "airQualityIndex": 11,
          "pm10Value": 11,
          "airPressure": 10135,
          "airHumidity": 53.5
      },
      "Bucuresti":{
        "location": "Bucharest",
        "airQualityIndex": 23,
        "pm10Value": 23,
        "airPressure": 10063,
        "airHumidity": 72.9
    }
      }

    stats=stats[this.state.pollutionLocation];*/

    this.setState({airQualityIndex:stats.airQualityIndex,pm10Value:stats.pm10Value,airPressure:stats.airPressure,airHumidity:stats.airHumidity});

    };

    crimeStats = () =>{
        var stats=Statistics.getCriminalityStatistics(this.crimeSearch)[0];
      /*var stats={
        "Bucuresti":{
          "crimeIndex": 27.99,
          "safetyIndex": 72.01,
          "crimeIncreasingPast3YearsIndex": 35.35,
          "brokenHomesIndex": 23.38,
          "robbingIndex": 25.23,
          "stolenCarsIndex": 18.03,
          "stolenObjectsFromCarsIndex": 34.57,
          "attackedIndex": 21.93,
          "insultedIndex": 34.48,
          "racismIndex": 14.46,
          "drugsIndex": 27.29,
          "violentCrimesIndex": 15.46,
          "corruptionIndex": 77.71,
          "nightWalkingSafetyIndex": 67.08
      },
      "Iasi":{
        "crimeIndex": 29.07,
        "safetyIndex": 70.93,
        "crimeIncreasingPast3YearsIndex": 37.17,
        "brokenHomesIndex": 22.57,
        "robbingIndex": 28.62,
        "stolenCarsIndex": 17.41,
        "stolenObjectsFromCarsIndex": 38.09,
        "attackedIndex": 26.26,
        "insultedIndex": 30,
        "racismIndex": 17.58,
        "drugsIndex": 23.03,
        "violentCrimesIndex": 15.89,
        "corruptionIndex": 74.17,
        "nightWalkingSafetyIndex": 61.25
    }
      };

    stats=stats[this.state.crimeSearch];*/

    this.setState({safetyIndex:stats.safetyIndex,brokenHomesIndex:stats.brokenHomesIndex,robbingIndex:stats.robbingIndex,
    stolenCarsIndex:stats.stolenCarsIndex,attackedIndex:stats.attackedIndex,racismIndex:stats.racismIndex,drugsIndex:stats.drugsIndex,nightWalkingSafetyIndex:stats.nightWalkingSafetyIndex
    });

    }

    covidNews = () =>{
        var news=Statistics.getCovidNews(this.state.covidNewsSearch)[0];
        var res=news.results;

        var covidNews=[];
        var html=[];

        for(const [index,news] of res.entries()){
            covidNews.push({
                title:news.title,
                link:news.link,
                snippet:news.snippet
            });
            html.push(
                <div style={{position:'relative'}}>
                    <h3>{news.title}</h3>
                    <a href={news.link}>{news.snippet}</a>
                    <hr />
                </div>
            );
        }

        this.setState({covidNews:covidNews,covidNewsHtml:html});

    }

    restaurantStats = () =>{
        var stats=Statistics.getRestaurantStatistics(this.state.restaurantLocation)[0];
        alert(JSON.stringify(stats));
        this.setState({
            simpleMeal1PersonPrice:stats.simpleMeal1PersonPrice,
            fullMeal2PersonsPrice:stats.fullMeal2PersonsPrice,
            beerBottlePrice:stats.beerBottlePrice,
            beerDraughtPrice:stats.beerDraughtPrice,
            cappuccinoPrice:stats.cappuccinoPrice,
            waterPrice:stats.waterPrice,
            cokePrice:stats.cokePrice,
            mcMealPrice:stats.mcMealPrice
        });
    }


    render(){

        const WrappedMap=withScriptjs(withGoogleMap(Map));
        
        return(
            <div>
                <WrappedMap googleMapURL={"https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=geometry,drawing,places&key=AIzaSyBO1l1piho62JAWKiodnd7rNgzZhpPbUAY"}
                    loadingElement={<div style={{ height: `100%` }} />}
                    containerElement={<div style={{ height: `400px` }} />}
                    mapElement={<div style={{ height: `100%` }} />}
                />
            </div>
        );  

        /*return(
            <div class="stats">
                
                <div class="topnav">
        <a class="active" href="/">Home</a>
        <a href="/profile">Your Profile</a>
        <a href="/search">Search Location</a>
        <a href="/fav">See Favorites </a>
        <a href="/login">Sign Out</a>
        <input type="text" placeholder="Search location" name="searchLoc" onKeyUp={this.search} onChange={this.myChangeHandler}/>

    </div>
<div class="col-sm-12 col-md-12 col-lg-12">
    <div class="statistic">
        <div class="row">

                <div class="description description-tabs">
                    <ul id="myTab" class="nav nav-pills">
                        <li class="active"><a href="#more-information" data-toggle="tab" class="no-margin"> Covid-19 Statistics </a></li>
                        <li class=""><a href="#weather" data-toggle="tab">Weather Statistics</a></li>
                        <li class=""><a href="#pollution" data-toggle="tab">Pollution</a></li>
                        <li class=""><a href="#crime" data-toggle="tab">Crime rate</a></li>
                        <li class=""><a href="#covid-news" data-toggle="tab">Covid News</a></li>
                        <li class=""><a href="#restaurant" data-toggle="tab">Restaurant Statistics</a></li>

                    </ul>
                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane fade active in" id="more-information">

                            <div class="content" style={{position:'relative',left:'20%'}}>
                                <div class="tab-pane active " id="profile">
                     <div class="col-sm">
                                        <div class="logo">
                            <h2 >C<i class="fas fa-virus "></i>vid-19</h2>
                                        </div>


                                    <div class="search-br">
                                      <div class="example" aria-disabled>
                                        <input onChange={this.myChangeHandler} style={{position:'relative',left:'-13%'}} type="text" placeholder="Search.." name="covidSearch"/>
                                        <input onChange={this.myChangeHandler} style={{position:'relative',left:'-13%'}} type="text" placeholder="start date" name="covidStartDate" />
                                        <input onChange={this.myChangeHandler} style={{position:'relative',left:'-13%'}} type="text" placeholder="end date" name="covidEndDate" /><br/>
                                        <button onClick={this.covidStats} style={{width:'15%'}}><i class="fa fa-search"></i></button>
                                        </div>
                                  </div>
                        </div>

                                    <div class="data">
                                        <p>Coronavirus Cases:</p>
                                        <h2 class="confirmed">{this.state.covidNewCases}</h2>
                                    </div>
                                    <div class="data">
                                        <p>Deaths:</p>
                                        <h2 class="death">{this.state.covidNewDeaths}</h2>
                                    </div>
                                    <div class="data">
                                        <p>Recovered:</p>
                                        <h2 class="recovered">{this.state.covidNewCases-this.state.covidNewDeaths}</h2>
                                    </div>




                                 </div>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="weather" style={{position:'relative',left:'20%'}}>

                              <div class="container">
                                <div class="tab-pane active " id="profile">
                                  <div class="weathertitle">
                                    <h2 >Weather <i class="fas fa-cloud-sun"></i></h2>
                                    </div>
                                  <div class="search-br1">
                                        <div class="example" aria-disabled>
                                            <input onChange={this.myChangeHandler} style={{position:'relative',left:'-13%'}} type="text" placeholder="Search.." name="wheaterSearch"/>
                                            <input onChange={this.myChangeHandler} style={{position:'relative',left:'-13%'}} type="text" placeholder="date" name="wheaterDate" /><br/>
                                            <button onClick={this.wheaterStats} ><i class="fa fa-search"></i></button>
                                        </div>
                                  </div>
                                  <div class="">
                                      

                                  <div class="weather-data">
                                  <div class="data">
                                        <p>Max wind speed:</p>
                                        <h2 class="confirmed">{this.state.windSpeed}</h2>
                                    </div>
                                    <div class="data">
                                        <p>Solar radiation:</p>
                                        <h2 class="death">{this.state.solarRad}</h2>
                                    </div>
                                    <div class="data">
                                        <p>Clouds:</p>
                                        <h2 class="recovered">{this.state.clouds}</h2>
                                    </div>
                                    <div class="data">
                                        <p>Precipitations:</p>
                                        <h2 class="confirmed">{this.state.prec}</h2>
                                    </div>
                                    <div class="data">
                                        <p>Pressure:</p>
                                        <h2 class="death">{this.state.pressure}</h2>
                                    </div>
                                    <div class="data">
                                        <p>Snow:</p>
                                        <h2 class="recovered">{this.state.snow}</h2>
                                    </div>
                                    <div class="data">
                                        <p>Max temperature:</p>
                                        <h2 class="confirmed">{this.state.maxTemp}</h2>
                                    </div>
                                    <div class="data">
                                        <p>Max UV:</p>
                                        <h2 class="death">{this.state.maxUV}</h2>
                                    </div>
                                    <div class="data">
                                        <p>Min temperature:</p>
                                        <h2 class="recovered">{this.state.minTemp}</h2>
                                    </div>
                                  </div>
                                </div>
                            </div>

                        </div>

                    </div>
                    <div class="tab-pane fade" id="pollution">
                        <div class="container" style={{position:'relative',left:'30%'}}>
                          <div class="pollutiontitle">
                            <h2 >Pollution <i class="fas fa-biohazard"></i></h2>
                            </div>
                            <div class="search-br2">
                                <div class="example" >
                                    <input onChange={this.myChangeHandler} style={{position:'relative',left:'-13%'}} type="text" placeholder="Search.." name="pollutionLocation"/><br/>
                                    <button onClick={this.pollutionStats} style={{width:'15%'}} type="submit"><i class="fa fa-search"></i></button>
                                </div>
                            </div>

                          <div class="weather-data">
                          <div class="data">
                                        <p>Air Quality Index:</p>
                                        <h2 class="recovered">{this.state.airQualityIndex}</h2>
                                    </div>
                                    <div class="data">
                                        <p>PM10 Value:</p>
                                        <h2 class="confirmed">{this.state.pm10Value}</h2>
                                    </div>
                                    <div class="data">
                                        <p>Air Pressure:</p>
                                        <h2 class="death">{this.state.airPressure}</h2>
                                    </div>
                                    <div class="data">
                                        <p>Air Humidity:</p>
                                        <h2 class="recovered">{this.state.airHumidity}</h2>
                                    </div>
                          </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="crime">
                    <div class="container" style={{position:'relative',left:'30%'}}>
                      <div class="pollutiontitle">
                        <h2 ><i>Crime Rate</i></h2>
                        </div>
                        <div class="search-br2">
                            <div class="example" >
                                <input onChange={this.myChangeHandler} type="text" style={{position:'relative',left:'-13%'}} placeholder="Search Location.." name="crimeSearch"/>
                                <button onClick={this.crimeStats} type="submit"><i class="fa fa-search"></i></button>
                            </div>

                            
                            <div class="weather-data">
                          <div class="data">
                                    <div class="data">
                                        <p>Safety Index:</p>
                                        <h2 class="recovered">{this.state.safetyIndex}</h2>
                                    </div>
                                    <div class="data">
                                        <p>Broken Homes Index:</p>
                                        <h2 class="confirmed">{this.state.brokenHomesIndex}</h2>
                                    </div>
                                    <div class="data">
                                        <p>Robbing Index:</p>
                                        <h2 class="death">{this.state.robbingIndex}</h2>
                                    </div>
                                    <div class="data">
                                        <p>Stolen Cars Index:</p>
                                        <h2 class="recovered">{this.state.stolenCarsIndex}</h2>
                                    </div>
                                    <p>Racism Index:</p>
                                        <h2 class="recovered">{this.state.racismIndex}</h2>
                                    </div>
                                    <div class="data">
                                        <p>Attacked Index:</p>
                                        <h2 class="confirmed">{this.state.attackedIndex}</h2>
                                    </div>
                                    <div class="data">
                                        <p>Drugs index:</p>
                                        <h2 class="death">{this.state.drugsIndex}</h2>
                                    </div>
                                    <div class="data">
                                        <p>Night Walking Safety Index:</p>
                                        <h2 class="recovered">{this.state.nightWalkingSafetyIndex}</h2>
                                    </div>
                          </div>
                        </div>


                          </div>
                        </div>

                        <div class="tab-pane fade" id="covid-news">
                    <div class="container" style={{position:'relative'}}>
                      <div class="pollutiontitle">
                        <h2 ><i>Covid News</i></h2>
                        </div>
                        <div class="search-br2">
                            <div class="example" >
                                <input onChange={this.myChangeHandler} type="text" style={{position:'relative',left:'-13%'}} placeholder="Search Location.." name="covidNewsSearch"/>
                                <input onChange={this.myChangeHandler} type="text" style={{position:'relative',left:'-13%'}} placeholder="Max Results.." name="covidNewsMaxRes"/><br />
                                <button onClick={this.covidNews} type="submit"><i class="fa fa-search"></i></button>
                            </div>

                            
                            {this.state.covidNewsHtml}
                        </div>


                          </div>
                        </div>


                        <div class="tab-pane fade" id="restaurant">
                    <div class="container" style={{position:'relative',left:'25%'}}>
                      <div class="pollutiontitle">
                        <h2 ><i>Restaurant Statistics</i></h2>
                        </div>
                        <div class="search-br2">
                            <div class="example" >
                                <input onChange={this.myChangeHandler} type="text" style={{position:'relative',left:'-13%'}} placeholder="Search Location.." name="restaurantLocation" />
                                <button onClick={this.restaurantStats} type="submit"><i class="fa fa-search"></i></button>
                            </div>

                            
                            <div class="weather-data">
                          <div class="data">
                                    <div class="data">
                                        <p>Simple Meal 1 Person:</p>
                                        <h2 class="recovered">{this.state.simpleMeal1PersonPrice}</h2>
                                    </div>
                                    <div class="data">
                                        <p>Full Meal 2 Persons:</p>
                                        <h2 class="confirmed">{this.state.fullMeal2PersonsPrice}</h2>
                                    </div>
                                    <div class="data">
                                        <p>Beer Draught:</p>
                                        <h2 class="death">{this.state.beerDraughtPrice}</h2>
                                    </div>
                                    <div class="data">
                                        <p>Beer Bottle:</p>
                                        <h2 class="recovered">{this.state.beerBottlePrice}</h2>
                                    </div>
                                    <p>Cappuccino :</p>
                                        <h2 class="recovered">{this.state.cappuccinoPrice}</h2>
                                    </div>
                                    <div class="data">
                                        <p>Water :</p>
                                        <h2 class="confirmed">{this.state.waterPrice}</h2>
                                    </div>
                                    <div class="data">
                                        <p>Coke:</p>
                                        <h2 class="death">{this.state.cokePrice}</h2>
                                    </div>
                                    <div class="data">
                                        <p>MC Meal:</p>
                                        <h2 class="recovered">{this.state.mcMealPrice}</h2>
                                    </div>
                          </div>
                        </div>


                          </div>
                        </div>

                      </div>

            </div>



            </div>
        </div>
    </div>
</div>
        );*/
    }

}

export default StatisticsPage;