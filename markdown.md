Consuming the api
======

## Register subscribers
URL :  /subscribers

Method : post

Body

```json
{
    "name": "Jane Doe",
    "identificationNumber": "54321"
}
```
Responses
  1. If a new user
```text
    {
        Subscriber:54321 saved
    }
```

  2.. If user is already registered 
```text
    {
        Subscriber:5233 already registered.Cannot register again
    
    }
```
## View categories

URL :  /categories

Method : get

 