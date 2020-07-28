# Hello Micronaut

Implementation of Quarkus Tutorial [https://redhat-developer-demos.github.io/quarkus-tutorial/quarkus-tutorial/index.html](https://redhat-developer-demos.github.io/quarkus-tutorial/quarkus-tutorial/index.html) with Micronaut 2.0

- Basics & Configuration
- Hibernate
- Rest Client
- Fault Tolerance
- Health Check
- Reactive
- Monitoring
- Security with JWT RBAC (using Keycloak as Authorization Server)

## Getting Started ##


### Keycloak Setup ###

Keycloak runs with Docker on http://localhost:8082 you can use admin/admin to access to the console.

To run

`cd infrastructure
docker-compose up -d`

On startup this command will import `keycloak/realm-export.json` this will create a realm **micronaut** with one client configured as service account :
- client_id : hello-mn
- client-secret : 43246458-4d3d-4e6a-a15a-1063cb3c4452


### Start the application ###

To start the application

    mvn mn:run
