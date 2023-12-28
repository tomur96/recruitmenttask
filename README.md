# Recruitment Task - Empik

## Project Description
This REST API service allows to fetch data from Github API about users.
It also counts how many times http requests were executed for particular Github accounts.

## Tech Stack:
#### Java 17
#### Spring Boot
#### Redis

## Steps to set up:
1. Clone the application 
```bash
git clone https://github.com/coma123/Spring-Boot-Blog-REST-API.git
```
1. Open Docker on you local machine
2. Open terminal with the project's path and type:
```bash
    cd configuration
```
then 
```bash
    docker-compose up
```
This will allow to set the Redis container
3. Run the application (notice the beautiful "Empik" banner while starting :) )
4. Open Postman (or other Http client) and call endpoints described below

### Users

| Method | Url                                                    | Description                                  |
| ------ |--------------------------------------------------------|----------------------------------------------|
| GET    | http://localhost:8080/api/v1/users/{username}          | Get Github account details                   |
| GET    | http://localhost:8080/api/v1/users/dbstatus/{username} | Get Database status for a particular account |

The endpoint for DB access purpose is for avoiding installing a Redis manager.