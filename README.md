# diff

## Requirements

- Make sure the PC you will be running the application on has the JDK 1.8 installed
- Make sure that the PC you will be running the application has internet access, so that when the application is run all of the dependencies can be downloaded properly
- If you open the app on an IDE (such as Eclipse or IntelliJ) install the lombok
plugin as the application uses this framework to remove boilerplate code. Further instructions can be found at:
    https://projectlombok.org/setup/eclipse
    https://projectlombok.org/setup/intellij

## Running the Application

To run the application open a command prompt, cd into the application root folder and type the following command:
    ```mvnw spring-boot:run```

The command will: compile the application, run the unit tests and start the application. After the command has finished
the last line on the command prompt should look like:

```2020-01-20 13:40:09.884  INFO 15392 --- [           main] com.diff.DiffApplication                 : Started DiffApplication in 3.966 seconds (JVM running for 4.552)```

## Running the Application Unit Tests

To run the application unit tests open a command prompt, cd into the application root folder and type the following command:
    ```mvnw test```
