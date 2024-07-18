# Content Management System (CMS)

## Описание

Простая система управления контентом с двумя сущностями: User и Post.

## Зависимости

- Java 11+
- Maven
- PostgreSQL
- Spring Boot
- Spring Data JPA

## Установка и запуск

1. Клонируйте репозиторий:
   ```sh
   git clone https://github.com/VladislavKorotkov/cms_api.git
2. Перейдите в директорию проекта:
    ```sh
    cd cms_api
3. Создайте базу данных PostgreSQL и настройте application.properties.
4. Соберите и запустите приложение:
   ```sh
   mvn clean install
   mvn spring-boot:run
