# Congestion Tax Calculator

This project is about a development of a project for an interview at Volvo Cars.
The project is called Congestion Tax Calculator.

## Starting project & Executing

This project was coded using Java 17.

### Starting project

Navigate to the ***root*** folder and execute the command:

```
java -jar .\congestion-tax-calculator-api-0.0.1-SNAPSHOT.jar
```

### Executing

Call the endpoint through an HTTP GET request informing `vehicleModel` as path parameter and a valid `dates` json object in the body:

```
localhost:8080/api/tax-congestion-calculator/{vehicleModel}
```

Example for ***vehicleModel***:

```
car
```

Example for ***body***:
```
{
    "datesTime": [
        "2013-01-14 21:00:00",
        "2013-02-08 15:29:00",
        "2013-02-08 21:00:00",
        "2013-02-12 15:40:00",
        "2013-02-12 16:05:00",
        "2013-02-12 21:00:00",
        "2013-02-13 17:00:00",
        "2013-02-13 17:05:00",
        "2013-02-13 17:50:00"
    ]
}
```

Curl:

```
curl -X GET \
  -H "Content-type: application/json" \
  -H "Accept: application/json" \
  -d '{
    "datesTime": [
        "2013-01-14 21:00:00",
        "2013-02-08 15:29:00",
        "2013-02-08 21:00:00",
        "2013-02-12 15:40:00",
        "2013-02-12 16:05:00",
        "2013-02-12 21:00:00",
        "2013-02-13 17:00:00",
        "2013-02-13 17:05:00",
        "2013-02-13 17:50:00"
    ]
	}' \
  "localhost:8080/tax-congestion-calculator/Car"
```