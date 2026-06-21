# Dogs API

A Spring Boot REST API for managing dog records with support for **pagination**, **dynamic filtering**, and an **in-memory H2 database** for development and testing.

---

## Tech Stack

- Java 17
- Spring Boot 3.5+
- Spring Web
- Spring Data JPA
- Hibernate
- H2 Database (in-memory)
- Maven
- ~~Micronaut framework~~ (NOT USED)
- ~~mapstruct~~ (NOT USED)

---

## Features

- CRUD operations for Dogs
- Pagination and sorting support
- Dynamic filtering (name, breed, supplier)
- Soft delete support (`deleted` flag)
- Automatic sample data loading on startup
- H2 Console for debugging

---

## Database (H2 In-Memory)

This application uses an **in-memory H2 database**, which resets on every restart.

### H2 Console
http://localhost:8080/h2-console

### JDBC Configuration
jdbc:h2:mem:testdb
Username: sa
Password: (empty)


---

## Sample Data (Loaded on Startup)

On application startup, sample data is automatically inserted via `data.sql`.

### Example Records

| Name  | Breed            | Supplier        | Status       |
|------|------------------|-----------------|--------------|
| Rex   | German Shepherd  | Kent Kennels    | IN_SERVICE   |
| Max   | Labrador         | Alpha Kennels   | IN_TRAINING  |
| Bella | Beagle           | Kent Kennels    | RETIRED      |

### Startup Configuration

```properties
spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true
```
## Running the Application
### Build Project
```
mvn clean install
```
### Run Application
```
mvn spring-boot:run
```

Application runs at:
```
http://localhost:8080
```

### API Endpoints
#### 1. Add Dogs
```
POST http://localhost:8080/api/dogs/dogs
{
  "name": "RexII",
  "breed": "German Shepherd",
  "supplier": "Kent Kennels",
  "badgeId": "K9-100",
  "gender": "MALE",
  "birthDate": "2022-03-10",
  "dateAcquired": "2023-01-15",
  "currentStatus": "IN_SERVICE",
  "kennellingCharacteristic": "Prefers isolated kennel"
}
```
#### 2. Get All Dogs (Pagination)
```
   GET /api/dogs/dogs
```   
####   Example:

   http://localhost:8080/api/dogs/dogs?page=0&size=5&sort=name,asc


#### 3. Filter Dogs

Supported filters:
- name
- breed
- supplier

#### Examples:
##### Filter by name
http://localhost:8080/api/dogs/dogs?name=rex

##### Filter by breed
http://localhost:8080/api/dogs/dogs?breed=Labrador

##### Filter by supplier
http://localhost:8080/api/dogs/dogs?supplier=Kent+Kennels


#### 4. Combined Filtering + Pagination
http://localhost:8080/api/dogs/dogs?breed=Labrador&page=0&size=2&sort=name,desc


#### Filtering Logic
Filtering is implemented using Spring Data JPA Specifications, allowing dynamic query composition.

Each filter is optional:
- If a parameter is missing → it is ignored
- Multiple filters can be combined
- 
#### Example
```
Specification<Dog> spec =
hasName(name)
.and(hasBreed(breed))
.and(hasSupplier(supplier))
.and(notDeleted());
```
📄 Pagination Details

Spring Data provides built-in pagination support:

 - page → page index (0-based)
- size → number of records per page
- sort → sorting field and direction
#### Example
http://localhost:8080/api/dogs/dogs?page=0&size=2&sort=breed,asc


### Testing the API

You can test using:

Postman
cURL
Example cURL
curl "http://localhost:8080/api/dogs/dogs?page=0&size=2"

### Entity Overview

Main entity: **Dog**

#### Key Fields
- id
- name
- breed
- supplier
- badgeId
- gender
- birthDate
- dateAcquired
- currentStatus
- kennellingCharacteristic
- leavingDate
- leavingReason
- deleted

### Soft Delete
Dogs are NOT physically deleted from the database.

Instead:
deleted = true

All queries exclude deleted records using:
notDeleted()

### Notes
 - H2 database is volatile (data resets on restart)
 - Designed for development/testing purposes
 - Can be replaced with MySQL/PostgreSQL in production