

alias dc="docker-compose"
alias dlogs_db="docker logs -f kotlinspring_pg_1"
alias dlogs_fly="docker logs -f kotlinspring_flyway_1"



alias psql="docker exec -it kotlinspring_pg_1 psql -U postgres app"
alias fly="docker run --rm --network kotlinspring_default boxfuse/flyway:5.2.4 -url=jdbc:postgresql://kotlinspring_pg_1:5432/app -user=postgres -password=postgres"