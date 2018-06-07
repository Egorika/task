# task
## Запуск
1. Создать БД task (конфиг в resources/application.properties. default - root:12345678)
2. ```mvn install```
3. Запуск DemoApplication.java.
4. Добавить роли в таблицу Role (2 строчки из insert_roles (src/main/resources/static/db/insert_roles.sql)).
6. Адрес localhost:8080. Чтобы сделать админа, надо у зарегистрированного пользователя изменить в таблице user столбец active на 1 и в таблице user_role столбец role_id на 1.
