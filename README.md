# UserRegistration

### Предоставлено 2 эндпоинта:

#### * POST: /user/register - регистрация пользователя
  Пример тела запроса:
```
{
    "login": "login",
    "password": "password",
    "lastName": "Surname",
    "firstName": "Name",
    "email": "name@gmail.com"
}
```
#### * GET: /user/get/{id} - получить пользователя по id
Возвращает объект:
```
{
    "login": "login",
    "password": "password",
    "lastName": "Surname",
    "firstName": "Name",
    "email": "name@gmail.com",
    "registrationDate": "2021-08-21",
    "id": "fd100e48-28d9-44f9-8161-c087a2f1d979"
}
```
