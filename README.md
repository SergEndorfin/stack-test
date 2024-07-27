# Stack API

## Overview

This project is a Spring Boot 3 application using Java 17, H2 in-memory database, and various Spring components like
Spring Data JPA, Spring Validation, and more. The application includes unit tests with JUnit 5 and Mockito.

## Requirements

- method1: <br>
  POST /push <br>
  json body: {"value":"value1"}
- method2: <br>
  GET /pop <br>
  returns json {"value": "value1"}
  <br>
  <br>
- build with maven or gradle
- implement unit tests
- swagger documentation
- readme document with instructions on build and run
- optionally: arrange storage in local H2 database

## Technologies Used

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **Spring Validation**
- **Springdoc OpenApi**
- **Spring Test**
- **JUnit 5**
- **Mockito**
- **H2 Database**
- **Maven**

## Getting Started

### Prerequisites

- Java 17
- Maven 3.6+
- Git

### Installation

1. **Clone the repository:**

```bash
git clone https://github.com/SergEndorfin/stack-test.git

cd stack-test
```

2. **Build the project:**

```bash
mvn clean install
```

3. **Run the application:**

```bash
mvn spring-boot:run
```

### Running Tests

To run the tests, use the following command:

```bash
mvn test
```

## API Endpoints

### Stack API

- URL: `/push`
- Method: `POST`
- Content-Type: `application/json`
- Request Body Example:

```json
{
  "value": "sample data"
}
```

- Responses:
    - 201 Created - Data added successfully.
    - 400 Bad Request - Invalid input data.
    - 500 Internal Server Error - Internal server error occurred.

### Pop Data

- URL: `/pop`
- Method: `GET`
- Responses:
    - 200 OK - Data popped successfully.
    - 404 Not Found - Data not found.
    - 500 Internal Server Error - Internal server error occurred.

## Configuration

The application is configured to run on http://localhost:8080. You can change the server port and other settings in the
`application.yml` file.

## H2 Database

The application uses an H2 in-memory database for development and testing purposes. The database console is available
at:

http://localhost:8080/h2-console.

JDBC URL: jdbc:h2:mem:testdb <br>
Username: sa <br>
Password: (leave empty)

## OpenAPI Documentation

The API documentation is available via OpenAPI/Swagger. When application is running access it at:

http://localhost:8080/swagger-ui/index.html

## Example Data

Here are some examples of request and response bodies for reference.

### Request Body Example for /push

```json
{
  "value": "sample data"
}
```

### Error Response Example

```json
{
  "apiPath": "/push",
  "errorCode": "BAD_REQUEST",
  "errorMessage": "{value=The length of the Value should be between 3 and 30}",
  "errorTime": "2024-07-26T18:53:06.597Z"
}
```

## Development

### Adding a New Dependency

To add a new dependency, modify the pom.xml file:

```xml
<dependency>
    <groupId>group-id</groupId>
    <artifactId>artifact-id</artifactId>
    <version>version</version>
</dependency>
```

### Updating the Application

After making changes, ensure the project builds correctly:

```bash
mvn clean install
```

## Contribution Guidelines

1. Fork the repository.
2. Create a new branch (git checkout -b feature-branch).
3. Commit your changes (git commit -am 'Add new feature').
4. Push to the branch (git push origin feature-branch).
5. Create a new Pull Request.

## License

This project is not licensed and open source.

## Contact

For any inquiries or support, please contact:

- **Name:** Sergii Kononenko
- **Email:** [sergendorfin@gmail.com](mailto:sergendorfin@gmail.com)

---
Happy coding!
