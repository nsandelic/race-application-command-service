# Race Application Command Service

This repository contains the backend services for our application, consisting of the Command and Query services.
This service used for managing Races and Applications, it's also used as STOMP event sender. 
Upon updating race or application it sends event about it
### NOTE
Application is currently setup for local deployment

## Prerequisites

- **Java 17** (or later)
- **PostgreSQL** (for database)

## Key Features

- CREATE, UPDATE, DELETE endpoints for managing Races and Applications
- Role based auth
- Login endpoint
- STOMP event sender

## Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/nsandelic/race-application-command-service.git
```

### 2. Set java version
In File->Project structure set Java 17 version

### 3. Create postgres database
Crate instance of postgres database.
Properties are located in application.properties file.
Use queries to insert initial users. File located in 
src/main/resources/user-insert.sql

### 4. Run
Run application and database schema will be created.
Use postman or client app to login and use endpoints.