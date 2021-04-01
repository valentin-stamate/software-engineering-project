import React from 'react';
import './home.css';

class HomePage extends React.Component{
    render(){
        return (
            <div>
            <header class="header" id="header">
                <div class="head-top">
                    <div class="site-logo">
                        <img src={require("./images/Logo_SmartBooking-01.png").default} />
                    </div>
                    <div style={{position:'relative'}} style={{right:'42%'}}>
                        <p>Smart Booking</p>
                    </div>
                    <div class="site-nav">
                        <a href="/login"><span id="nav-btn">Login <i class="fas fa-bars"></i></span></a>
                    </div>
                </div>
        
                <div class="head-bottom flex">
                    <h2>Your journey starts here</h2>
                    <p></p>
                    <a href="#book."> <button type="button" class="head-btn" >Search</button> </a>
                </div>
            </header>
            <div id="book." class="book">
                <form class="book-form">
                    <div class="form-item">
                        <label for="checkin-date">Check In Date: </label>
                        <input type="date" id="chekin-date" />
                    </div>
                    <div class="form-item">
                        <label for="checkout-date">Check Out Date: </label>
                        <input type="date" id="chekout-date" />
                    </div>
                    <div class="form-item">
                        <label for="adult">Destination: </label>
                        <input type="text" min="1" value="1" id="adult" />
                    </div>
                   
                    <div class="form-item">
                        <input type="submit" class="btn" value="Search now" />
                    </div>
                </form>
            </div>
            <div class = "sidenav" id = "sidenav">
                <span class = "cancel-btn" id = "cancel-btn">
                    <i class = "fas fa-times"></i>
                </span>
    
                <ul class = "navbar">
                    <li><a href = "#header">home</a></li>
                    <li><a href = "#services">services</a></li>
                    <li><a href = "#customers">customers</a></li>
                </ul>
                <button class = "btn sign-up">sign up</button>
                <button class = "btn log-in">log in</button>
            </div>
    
            <div id = "modal"></div>

            <section class = "services sec-width" id = "services">
                <div class = "title">
                    <h2>services</h2>
                </div>
                <div class = "services-container">
                    <article class = "service">
                        <div class = "service-icon">
                            <span>
                                <i class = "fas fa-hotel"></i>
                            </span>
                        </div>
                        <div class = "service-content">
                            <h2>Booking Services</h2>
                            <p>Our site seamlessly connects millions of travellers with memorable experiences, a range of transport options and incredible places to stay - from homes to hotels and much more. Smart Booking enables properties all over the world to reach a global audience and grow their businesses in a very easy way.</p>
                        </div>
                    </article>
                    <article class = "service">
                        <div class = "service-icon">
                            <span>
                                <i class = "fas fa-cloud-sun"></i>
                            </span>
                        </div>
                        <div class = "service-content">
                            <h2>Weather conditions</h2>
                            <p>Know the weather before your trip to be sure you will have sunny and beautiful days to sightsee the places you visit. Our site offers you easy to understand and colourful charts to know the weather forecast for any place in the world!</p>
                        </div>
                    </article>
                    <article class = "service">
                        <div class = "service-icon">
                            <span>
                                <i class="fas fa-smog"></i>
                            </span>
                        </div>
                        <div class = "service-content">
                            <h2>Pollution levels</h2>
                            <p>Get to know about the air, water, street, audio pollution levels in the areas you want to visit. This way you can know if the places you go to are trully the right ones for you and your health.</p>
                        </div>
                    </article>
                    <article class = "service">
                        <div class = "service-icon">
                            <span>
                                <i class = "fas fa-viruses"></i>
                            </span>
                        </div>
                        <div class = "service-content">
                            <h2>Latest coronavirus information</h2>
                            <p>Find out the latest news about coronavirus, which places are safe to visit and what are the restrictions in said places. We care about your health so we made all the informations accesible for you</p>
                        </div>
                    </article>
                </div>
            </section>
    
    
            <section class = "suggestions sec-width" id = "suggestions">
                <div class = "title">
                    <h2>suggestion</h2>
                </div>
                <div class = "suggestions-container">
                    <article class = "suggestion">
                        <div class = "suggestion-image">
                            <img src = {require("./images/Paris.jpg").default} alt = ".image" />
                        </div>
                        <div class = "suggestion-text">
                            <h3>Paris</h3>
                            <p>Place to visit:</p>
                            <ul>
                                <li>
                                    <i class = "fas fa-arrow-alt-circle-right"></i>
                                    The Eiffel Tower
                                </li>
                                <li>
                                    <i class = "fas fa-arrow-alt-circle-right"></i>
                                   Notre-Dame Cathedral
                                </li>
                                <li>
                                    <i class = "fas fa-arrow-alt-circle-right"></i>
                                    Louvre Muzeum
                                </li>
                            </ul>
                            <p>Paris, capital of France, is one of the most important and influential cities in the world. In terms of tourism, Paris is the second most visited city in Europe after London. The capital of France seems to have been designed specifically for the enjoyment of its visitors. Its streets, squares, buildings, gardens and monuments beckon tourists to return, and indeed, many do. Some of the most memorable things to do in Paris include visiting the Eiffel Tower, the Arc de Triomphe and Notre-Dame Cathedral. During the evening, experiencing one of the legendary Moulin Rouge cabaret shows, strolling through some of the most picturesque neighborhoods, like Montmartre, or climbing the Montparnasse Tower are a must.
    
                                It is always a good time to visit Paris. </p>
                            
                            
                            <button type = "button" class = "btn">book now</button>
                        </div>
                    </article>
                    <article class = "suggestion">
                        <div class = "suggestion-image">
                            <img src = {require("./images/atena.jpg").default} alt = ".image" />
                        </div>
                        <div class = "suggestion-text">
                            <h3>Atena</h3>
                            <p>Places to visit:</p>
                            <ul>
                                <li>
                                    <i class = "fas fa-arrow-alt-circle-right"></i>
                                    Acropole
                                </li>
                                <li>
                                    <i class = "fas fa-arrow-alt-circle-right"></i>
                                    Olympieion
                                </li>
                                <li>
                                    <i class = "fas fa-arrow-alt-circle-right"></i>
                                    Theatre of Dionysus
                                </li>
                            </ul>
                            <p>Athens. This gigantic city sprawls out over 1,131 square miles (2,929 square kilometers) and is home to over 664,000 people. It’s been a city since its founding in 508 BC.
    
                                And it’s one of the most visited places in Greece! It makes for the perfect start/end of a trip (mostly because you have to fly out of the city if you want to go anywhere international).
                                
                                This is a city steeped in history and has some of the largest collection of Greek ruins and artifacts in the world.
                                
                                However, outside the ruins and the beautiful Plaka, I tend to find Athens is a city filled with graffiti and trash. I love the history, I love the chaos, I love the Greeks, I just don’t love the city. Even the folks I know from Athens try to get away.
                                
                                Given the amount of history and sights there are here, I’d plan to spend about three days here. You’ll need it to see everything here!</p>
                            
                            
                            <button type = "button" class = "btn">book now</button>
                        </div>
                    </article>
                    <article class = "suggestion">
                        <div class = "suggestion-image">
                            <img src = {require("./images/bangkok.jpg").default} alt = ".image" />
                        </div>
                        <div class = "suggestion-text">
                            <h3>Bangkok</h3>
                            <p>Places to visit:</p>
                            <ul>
                                <li>
                                    <i class = "fas fa-arrow-alt-circle-right"></i>
                                    
                                    Wat Traimit Temple
                                </li>
                                <li>
                                    <i class = "fas fa-arrow-alt-circle-right"></i>
                                    The Great Palace and Wat Prakeaw
                                </li>
                                <li>
                                    <i class = "fas fa-arrow-alt-circle-right"></i>
                                   The National Museum and Palace Wang Na
                                </li>
                            </ul>
                            <p>Welcome to Bangkok – a sprawling, humid metropolis of more than 10 million souls that rose along the eastern banks of the Chao Phraya river a little more than 200 years ago. Today, the Thai capital brims with interesting historic sites, stylish hotels, incredible culinary adventures, and fantastic shopping, and none of this need break the bank. The city has had some success in shedding its longstanding image of sleaze for a younger, more cosmopolitan mantle and is a pretty safe urban space. And while the military government has put the break on non-stop partying, the arts scene and the world-famous street food culture, many visitors continue to feel enchanted by this cornucopia of sights, sounds, smells, tastes and moods. </p>
                            
                            
                            <button type = "button" class = "btn">book now</button>
                        </div>
                    </article>
                </div>
            </section>
    
    
            <section class = "customers" id = "customers">
                <div class = "sec-width">
                    <div class = "title">
                        <h2>customers</h2>
                    </div>
                    <div class = "customers-container">
                        <div class = "customer">
                            <div class = "rating">
                                <span><i class = "fas fa-star"></i></span>
                                <span><i class = "fas fa-star"></i></span>
                                <span><i class = "fas fa-star"></i></span>
                                <span><i class = "fas fa-star"></i></span>
                                <span><i class = "far fa-star"></i></span>
                            </div>
                            <h3>We Loved it</h3>
                            <p>Very proffesional and effective. Thank you for lettinf us have an easy experience in planning our trip. </p>
                            <img src = {require("./images/pers1.jpg").default} alt = "customer image" />
                            <span>Ava Stone</span>
                        </div>
                        <div class = "customer">
                            <div class = "rating">
                                <span><i class = "fas fa-star"></i></span>
                                <span><i class = "fas fa-star"></i></span>
                                <span><i class = "fas fa-star"></i></span>
                                <span><i class = "fas fa-star"></i></span>
                                <span><i class = "far fa-star"></i></span>
                            </div>
                            <h3>An unforgetable holiday</h3>
                            <p>Thank everyone here for making our first family vacation wonderful!</p>
                            <img src = {require("./images/pers.jpg").default} alt = "customer image" />
                            <span>Alina Butnariu</span>
                        </div>
                        <div class = "customer">
                            <div class = "rating">
                                <span><i class = "fas fa-star"></i></span>
                                <span><i class = "fas fa-star"></i></span>
                                <span><i class = "fas fa-star"></i></span>
                                <span><i class = "fas fa-star"></i></span>
                                <span><i class = "far fa-star"></i></span>
                            </div>
                            <h3>A remarcable experience!</h3>
                            <p>Our experience here was trully memorable and without prpoblems.</p>
                            <img src = {require("./images/pers3.jpg").default} alt = "customer image" />
                            <span>Alexandru Stan</span>
                        </div>
                    </div>
                </div>
            </section>
            <footer class = "footer">
                <div class = "footer-container">
                    <div>
                        <h2>About Us </h2>
                        <p>We are a group of students that want to make your travel experience easier. Here you can find the best places to book and useful info about different locations in coulourful charts.</p>
                        <ul class = "social-icons">
                            <li class = "flex">
                                <i class = "fa fa-twitter fa-2x"></i>
                            </li>
                            <li class = "flex">
                                <i class = "fa fa-facebook fa-2x"></i>
                            </li>
                            <li class = "flex">
                                <i class = "fa fa-instagram fa-2x"></i>
                            </li>
                        </ul>
                    </div>
    
                    <div>
                        <h2>Useful Links</h2>
                        <a href = "#">FAQ</a>
                        <a href = "#">About us</a>
                        <a href = "#">Join us</a>
                    </div>
    
                    <div>
                        <h2>Tell us about you</h2>
                        <a href = "#">Survey</a>
                        <a href = "#">Contact Us</a>
                    </div>
    
                    <div>
                        <h2>Have A Question</h2>
                        <div class = "contact-item">
                            <span>
                                <i class = "fas fa-map-marker-alt"></i>
                            </span>
                            <span>
                                16, General Henri Mathias Berthelot Street, Iași, România
                            </span>
                        </div>
                        <div class = "contact-item">
                            <span>
                                <i class = "fas fa-phone-alt"></i>
                            </span>
                            <span>
                                +84545 37534 48
                            </span>
                        </div>
                        <div class = "contact-item">
                            <span>
                                <i class = "fas fa-envelope"></i>
                            </span>
                            <span>
                                info@domain.com
                            </span>
                        </div>
                    </div>
                </div>
            </footer>
            <script src="https://kit.fontawesome.com/dbed6b6114.js" crossorigin="anonymous"></script>
            </div>
        );
    }
}

export default HomePage;