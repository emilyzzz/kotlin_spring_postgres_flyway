## Kotlin Spring Rest App
_(work in progress)_

Stack: Kotlin, Spring Framework, Postgres(docker), Flyway
Note, docker-compose.yml developed on Mac, may or may not work on Windows for volume mounting.

### Build & Run
Build will only pass when the app connects to a running postgres. Run `docker-compose up -d` to start postgres.
```
./gradlew build
./gradlew bootRun             # or use Intellij to run
```

### Aliases

```
source aliases.sh             # to use alias: psql, fly
```

### Flyway
Manage migration files in: src/main/resources/db/migration
```
docker-compose up -d          # flyway migrate, then exit
fly                           # flyway help page
fly info                      # show info
fly migrate                   # make migration, same effect as 'dc up -d'
fly validate
```

### Rest Endpoints

*Create Question*
```
curl -X POST "localhost:8080/questions" -H "content-type: application/json" -d '{"title": "title1", "description": "description1"}' | python -mjson.tool

# response
{'createdAt': '2019-03-29T02:40:00.572+0000',
 'description': 'description1',
 'id': 1002,
 'title': 'title1',
 'updatedAt': '2019-03-29T02:40:00.572+0000'}
```

*Get All Questions*
```
$ curl -X GET "localhost:8080/questions" | python -mjson.tool

# response
{
    "content": [
        {
            "createdAt": "2019-03-29T02:40:00.572+0000",
            "updatedAt": "2019-03-29T02:40:00.572+0000",
            "id": 1002,
            "title": "title1",
            "description": "description1"
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "offset": 0,
        "pageSize": 20,
        "pageNumber": 0,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 20,
    "number": 0,
    "first": true,
    "numberOfElements": 1,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "empty": false
}
```

More:
```
curl -X GET "localhost:8080/questions?page=0&size=2&sort=createdAt,desc" | python -mjson.tool
```

*Create Answer*
```
curl -X POST "localhost:8080/questions/1002/answers" -H "content-type: application/json" -d '{"text": "answer to question 1"}' | python -mjson.tool

# response
{
    "createdAt": "2019-03-29T02:46:09.017+0000",
    "updatedAt": "2019-03-29T02:46:09.017+0000",
    "id": 1000,
    "text": "answer to question 1"
}
```

*Get All Answers For a Question*
```
curl -X GET "localhost:8080/questions/1002/answers"  | python -mjson.tool

# response
[
    {
        "createdAt": "2019-03-29T02:46:09.017+0000",
        "updatedAt": "2019-03-29T02:46:09.017+0000",
        "id": 1000,
        "text": "answer to question 1"
    }
]
```