# Spring Boot demo application

## Overview

This project is a Spring Boot demo application.

This web application allows you to manage handball tournaments. For now, it is a simple CRUD system. The backend is almost entirely developed but the frontend is under development. Admins can create and manage seasons, teams, matches. When a match is played, admins can inser the score.

First goal of this project is to show how to use Spring Boot together with Spring MVC, Spring Security and Spring Data JPA. Second goal is to show what are the framework and tools that we can use today to create light and powerful web application in Java.

**This project is still under heavy development!**

## Frameworks and libraries

This project is a full web application with the following technologies:

* [Spring Boot](http://projects.spring.io/spring-boot/)
* [Spring MVC](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html)
* [Spring Security](http://projects.spring.io/spring-security/)
* [Spring Data JPA](http://projects.spring.io/spring-data-jpa/)
* H2 - Database Engine (this app uses an embedded H2 database)
* Hibernate
* Thymeleaf - Template engine
* Twitter Bootstrap - HTML and CSS framework
* jQuery

## Build and Run

Using maven:
```sh
mvn clean package spring-boot:run
```

## Running the application (quickstart)

* Clone this git repo and import this project in IntelliJ IDEA.
* Access http://localhost:8080/handball/ in your browser.
* Currently there are two users: admin@example.com/password1 and user@example.com/password1

## Configuration

Configure application in application.properties in resources folder (in classpath). This file can be overridden with a copy and specific properties in current dir, config folder in classpath or config folder under current dir. It also supports Spring environment profiles.

## TODOs
* Frontend: dashboard for logged users, teams ranking table, match, comments...
* Implement email verication for new users
* Unit and integration tests
* Translate the application in english

## Licence

Copyright 2015 Guillaume MOREL-BAILLY

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this work except in compliance with the License.
You may obtain a copy of the License in the LICENSE file, or at:

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
