# EU Open Data (Smart Booking) Backend

## About
The backend is available at this url : `incomingurl.com`

## API Endpoints

* `user/login`
> Login the user. The endpoint will receive the users as follows: <br>
>{<br>
>   "username": value,<br>
>   "password": value<br>
>}
> The response will look like this if successful(empty otherwise):
>{<br>
>   "userId": value,<br>
>   "username": value,<br>
>   "email": value,<br>
>   "profilePhotoLink": value,<br>
>   
>}

* `user/get_locations`
> Get the location for a given user
> The returned type will be decided soon

* `user/add_location`
> Add a location to user


* `user/register`
> Signup the user. The returned json object will look like this if successful:
>{<br>
>   "key": value,<br>,
>   "user_object": user<br>
>}