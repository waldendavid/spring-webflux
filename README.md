
# Requirements
* JDK 11
* Maven
# Running the application
There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the de.codecentric.springbootsample.Application class from your IDE.
# Technical functionality
Spring Weblux Api is a simple app, functionality:
* downloading post from the [JSONPlaceholder](https://jsonplaceholder.typicode.com/)(Free fake API for testing and prototyping).
* saving each post in a separate JSON file. File name format: <post_id>.json.

App has junit tests (also parametrized with values and source)

Api has only one endpoint: http://localhost:8080/posts for checking jsons locally. Api was an introduction to reactive programming.
