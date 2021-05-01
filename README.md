## Presentations

## Purpose
I use this repo as place to store various presentations about Java
best practices and tips.

## Structure
The `test` directory hosts runnable the code samples used for the presentation.

Current presentations are:
- Best Practices for Optionals in Java
- Functional Programing in Java
- Why, When, and How to Refactor
  - Duplicated code
  - Long Method
  - Long Parameter
  - Switch Statements
  - Flag Conditionals
- Java 11 new features
- Using the Grouping collector in Java 8
- Effective Java
    - Static Factory Methods
    - Try-with-resources

## Pre-requisites
- Java 11
- Maven 3+

## Setting up
1. Clone the repository
2. Run `mvn clean compile` to get the build the project

N.B. Some tests purposely fail for some presentations. 
Because of this running `mvn clean test` or `mvn clean package` will fail.