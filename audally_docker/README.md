# Keycloak and PostgreSQL Docker Compose

A universally applicable example for using Keycloak and PostgreSQL with Docker Compose.

## Keycloak and PostgreSQL
              
The `docker-compose.yml` starts a PostgreSQL, creates a volume for it, and starts Keycloak connected to the PostgreSQL instance.

It also runs the `docker_pg_init.sql` script on start, which creates a `keycloak` schema in which Keycloak stores the data.

Run it with the following command:

```bash
docker-compose up -d
```

* **-d (--detach):**  asks Docker to run this container in the background.

---

To stop it, if you have already an instance running, run:

```bash
docker-compose down
```

A handy command to delete all existing volumes:

```bash
docker volume rm $(docker volume ls -q)
```
