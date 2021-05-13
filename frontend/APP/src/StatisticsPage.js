import react from 'react';
import './statistics.css';
import Statistics from './Statistics.js';
import { Helmet } from 'react-helmet';

class StatisticsPage extends react.Component{
    constructor(props){
        super(props);

        this.state={
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
            snow:0
        };
    }

    myChangeHandler = (event) => {
        let nam = event.target.name;
        let val = event.target.value;
        this.setState({[nam] : val});
    }

    covidStats = () => {
        var stats=Statistics.getCovidStatistics(this.state.covidSearch,this.state.covidStartDate,this.state.covidEndDate);
        var deaths=0;
        var cases=0;

        for(const[index,stat] of stats.entries()){
            deaths+=stats.newDeaths;
            cases+=stats.newCases;
        }

        this.setState({covidNewCases:cases,covidNewDeaths:deaths});
    }

    wheaterStats = () => {
        var stats=Statistics.getWeatherStatistics(this.state.wheaterSearch,this.state.wheaterDate);
        var data=stats.data[0];
        this.setState({windSpeed:data.wind_spd,clouds:data.clouds,prec:data.precip,pressure:data.pres,snow:data.snow,maxTemp:data.max_temp,minTemp:data.min_temp,maxUV:data.max_uv});
    }




    render(){
        
        return(
            <div class="stats">
                <div class="topnav">
        <a class="active" href="#home">Home</a>
        <a href="#">See Favorites </a>
        <a href="#">Sign Out</a>

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

                    </ul>
                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane fade active in" id="more-information">

                            <div class="content" style={{position:'relative',left:'40%'}}>
                                <div class="tab-pane active " id="profile">
                     <div class="col-sm">
                                        <div class="logo">
                            <h2 >C<i class="fas fa-virus "></i>vid-19</h2>
                                        </div>


                                    <div class="search-br">
                                      <form class="example" aria-disabled>
                                        <input onChange={this.myChangeHandler} type="text" placeholder="Search.." name="covidSearch"/>
                                        <input onChange={this.myChangeHandler} type="text" placeholder="start date" name="covidStartDate" />
                                        <input onChange={this.myChangeHandler} type="text" placeholder="end date" name="covidEndDate" />
                                        <button onClick={this.covidStats} style={{width:'15%'}}><i class="fa fa-search"></i></button>
                                        </form>
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
                                            <input onChange={this.myChangeHandler} type="text" placeholder="Search.." name="wheaterSearch"/>
                                            <input onChange={this.myChangeHandler} type="text" placeholder="date" name="wheaterDate" />
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
                        <div class="container" style={{position:'relative',left:'50%'}}>
                          <div class="pollutiontitle">
                            <h2 >Pollution <i class="fas fa-biohazard"></i></h2>
                            </div>
                            <div class="search-br2">
                                <form class="example" >
                                    <input type="text" placeholder="Search.." name="search"/>
                                    <button style={{width:'15%'}} type="submit"><i class="fa fa-search"></i></button>
                                </form>
                            </div>

                          <div class="weather-data">
                            <div class="table-responsive">
                              <table class="table">
                                <thead>
                                  <tr>
                                    <th>Day</th>
                                    <th>Date</th>
                                    <th>Max</th>
                                    <th>Min</th>
                                    <th>Pm10</th>
                                    <th>NO2</th>
                                    <th>SO2</th>

                                  </tr>
                                </thead>
                                <tbody>
                                  <tr>
                                    <td>Monday</td>
                                    <td>12.02.2021</td>
                                    <td>12</td>
                                    <td>9</td>
                                    <td>11</td>
                                    <td>4</td>
                                    <td>3</td>
                                  </tr>
                                  <tr>
                                    <td>Tuesday</td>
                                    <td>13.02.2021</td>
                                    <td>11</td>
                                    <td>8</td>
                                    <td>11</td>
                                    <td>4</td>
                                    <td>2</td>
                                  </tr>
                                  <tr>
                                    <td>Wednesday</td>
                                    <td>14.02.2021</td>
                                    <td>12</td>
                                    <td>7</td>
                                    <td>10</td>
                                    <td>4</td>
                                    <td>2</td>
                                  </tr>
                                  <tr>
                                    <td>Thursday</td>
                                    <td>16.02.2021</td>
                                    <td>10</td>
                                    <td>6</td>
                                    <td>12</td>
                                    <td>3</td>
                                    <td>3</td>
                                  </tr>
                                  <tr>
                                    <td>Friday</td>
                                    <td>17.02.2021</td>
                                    <td>9</td>
                                    <td>6</td>
                                    <td>10</td>
                                    <td>4</td>
                                    <td>2</td>
                                  </tr>
                                  <tr>
                                    <td>Sturday</td>
                                    <td>18.02.2021</td>
                                    <td>12</td>
                                    <td>8</td>
                                    <td>10</td>
                                    <td>3</td>
                                    <td>4</td>
                                  </tr>
                                  <tr>
                                    <td>Sunday</td>
                                    <td>19.02.2021</td>
                                    <td>11</td>
                                    <td>7</td>
                                    <td>11</td>
                                    <td>4</td>
                                    <td>3</td>
                                  </tr>
                                </tbody>
                              </table>
                            </div>
                          </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="crime">
                    <div class="container">
                      <div class="pollutiontitle">
                        <h2 ><i>Crime Rate</i></h2>
                        </div>
                        <div class="search-br2">
                            <form class="example" >
                                <input type="text" placeholder="Search Location.." name="search"/>
                                <button type="submit"><i class="fa fa-search"></i></button>
                            </form>

                            <label for="dateofbirth">Search by Date</label>
                            <input type="date" name="dateofbirth" id="dateofbirth"/>
                            <h2 class="crime-rate">Crime Rate: </h2>
                        </div>


                          </div>
                        </div>
                      </div>

            </div>



            </div>
        </div>
    </div>
</div>
        );
    }

}

export default StatisticsPage;