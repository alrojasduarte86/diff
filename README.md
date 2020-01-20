# diff

This application exposes three endpoints:

* PUT /v1/diff/{id}/left: Is used to set the left piece of data to be compared. The body of the request should be a JSON Base64 code text.<br/>

Sample Request<br/>

```
curl -X PUT -H "Content-Type: application/json" -d 'W3siaWQiOiI2Nzg5MCJ9LAp7ImlkIjoiMTIzNDUifSwKeyJpZCI6IjExMTEifSwKeyJpZCI6IjIyMjIifV0=' http://localhost:8080/v1/diff/12345/left
```

Sample Response<br/>
```json
{
    "id": "12345",
    "leftData": "[{\"id\":\"67890\"},\n{\"id\":\"12345\"},\n{\"id\":\"1111\"},\n{\"id\":\"2222\"}]"
}
```

* PUT /v1/diff/{id}/right:  Is used to set the right piece of data to be compared. The body of the request should be a JSON Base64 code text.<br/>

Sample Request<br/>

```
curl -X PUT -H "Content-Type: application/json" -d 'W3siaWQiOiI2Nzg5MCJ9LAp7ImlkIjoiMTIzNDUifSwKeyJpZCI6IjExMTEifSwKeyJpZCI6IjIyMjIifV0=' http://localhost:8080/v1/diff/12345/right
```

Sample Response<br/>
```json
{
    "id": "12345",
    "rightData": "[{\"id\":\"67890\"},\n{\"id\":\"12345\"},\n{\"id\":\"1111\"},\n{\"id\":\"2222\"}]"
}
```

* GET /v1/diff/{id}/: Is used to get the diffs between the left and right pieces of data that have {id} as their id

Sample Request<br/>

```
curl -X GET http://localhost:8080/v1/diff/12345/
```

Sample Response<br/>
```json
{
    "result": "NOT_EQUAL_SIZE",
    "diffs": [
        {
            "leftOffset": {
                "start": 1,
                "end": 2,
                "length": 1
            },
            "rightOffset": {
                "start": 1,
                "end": 2,
                "length": 1
            }
        },
        {
            "leftOffset": {
                "start": 3,
                "end": 4,
                "length": 1
            },
            "rightOffset": {
                "start": 3,
                "end": 4,
                "length": 1
            }
        }
    ]
}
```

## Requirements

- Make sure the PC you will be running the application on has the JDK 1.8 installed
- Make sure that the PC you will be running the application has internet access, so that when the application is run all of the dependencies can be downloaded properly
- If you open the app on an IDE (such as Eclipse or IntelliJ) install the lombok
plugin as the application uses this framework to remove boilerplate code. Further instructions can be found at:
    https://projectlombok.org/setup/eclipse
    https://projectlombok.org/setup/intellij

## Running the Application

To run the application open a command prompt, cd into the application root folder and type the following commands:
    ```mvnw clean install package```<br/>
    ```mvnw spring-boot:run```

The command will: compile the application, run the unit tests and start the application. After the command has finished
the last line on the command prompt should look like:

```2020-01-20 13:40:09.884  INFO 15392 --- [           main] com.diff.DiffApplication                 : Started DiffApplication in 3.966 seconds (JVM running for 4.552)```

## Running the Application Unit Tests

To run the application unit tests open a command prompt, cd into the application root folder and type the following command:
    ```mvnw test```
