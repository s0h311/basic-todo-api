### **Welcome to bfour**

### Bfour is an **open source** and **RESTful** Backend for managing todos and the users associated with them.

>By Merle Pantwich, Thuy Anh Nguyen, Farham Moazipor-Tehrani, Soheil Nazari
---
<br/>

**Example Usage**

*Create a user* -> `POST` request to `/user` with the following body:

```Json
{
    "fistName": "John",
    "lastName": "Wick",
    "email": "john@wick.de",
    "password": "boogeyman"
}
```

*Get all users* -> `GET` request to `/user`

*Delete user with the id of `1234`* -> `DELETE` request to `/user/1234`

*Create a todo* -> `POST` request to `/todo` with the following body:

```Json
{
    "title": "Survive!",
    "description": "Excommunicado",
    "userId": 1234,
}
```
