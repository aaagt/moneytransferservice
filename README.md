# Сервис перевода денег

## Структура проекта

- [backend](./backend) - Бэкенд приложения
- [frontend](./frontend) - Фронтенд приложения
- [nginx](./nginx) - Настройки для Nginx
- [specifications](./specifications) - openapi спецификации

## Запуск

1. Соберите приложение бэкенда в папку:

```
backend/build/libs/backend-1.0-SNAPSHOT.jar
```

2. Запустите docker compose

```bash
docker compose up --build
```

3. Приложение будет доступно по адресу: http://localhost/card-transfer/
