# Сервис почтовых отправлений

## Инструкция:

1. Откройте Docker Desktop
2. Откройте консоль и перейдите в папку с проектом, где находиться файл docker-compose.yml
3. Пропишите эту команду: docker-compose up --build


После у вас должно запуститься 3 контейнера (база данных, интерфейс для бд, приложение)

Что бы перейти в swagger: http://localhost:8080/swagger-ui/index.html#/

Что бы перейти в pgAdmin(панель управления БД), перейдите по этой ссылке: http://localhost:5050/browser/

Все данные для входов находяться в .env
