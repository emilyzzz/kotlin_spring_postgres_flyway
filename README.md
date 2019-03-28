## Kotlin Spring Project
Kotlin, Spring Framework, Postgres(docker), Flyway

### Build
Build will only pass when the app connects to a running postgres. Run `docker-compose up -d` to start postgres.
```
./gradlew build
```

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

