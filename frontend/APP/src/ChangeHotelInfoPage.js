import './changeHotelInfo.css';
const react = require("react");

class ChangeHotelInfoPage extends react.Component{
    constructor(props){
        super(props);
    }

    render(){
        return( 
<div class="changeHotelInfo">
    <div class="container">
    <div class="row flex-lg-nowrap">
      <div class="col-12 col-lg-auto mb-3" style={{width: '200px'}}>
        <div class="card p-3">
          <div class="e-navlist e-navlist--active-bg">
            <ul class="nav">
              <li class="nav-item"><a class="nav-link px-2 active" href="./overview.html"><i class="fa fa-fw fa-bar-chart mr-1"></i><span>Overview</span></a></li>
              <li class="nav-item"><a class="nav-link px-2" href="./users.html"><i class="fa fa-fw fa-user mr-1"></i><span>Profile</span></a></li>
              <li class="nav-item"><a class="nav-link px-2" href="./settings.html"><i class="fa fa-fw fa-cog mr-1"></i><span>Settings</span></a></li>
            </ul>
          </div>
        </div>
      </div>

      <div class="col">
        <div class="row">
          <div class="col mb-3">
            <div class="card">
              <div class="card-body">
                <div class="e-profile">
                  <div class="row">
                    <div class="col-12 col-sm-auto mb-3">
                      <div class="mx-auto" style={{width: '140px'}}>
                        <div class="d-flex justify-content-center align-items-center rounded" style={{height: '140px', backgroundColor: 'rgb(233, 236, 239)'}}>
                          <span> <img src={require("./images/34712772.jpg").default} alt="Paris" class="d-flex justify-content-center align-items-center rounded" /></span>

                        </div>
                      </div>
                    </div>
                    <div class="col d-flex flex-column flex-sm-row justify-content-between mb-3">
                      <div class="text-center text-sm-left mb-2 mb-sm-0">
                        <h4 class="pt-sm-2 pb-1 mb-0 text-nowrap">Hotel Café Royal</h4>
                        <p class="mb-0">@hotelcaferoyal.com</p>
                        <div class="text-muted"><small>Last seen 5 days ago</small></div>
                        <div class="mt-2">
                          <button class="btn btn-primary" type="button">
                            <i class="fa fa-fw fa-camera"></i>
                            <span>Change Photo</span>
                          </button>
                        </div>
                      </div>
                      <div class="text-center text-sm-right">
                        <span class="badge badge-secondary">administrator</span>
                        <div class="text-muted"><small>Joined 09 Jan. 2021</small></div>
                      </div>
                    </div>
                  </div>
                  </div>
                  <ul class="nav nav-tabs">
                    <li class="nav-item"><a href="" class="active nav-link">Settings</a></li>
                  </ul>
                  <div class="tab-content pt-3">
                    <div class="tab-pane active">
                      <form class="form" novalidate="">
                        <div class="row">
                          <div class="col">
                            <div class="row">
                              <div class="col">
                                <div class="form-group">
                                  <label>Name</label>
                                  <input class="form-control" type="text" name="name"  value="Hotel Corque"/>
                                </div>
                              </div>
                              <div class="col">
                                <div class="form-group">
                                  <label>Phone Number</label>
                                  <input class="form-control" type="text" name="username"  value="(+12) 123 1234 567"/>
                                </div>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col">
                                <div class="form-group">
                                  <label>Email</label>
                                  <input class="form-control" type="text" placeholder="user@example.com" value="coderthemes@gmail.com"/>
                                </div>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col">
                                <div class="form-group">
                                  <label>Location</label>
                                  <input class="form-control" type="text" placeholder="user@example.com" value="Solvang, United States"/>
                                </div>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col mb-3">
                                <div class="form-group">
                                  <label>Description</label>
                                  <textarea class="form-control" rows="5" placeholder="My Bio" >Located 5 minutes’ walk away from Old Mission Santa Ines, this boutique hotel offers a restaurant on site. It features an outdoor pool and hot tub and spacious room with free WiFi.</textarea>
                                </div>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col">
                                <div class="form-group">
                                  <label>Add Photos: </label>
                                  <input type="file" id="exampleInputFile"/>
                                  <p class="help-block">Type:PNG,JPGS,GIFS.</p>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="row">
                          <div class="col-12 col-sm-6 mb-3">
                            <div class="row">
                              <div class="col">
                                <div class="form-group">
                                  <label>Price</label>
                                  <input class="form-control" type="number" id="price" name="price" value="100" min="0"/>
                                </div>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col">
                                <div class="form-group">
                                  <label>Currency</label>
                                  <select class="form-control" name="currency" id="currency">
                                    <option value="USD" selected="selected" label="US dollar">USD</option>
                                    <option value="EUR" label="Euro">EUR</option>
                                    <option value="JPY" label="Japanese yen">JPY</option>
                                    <option value="GBP" label="Pound sterling">GBP</option>
                                    <option disabled>──────────</option>
                                    <option value="AED" label="United Arab Emirates dirham">AED</option>
                                    <option value="AFN" label="Afghan afghani">AFN</option>
                                    <option value="ALL" label="Albanian lek">ALL</option>
                                    <option value="AMD" label="Armenian dram">AMD</option>
                                    <option value="ANG" label="Netherlands Antillean guilder">ANG</option>
                                    <option value="AOA" label="Angolan kwanza">AOA</option>
                                    <option value="ARS" label="Argentine peso">ARS</option>
                                    <option value="AUD" label="Australian dollar">AUD</option>
                                    <option value="AWG" label="Aruban florin">AWG</option>
                                    <option value="AZN" label="Azerbaijani manat">AZN</option>
                                    <option value="BAM" label="Bosnia and Herzegovina convertible mark">BAM</option>
                                    <option value="BBD" label="Barbadian dollar">BBD</option>
                                    <option value="BDT" label="Bangladeshi taka">BDT</option>
                                    <option value="BGN" label="Bulgarian lev">BGN</option>
                                    <option value="BHD" label="Bahraini dinar">BHD</option>
                                    <option value="BIF" label="Burundian franc">BIF</option>
                                    <option value="BMD" label="Bermudian dollar">BMD</option>
                                    <option value="BND" label="Brunei dollar">BND</option>
                                    <option value="BOB" label="Bolivian boliviano">BOB</option>
                                    <option value="BRL" label="Brazilian real">BRL</option>
                                    <option value="BSD" label="Bahamian dollar">BSD</option>
                                    <option value="BTN" label="Bhutanese ngultrum">BTN</option>
                                    <option value="BWP" label="Botswana pula">BWP</option>
                                    <option value="BYN" label="Belarusian ruble">BYN</option>
                                    <option value="BZD" label="Belize dollar">BZD</option>
                                    <option value="CAD" label="Canadian dollar">CAD</option>
                                    <option value="CDF" label="Congolese franc">CDF</option>
                                    <option value="CHF" label="Swiss franc">CHF</option>
                                    <option value="CLP" label="Chilean peso">CLP</option>
                                    <option value="CNY" label="Chinese yuan">CNY</option>
                                    <option value="COP" label="Colombian peso">COP</option>
                                    <option value="CRC" label="Costa Rican colón">CRC</option>
                                    <option value="CUC" label="Cuban convertible peso">CUC</option>
                                    <option value="CUP" label="Cuban peso">CUP</option>
                                    <option value="CVE" label="Cape Verdean escudo">CVE</option>
                                    <option value="CZK" label="Czech koruna">CZK</option>
                                    <option value="DJF" label="Djiboutian franc">DJF</option>
                                    <option value="DKK" label="Danish krone">DKK</option>
                                    <option value="DOP" label="Dominican peso">DOP</option>
                                    <option value="DZD" label="Algerian dinar">DZD</option>
                                    <option value="EGP" label="Egyptian pound">EGP</option>
                                    <option value="ERN" label="Eritrean nakfa">ERN</option>
                                    <option value="ETB" label="Ethiopian birr">ETB</option>
                                    <option value="EUR" label="EURO">EUR</option>
                                    <option value="FJD" label="Fijian dollar">FJD</option>
                                    <option value="FKP" label="Falkland Islands pound">FKP</option>
                                    <option value="GBP" label="British pound">GBP</option>
                                    <option value="GEL" label="Georgian lari">GEL</option>
                                    <option value="GGP" label="Guernsey pound">GGP</option>
                                    <option value="GHS" label="Ghanaian cedi">GHS</option>
                                    <option value="GIP" label="Gibraltar pound">GIP</option>
                                    <option value="GMD" label="Gambian dalasi">GMD</option>
                                    <option value="GNF" label="Guinean franc">GNF</option>
                                    <option value="GTQ" label="Guatemalan quetzal">GTQ</option>
                                    <option value="GYD" label="Guyanese dollar">GYD</option>
                                    <option value="HKD" label="Hong Kong dollar">HKD</option>
                                    <option value="HNL" label="Honduran lempira">HNL</option>
                                    <option value="HKD" label="Hong Kong dollar">HKD</option>
                                    <option value="HRK" label="Croatian kuna">HRK</option>
                                    <option value="HTG" label="Haitian gourde">HTG</option>
                                    <option value="HUF" label="Hungarian forint">HUF</option>
                                    <option value="IDR" label="Indonesian rupiah">IDR</option>
                                    <option value="ILS" label="Israeli new shekel">ILS</option>
                                    <option value="IMP" label="Manx pound">IMP</option>
                                    <option value="INR" label="Indian rupee">INR</option>
                                    <option value="IQD" label="Iraqi dinar">IQD</option>
                                    <option value="IRR" label="Iranian rial">IRR</option>
                                    <option value="ISK" label="Icelandic króna">ISK</option>
                                    <option value="JEP" label="Jersey pound">JEP</option>
                                    <option value="JMD" label="Jamaican dollar">JMD</option>
                                    <option value="JOD" label="Jordanian dinar">JOD</option>
                                    <option value="JPY" label="Japanese yen">JPY</option>
                                    <option value="KES" label="Kenyan shilling">KES</option>
                                    <option value="KGS" label="Kyrgyzstani som">KGS</option>
                                    <option value="KHR" label="Cambodian riel">KHR</option>
                                    <option value="KID" label="Kiribati dollar">KID</option>
                                    <option value="KMF" label="Comorian franc">KMF</option>
                                    <option value="KPW" label="North Korean won">KPW</option>
                                    <option value="KRW" label="South Korean won">KRW</option>
                                    <option value="KWD" label="Kuwaiti dinar">KWD</option>
                                    <option value="KYD" label="Cayman Islands dollar">KYD</option>
                                    <option value="KZT" label="Kazakhstani tenge">KZT</option>
                                    <option value="LAK" label="Lao kip">LAK</option>
                                    <option value="LBP" label="Lebanese pound">LBP</option>
                                    <option value="LKR" label="Sri Lankan rupee">LKR</option>
                                    <option value="LRD" label="Liberian dollar">LRD</option>
                                    <option value="LSL" label="Lesotho loti">LSL</option>
                                    <option value="LYD" label="Libyan dinar">LYD</option>
                                    <option value="MAD" label="Moroccan dirham">MAD</option>
                                    <option value="MDL" label="Moldovan leu">MDL</option>
                                    <option value="MGA" label="Malagasy ariary">MGA</option>
                                    <option value="MKD" label="Macedonian denar">MKD</option>
                                    <option value="MMK" label="Burmese kyat">MMK</option>
                                    <option value="MNT" label="Mongolian tögrög">MNT</option>
                                    <option value="MOP" label="Macanese pataca">MOP</option>
                                    <option value="MRU" label="Mauritanian ouguiya">MRU</option>
                                    <option value="MUR" label="Mauritian rupee">MUR</option>
                                    <option value="MVR" label="Maldivian rufiyaa">MVR</option>
                                    <option value="MWK" label="Malawian kwacha">MWK</option>
                                    <option value="MXN" label="Mexican peso">MXN</option>
                                    <option value="MYR" label="Malaysian ringgit">MYR</option>
                                    <option value="MZN" label="Mozambican metical">MZN</option>
                                    <option value="NAD" label="Namibian dollar">NAD</option>
                                    <option value="NGN" label="Nigerian naira">NGN</option>
                                    <option value="NIO" label="Nicaraguan córdoba">NIO</option>
                                    <option value="NOK" label="Norwegian krone">NOK</option>
                                    <option value="NPR" label="Nepalese rupee">NPR</option>
                                    <option value="NZD" label="New Zealand dollar">NZD</option>
                                    <option value="OMR" label="Omani rial">OMR</option>
                                    <option value="PAB" label="Panamanian balboa">PAB</option>
                                    <option value="PEN" label="Peruvian sol">PEN</option>
                                    <option value="PGK" label="Papua New Guinean kina">PGK</option>
                                    <option value="PHP" label="Philippine peso">PHP</option>
                                    <option value="PKR" label="Pakistani rupee">PKR</option>
                                    <option value="PLN" label="Polish złoty">PLN</option>
                                    <option value="PRB" label="Transnistrian ruble">PRB</option>
                                    <option value="PYG" label="Paraguayan guaraní">PYG</option>
                                    <option value="QAR" label="Qatari riyal">QAR</option>
                                    <option value="RON" label="Romanian leu">RON</option>
                                    <option value="RSD" label="Serbian dinar">RSD</option>
                                    <option value="RUB" label="Russian ruble">RUB</option>
                                    <option value="RWF" label="Rwandan franc">RWF</option>
                                    <option value="SAR" label="Saudi riyal">SAR</option>
                                    <option value="SEK" label="Swedish krona">SEK</option>
                                    <option value="SGD" label="Singapore dollar">SGD</option>
                                    <option value="SHP" label="Saint Helena pound">SHP</option>
                                    <option value="SLL" label="Sierra Leonean leone">SLL</option>
                                    <option value="SLS" label="Somaliland shilling">SLS</option>
                                    <option value="SOS" label="Somali shilling">SOS</option>
                                    <option value="SRD" label="Surinamese dollar">SRD</option>
                                    <option value="SSP" label="South Sudanese pound">SSP</option>
                                    <option value="STN" label="São Tomé and Príncipe dobra">STN</option>
                                    <option value="SYP" label="Syrian pound">SYP</option>
                                    <option value="SZL" label="Swazi lilangeni">SZL</option>
                                    <option value="THB" label="Thai baht">THB</option>
                                    <option value="TJS" label="Tajikistani somoni">TJS</option>
                                    <option value="TMT" label="Turkmenistan manat">TMT</option>
                                    <option value="TND" label="Tunisian dinar">TND</option>
                                    <option value="TOP" label="Tongan paʻanga">TOP</option>
                                    <option value="TRY" label="Turkish lira">TRY</option>
                                    <option value="TTD" label="Trinidad and Tobago dollar">TTD</option>
                                    <option value="TVD" label="Tuvaluan dollar">TVD</option>
                                    <option value="TWD" label="New Taiwan dollar">TWD</option>
                                    <option value="TZS" label="Tanzanian shilling">TZS</option>
                                    <option value="UAH" label="Ukrainian hryvnia">UAH</option>
                                    <option value="UGX" label="Ugandan shilling">UGX</option>
                                    <option value="USD" label="United States dollar">USD</option>
                                    <option value="UYU" label="Uruguayan peso">UYU</option>
                                    <option value="UZS" label="Uzbekistani soʻm">UZS</option>
                                    <option value="VES" label="Venezuelan bolívar soberano">VES</option>
                                    <option value="VND" label="Vietnamese đồng">VND</option>
                                    <option value="VUV" label="Vanuatu vatu">VUV</option>
                                    <option value="WST" label="Samoan tālā">WST</option>
                                    <option value="XAF" label="Central African CFA franc">XAF</option>
                                    <option value="XCD" label="Eastern Caribbean dollar">XCD</option>
                                    <option value="XOF" label="West African CFA franc">XOF</option>
                                    <option value="XPF" label="CFP franc">XPF</option>
                                    <option value="ZAR" label="South African rand">ZAR</option>
                                    <option value="ZMW" label="Zambian kwacha">ZMW</option>
                                    <option value="ZWB" label="Zimbabwean bonds">ZWB</option>
                                  </select>
                                </div>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col">
                                <div class="form-group">
                                  <label>Number of Rooms</label>
                                  <input class="form-control" type="number" value="10" min="1"/>
                                </div>
                              </div>
                            </div>
                            <div class="custom-control custom-checkbox">
                              <input type="checkbox" class="custom-control-input" id="notifications-offers" checked=""/>
                              <label class="custom-control-label" for="notifications-offers">Email Notifications</label>
                            </div>
                          </div>
                          <div class="col-12 col-sm-5 offset-sm-1 mb-3">
                            <div class="mb-2"><b>Facilities</b></div>
                            <div class="row">
                              <div class="col">
                                <div class="custom-controls-stacked px-2">
                                  <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="facilities1" checked=""/>
                                    <label class="custom-control-label" for="facilities1">Restaurant</label>
                                  </div>
                                  <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="facilities2" checked=""/>
                                    <label class="custom-control-label" for="facilities2">Cafeteria</label>
                                  </div>
                                  <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="facilities3" checked=""/>
                                    <label class="custom-control-label" for="facilities3">Swimming Pool</label>
                                  </div>
                                  <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="facilities4" checked=""/>
                                    <label class="custom-control-label" for="facilities4">Fitness Center</label>
                                  </div>
                                  <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="facilities5" checked=""/>
                                    <label class="custom-control-label" for="facilities5">Pet Friendly</label>
                                  </div>
                                  <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="facilities6" checked=""/>
                                    <label class="custom-control-label" for="facilities6">Terrace</label>
                                  </div>
                                  <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="facilities7" checked=""/>
                                    <label class="custom-control-label" for="facilities7">Parking</label>
                                  </div>
                                  <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="facilities8" checked=""/>
                                    <label class="custom-control-label" for="facilities8">Sauna</label>
                                  </div>
                                  <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="facilities9" checked=""/>
                                    <label class="custom-control-label" for="facilities9">Walking Tours</label>
                                  </div>
                                  <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="facilities10" checked=""/>
                                    <label class="custom-control-label" for="facilities10">Live
                                      music/performance</label>
                                  </div>
                                </div>

                              </div>
                            </div>

                          </div>
                        </div>
                        <div class="row">
                          <div class="col d-flex justify-content-end">
                            <button class="btn btn-primary" type="submit">Save Changes</button>
                          </div>
                        </div>
                      </form>

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
            );
    }
}

export default ChangeHotelInfoPage;
