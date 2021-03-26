# EU Open Data (Smart Booking) Backend

## About
The backend is available at this url : `incomingurl.com`

## API Endpoints
The following are only for internal testing
* `get/users`
> Gets all the users from database
* `post/user`
> Add a user to database. The json object from body looks like this: <br>
>{<br>
>   "username: value,<br>
>   "email": value, <br>
>   "password": value <br>
>}

* `user/get_locations`
> Get the location for a given user
> The returned type will be decided soon

* `user/add_location`
> Add a location to user

* `user/login`
> Login the user. The returned json object will look like this if successful:
>{<br>
>   "key": value,<br>,
>   "user_object": user<br>
>}

* `user/register`
> Signup the user. The returned json object will look like this if successful:
>{<br>
>   "key": value,<br>,
>   "user_object": user<br>
>}