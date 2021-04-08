# EU Open Data (Smart Booking) Backend

## About
The backend is available at this url : `http://188.34.167.200:8082/`

## API Endpoints
In order to authorize requests, the client must first login and then send subsequent requests containing an "Authorization" header, with the value containing the token.  
  
For example, the header could be "Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" if the token received from logging in is "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9".

* `user/login`
Login the user. The endpoint will receive the users as follows in the request body:
```json
{
   "username": "the-username",
   "password": "the-password"
}
```

The response will look like this if successful, with status code 200:
```json
{
   "message": "authentication successful",
   "token": "the-token"
}
```

The response will look like this if unsuccessful, with status code 400 or 401:
```json
{
   "message": "the-error-message"
}
```
The client should therefore check the status code for errors.

* `user/register`
Register the user. The body for request will look like this:
```json
{
   "displayName": "the-display-name",
   "username": "the-username",
   "password": "the-password",
   "email": "the-email",
   "profilePhotoLink": "the-photo-url"
}
```

The response will look like this if successful, with status code 200:
```json
{
   "message": "registration successful"
}
```

The response will look like this if unsuccessful, with status code 400:
```json
{
   "message": "the-error-message"
}
```
The client should therefore check the status code for errors.
