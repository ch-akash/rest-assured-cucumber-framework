# RestAssured Cucumber Framework

![Java](https://img.shields.io/badge/Java-21-blue.svg?style=for-the-badge&logo=openjdk&logoColor=black&labelColor=ED8B00)
![Maven](https://img.shields.io/badge/Maven-3.9.5-blue?logo=apachemaven&logoColor=black&labelColor=C71A36&style=for-the-badge)
![TestNG](https://img.shields.io/badge/TestNG-7.10.2-blue?logo=testng&labelColor=CD6532&style=for-the-badge)
![Static Badge](https://img.shields.io/badge/cucumber-7.18.0-blue?logo=cucumber&style=for-the-badge&logoColor=cyan)
![RestAssured](https://img.shields.io/badge/RestAssured-5.4.0-blue?labelColor=00A86B&style=for-the-badge)
![Allure](https://img.shields.io/badge/Allure-2.24.0-blue?labelColor=FF6400&style=for-the-badge)

## Table of Contents

* [Getting Started](#getting-started)
    * [Prerequisites](#prerequisites)
    * [Install dependencies](#install-dependencies)
* [Project Structure](#project-structure)
* [Features](#features)
* [Test Isolation](#test-isolation)
* [Dependency Injection](#dependency-injection)
* [Running Tests](#running-tests)
* [Viewing Reports](#viewing-reports)
* [Cucumber Command Line Options](#cucumber-command-line-options)
* [Contributing](#contributing)

This is a simple framework to demonstrate how to use RestAssured with Cucumber and TestNG. We will take full advantage
of the Cucumber BDD framework to write our test scenarios in Gherkin language.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing
purposes.

### Prerequisites

You need to have the following software installed on your machine:

- [Java 21](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- Allure
    - [Allure CLI](https://allurereport.org/docs/install/)
    - [Allure Cucumber Plugin](https://allurereport.org/docs/cucumberjvm/)

### Install dependencies

    mvn install -DskipTests

## Project Structure

The project follows a standard Maven directory structure:

- `src/main/java`: Contains the main Java source code.
- `src/main/resources`: Contains the main resources such as property files.
- `src/test/java`: Contains the test Java source code.
- `src/test/java/stepDefs`: Contains the step definitions for the Cucumber scenarios.
- `src/test/resources/features`: Contains the Cucumber feature files.

## Features

- **Cucumber**: Cucumber is used for writing BDD test scenarios in Gherkin language.
- **RestAssured**: RestAssured is used for making HTTP requests and validating API responses.
- **Maven**: Maven is used as the build and dependency management tool, making it easy to manage project dependencies.
- **Allure**: Allure is used for test reporting, providing detailed reports with rich visualization.
- **TestNG**: TestNG is used as the runner for the Cucumber tests.
- **PicoContainer**: PicoContainer is used for dependency injection in the step definitions. This allows us to share
  state between steps which are in the same scenario but in different step definition classes.

## Test Isolation

The tests are isolated from each other, meaning that each test is independent and does not rely on the state of the
previous test. This is by default in Cucumber, as each scenario is run in a separate instance of the step definition
class.

## Dependency Injection

PicoContainer is used for dependency injection in the step definitions. This allows us to share state between steps
which are in the same scenario but in different step definition classes. We have used constructor injection to inject
the dependencies. A class `SharedContext` is used to store the shared state which is passed between the step definitions
with a parameterised constructor.

## Running Tests

Run the Cucumber Test Runner Class directly:

mvn test -Dtest=CucumberTests

Run Cucumber tests using TestNG XML file:

    mvn test -DsuiteXmlFile=cuke.xml

## Viewing Reports

After running the tests, you can view the Allure report by running the following command:

    allure serve

## Cucumber Command Line Options

We can pass CucumberOptions to the Cucumber Test Runner Class to control the behavior of the Cucumber tests. Here are
some of the common options:

- `cucumber.filter.tags`: Run scenarios with tags that match the expression. Example:

        mvn test -Dcucumber.filter.tags="@smoke and not @slow"

- `cucumber.filter.name`: Run scenarios with names that match the expression. Example:

        mvn test -Dcucumber.filter.name=".*CRUD Test.*"

- `cucumber.execution.dry-run`: Skip execution of glue code. Example:

         mvn test -Dcucumber.execution.dry-run=true

- `cucumber.features`: Run multiple feature files with comma separated path. Example:

        mvn test -Dcucumber.features=src/test/resources/features/feature1.feature, src/test/resources/features/feature2.feature

Some more options are listed here:

```properties
cucumber.ansi-colors.disabled=# true or false. default: false
cucumber.execution.limit=# number of scenarios to execute (CLI only).
cucumber.execution.order=# lexical, reverse, random or random:[seed] (CLI only). default: lexical
cucumber.execution.wip=# true or false. default: false.
cucumber.glue=# comma separated package names. example: com.example.glue
cucumber.plugin=# comma separated plugin strings. example: pretty, json:path/to/report.json
cucumber.object-factory=# object factory class name. example: com.example.MyObjectFactory
cucumber.snippet-type=# underscore or camelcase. default: underscore
```

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvement, please open an issue or submit a
pull request.
