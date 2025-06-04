# CryptoManager
A simple Spring Boot application for managing your cryptocurrency portfolio.

## Features
Add new cryptocurrencies

Get all stored cryptocurrencies

Get details of a specific cryptocurrency by its ID

Update existing crypto

Calculate total portfolio value

## REST API
GET /cryptos
Get all cryptocurrencies

GET /cryptos/{id}
Get a specific cryptocurrency by ID

POST /cryptos
Add a new cryptocurrency

PUT /cryptos/{id}
Update an existing cryptocurrency

GET /portfolio-value
Get total portfolio value

## Stack
Java 21 – Programming language

Spring Boot – Backend framework

Lombok – Reduces boilerplate code

Maven – Build and dependency management

JUnit 5 & Mockito – Unit testing framework

## Getting Started
Clone the repository

Build the project using Maven

Run the application using your IDE or the command mvn spring-boot:run

Test endpoints using Postman, Curl, or browser