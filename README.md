# Spring-Boot-REST-API
Backend RESTful API with Java Spring Boot framework, using
MVC and Repository patterns
#Requirements
* Java
* Spring Boot framework
* intellJ
* Maven

# Description
App will serve weather information about a city. Data will be stored in a MAP as JsonObject or in a MySQL db.
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


# Frontend:
Spring MVC with Thymeleaf for templating  
jQuery for AJAX

#How to run:
insert how to launch application