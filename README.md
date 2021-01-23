REST API, которое взаимодействует с БД (Hibernate). [![Build Status](https://travis-ci.com/Borlok/RestCrudWithHerokuFlywayHibernate.svg?branch=master)](https://travis-ci.com/Borlok/RestCrudWithHerokuFlywayHibernate)

Сущности:

Customer

Specialty

Account

AccountStatus (enum ACTIVE, BANNED, DELETED)

Customer-> Set<Specialty> specialties+ Account account

Account -> AccountStatus

Требования:

Все CRUD операции для каждой из сущностей.

Придерживаться подхода MVC

Для сборки проекта использовать Maven

Для взаимодействия с БД - Hibernate (конфигурация - аннотации)

Инициализация БД должна быть реализована с помощью flyway

Взаимодействие с пользователем необходимо реализовать с помощью Postman (https://www.getpostman.com/)

Репозиторий должен иметь бейдж сборки travis(https://travis-ci.com/)

Рабочее приложение должно быть развернуто на heroku.com

Технологии: Java, PostgreSQL, Hibernate, HTTP, Servlets, Maven, Flyway.
