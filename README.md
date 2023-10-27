# Разработка корпоративных информационных систем. Практическая работа 4
Варинат 6: Холодильник.

## Установка PostgreSQL
1. Установка PostgerSQL:


   sudo apt-get update


   sudo apt-get install postgresql postgresql-contrib

3. Создание БД:


   sudo -i -u postgres


   psql


   CREATE DATABASE postgres WITH ENCODING 'UTF8';

5. Создание пользователя:


   CREATE USER yersh WITH ENCRYPTED PASSWORD 'hyunjin34';


   GRANT ALL PRIVILEGES ON DATABASE postgres TO yersh;

7. Инициализация БД:


   psql -U postgres -d postgres -f init.sql

## Инструкция по сборке и запуску
1. Сборка проекта: mvn pakage
2. Запуск из папки target: java -jar .\second-1.0-SNAPSHOT.jar
