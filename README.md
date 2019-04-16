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

*Create Teacher*
```
curl -s -X POST "localhost:8080/teachers" -H "content-type: application/json" -d '{"name": "name1", "description": "description1"}' | python -mjson.tool

# response
{'createdAt': '2019-03-29T02:40:00.572+0000',
 'description': 'description1',
 'id': 1002,
 'name': 'name1',
 'updatedAt': '2019-03-29T02:40:00.572+0000'}
```

*Get All Teachers*
```
curl -s -X GET "localhost:8080/teachers" | python -mjson.tool

# response
{
    "content": [
        {
            "createdAt": "2019-03-29T02:40:00.572+0000",
            "updatedAt": "2019-03-29T02:40:00.572+0000",
            "id": 1002,
            "name": "name1",
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

*Query Params*
```
curl -X GET "localhost:8080/teachers?page=0&size=2&sort=createdAt,desc" | python -mjson.tool
```

*Create Student*
```
curl -X POST "localhost:8080/teachers/1000/students" -H "content-type: application/json" -d '{"name": "student1", "description": "student1 to teacher 1"}' | python -mjson.tool

# response
{
    "createdAt": "2019-03-29T02:46:09.017+0000",
    "updatedAt": "2019-03-29T02:46:09.017+0000",
    "id": 1000,
    "name": "student1",
    "description": "student1 to teacher 1"
}
```

*Get All Students For a Teacher*
```
curl -X GET "localhost:8080/teachers/1000/students"  | python -mjson.tool

# response
[
    {
        "createdAt": "2019-03-29T02:46:09.017+0000",
        "updatedAt": "2019-03-29T02:46:09.017+0000",
        "id": 1000,
        "text": "student1 to question 1"
    }
]
```

*Update A Teacher*
```
curl -s -X PUT "localhost:8080/teachers/1000" -H "content-type: application/json" -d '{"name": "updated teacher1 name"}' | python -mjson.tool

# response, note we didn't include 'description' in payload so it's set to default ""
{
    "createdAt": "2019-03-30T03:45:51.984+0000",
    "updatedAt": "2019-03-30T03:55:26.762+0000",
    "id": 1000,
    "name": "updated teacher1 name",
    "description": ""
}
```