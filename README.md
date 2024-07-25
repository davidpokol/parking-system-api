# 🚗 Parking System API

This REST API designed for managing vehicles, parking garages, and parking records.

## Features

- Vehicle creation, modification, deletion
- Parking Garage creation, modification, deletion
- Parking Record creation
- Get all Vehicles
- Get a Vehicle by ID
- Get all Parking Garages
- Get a Parking Garage by ID
- Get all Parking Records
- Get a Parking Record by ID

## API Documentation

You can check out the documentation [here](DOCUMENTATION.md).

**OR**

You can open it through **Swagger** as well.

Once the application is running, navigate to `http://localhost:9000/v1/api-docs` to access the Swagger UI.

## Requirements

## Getting Started

### Prerequisites
- Java JDK 21
- Maven (optional)

### Run Locally

Clone the project

```bash
  git clone https://github.com/davidpokol/parking-system-api.git parking-garage-api
```

Navigate to the project directory

```bash
  cd parking-garage-api
```

Set up [Environment Variables](#environment-variables)

Install dependencies

```bash
  ./mvnw install
```

Start the server

```bash
  ./mvnw spring-boot:run -pl web
```

### Environment Variables

To run this project, you will need to add the following environment variables to your *.env* file. This project uses
**PostgreSQL**, so create a database if you don't have one yet.

`DB_URL`

`DB_USER`

`DB_PASSWORD`

There is also an `example.env` file in the project directory, to help you set up the environment variables.
