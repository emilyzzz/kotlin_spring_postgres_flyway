## Kotlin Spring Project
Kotlin, Spring Framework, Postgres(docker), Flyway


### To Use Quick Aliases

```
source aliases.sh      # to use alias: psql, fly
```


#### Flyway
Manage migration files in: src/main/resources/db/migration
```
docker-compose up -d          # flyway migrate, then exit
fly                           # flyway help page
fly info                      # show info
fly migrate                   # make migration, same effect as 'dc up -d'
fly validate
```

