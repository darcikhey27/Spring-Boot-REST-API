# Spring-Boot-REST-API
Backend RESTful API with Java Spring Boot framework, using
MVC and Repository Patterns
## Java, Spring Boot, Maven, IntelJ

# Brief
Weather information will be stored in a MAP as JsonObject or in a MySQL db.
endpoints will return a `JsonObject` that can be used in the frontend to display weather info about a city
# API endpoints
## GET
`http://localhost:8080/get-city`

## POST
`http://localhost:8080/add{cityName}`

## UPDATE
`http://localhost:8080/update{cityName}`
`http://localhost:8080/update{cityId}`

## DELETE
`http://localhost:8080/delete{cityName}`
`http://localhost:8080/delete{cityId}`


#How to run:
insert how to launch application