# EU Open Data (Smart Booking) Backend

## About
The backend is available at this url : `http://188.34.167.200:8082/`

## API Endpoints
In order to access user data, the client must log in first and for every request it should have an "Authorization" header, with the value containing the token.

For example, the header could be "`Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9`" if the token received from logging in is "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9".

### Login
* `user/login`
  Login the user. The endpoint will receive the users as follows in the request body:
<details>
  <summary>Request body</summary>

```json
{
  "username": "the-username",
  "password": "the-password"
}
```

</details>

The response will look like this if successful, with status code 200:
<details>
  <summary>Request response</summary>

```json
{
  "username": "the-username",
  "email": "the-email",
  "profilePhotoLink": "picture-url",
  "authorizationToken": "token"
}
```
</details>

The response will look like this if unsuccessful, with status code 400 or 401:
<details>
  <summary>Request response</summary>

```json
{
   "message": "the-error-message"
}
```
</details>
The client should therefore check the status code for errors.

### Register
* `user/register`
  Register the user. The body for request will look like this:
<details>
<summary>Request body</summary>

```json
{
   "username": "the-username",
   "email": "the-email",
   "password": "the-password",
   "profilePhotoLink": "the-photo-url"
}
```
</details>

The response will look like this if successful, with status code 200:
<details>
<summary>Request response</summary>

```json
{
   "message": "registration successful"
}
```
</details>

The response will look like this if unsuccessful, with status code 400:
<details>
<summary>Request response</summary>

```json
{
   "message": "the-error-message"
}
```
</details>

After the user is registered a token will be sent to the provided email to verify the user.
When the user is activated it can log in.

### User Hotels Information
* `user/hotels`
  See all the saved hotels. The Authorization header with the user token should be present.
  The response will look like this if successful, with status code 200:

<details>
<summary>Request response</summary>

```json
[
  {
    "hotel": {
      "hotelName": "hotel-name",
      "locationName": "hotel-location"
    },
    "weather": {
      "coord": {
        "lon": 1.0,
        "lat": 1.0
      },
      "weather": [
        {
          "id": 100,
          "main": "Clear",
          "description": "clear sky",
          "icon": "icon"
        }
      ],
      "base": "stations",
      "main": {
        "temp": 1.0,
        "feels_like": 1.0,
        "temp_min": 1.0,
        "temp_max": 1.0,
        "pressure": 10,
        "humidity": 26
      },
      "visibility": 10000,
      "wind": {
        "speed": 1.0,
        "deg": 0
      },
      "rain": {
        "_1h": 1.0
      },
      "clouds": {
        "all": 0
      },
      "dt": 1,
      "sys": {
        "type": 1,
        "id": 1,
        "country": "RO",
        "sunrise": 1617940441,
        "sunset": 1617987793
      },
      "timezone": 10800,
      "id": 664205,
      "name": "city",
      "cod": 200
    },
    "covidInformation": {
      "information": "API still in development"
    },
    "airPollution": {
      "airQualityIndex": 1,
      "pm10Value": 1,
      "airPressure": 1,
      "airHumidity": 1.0
    }
  }
]
```
</details>

The response will look like this if unsuccessful, with status code 400:
<details>
<summary>Request response</summary>

```json
{
   "message": "the-error-message"
}
```
</details>

### Save a hotel
* `user/add_hotel`
  Add a hotel from a specified. The Authorization header with the user token should be present.
  The body for request will look like this:

<details>
<summary>Request body</summary>

```json
{
   "hotelName": "the-hotel-name",
   "locationName": "the-email"
}
```
</details>

The response will look like this if successful, with status code 200:
<details>
<summary>Request response</summary>

```json
{
   "message": "registration successful"
}
```
</details>
The response will look like this if unsuccessful, with status code 401, 406:

<details>
<summary>Request response</summary>

```json
{
   "message": "the-error-message"
}
```
</details>

### Delete a hotel
* `user/delete_hotel`
  Delete a hotel from a specified. The Authorization header with the user token should be present.

<details>
<summary>Request body</summary>

```json
{
   "hotelName": "the-hotel-name"
}
```
</details>

The response will look like this if successful, with status code 200:
<details>
<summary>Request response</summary>

```json
{
   "message": "registration successful"
}
```
</details>

The response will look like this if unsuccessful, with status code 401, 404, 406:
<details>
<summary>Request response</summary>

```json
{
   "message": "the-error-message"
}
```
</details>

### See information about a hotel
* `user/hotel_information`
  See the hotel information. The endpoint will receive hotel data as follows in the request body:

<details>
<summary>Request body</summary>

```json
{
  "hotelName": "hotel-name",
  "locationName": "hotel-location"
}
```
</details>

The response will look like this if successful, with status code 200:
<details>
<summary>Request response</summary>

```json
{
  "hotel": {
    "hotelName": "hotel-name",
    "locationName": "hotel-location"
  },
  "weather": {
    "coord": {
      "lon": 1.0,
      "lat": 1.0
    },
    "weather": [
      {
        "id": 100,
        "main": "Clear",
        "description": "clear sky",
        "icon": "icon"
      }
    ],
    "base": "stations",
    "main": {
      "temp": 1.0,
      "feels_like": 1.0,
      "temp_min": 1.0,
      "temp_max": 1.0,
      "pressure": 10,
      "humidity": 26
    },
    "visibility": 10000,
    "wind": {
      "speed": 1.0,
      "deg": 0
    },
    "rain": {
      "_1h": 1.0
    },
    "clouds": {
      "all": 0
    },
    "dt": 1,
    "sys": {
      "type": 1,
      "id": 1,
      "country": "RO",
      "sunrise": 1617940441,
      "sunset": 1617987793
    },
    "timezone": 10800,
    "id": 664205,
    "name": "city",
    "cod": 200
  },
  "covidInformation": {
    "title": "title",
    "link": "link",
    "displayLink": "display-link",
    "snippet": "about"
  },
  "airPollution": {
    "airQualityIndex": 1,
    "pm10Value": 1,
    "airPressure": 1,
    "airHumidity": 1.0
  }
}
```
</details>

The response will look like this if unsuccessful, with status code 400:
<details>
<summary>Request response</summary>

```json
{
   "message": "the-error-message"
}
```
</details>

## Dummy User

In order to test out API you can create an account or log in with an already existent user.
<details>
  <summary>Request body</summary>

```json
{
    "username": "ValentinSt",
    "password": "dcy3w8r7ds4lr329"
}
```
</details>
