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

* `user/register`
> Register the user. The body for request will look like this:<br>
>{<br>
>   "username": value,<br>
>   "email": value,<br>
>   "password": value,<br>
>   "profilePhotoLink": value,<br>
>}<br>
> The returned JSON:<br>
> {<br>
>     "status": "failed",<br>
>     "reason": "invalid form data" (present) field on failure<br>
>}<br>
> 


