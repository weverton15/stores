# Project to Crossjoin company. This project has Spring Boot with PostgreSQL and Docker Compose Example

## ENDPOINTS THIS SPRING BOOT APP
- All rest methods (POST,DELETE,PUT AND GET(Get all and Get specific)) to 3 tables:
  - Stores
  - Products
  - StoreProduct (Link store and products because do not repeat theirs)

# STEPS TO EXECUTE API 

## BUILD the application
mvn clean install -dSkipTests pom.xml

## BUILD AND UP Docker Compose
docker-compose up --build   
docker-compose down <- down docker compose

## CURLS TO MAINLY ENDPOINS

### GET /store/product
curl --location --request GET 'http://localhost:8080/store/product' --header 'Content-Type: application/json'

### GET /stores
curl --location --request GET 'http://localhost:8080/stores' --header 'Content-Type: application/json'