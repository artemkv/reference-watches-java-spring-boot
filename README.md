# Java Spring Boot API Reference Application

This project is intended as a reference for building Java Spring Boot APIs.

## Build and run the project

### Running in Docker

```
# update images
docker pull artemkv/ref-watches-java:latest

# run image
docker run -d -p 8500:8500 artemkv/ref-watches-java:latest
```

## Configuration properties

application.properties
```
server.address - the address on which the service will be available (0.0.0.0)
server.port - the port on which the service will be available (8080)
spring.datasource.url - the connection string to connect to H2 database(jdbc:h2:mem:watchesdb;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE)
logging.path - the path to the log file (logs)
```

watchesapi.properties
```
watchesapi.page_size_limit - limit on the page size when retrieving collections(1000)
```

## Environment variables:

Note: Values of environment variables override the configuration parameters.

```
SERVER_ADDRESS - the address on which the service will be available (0.0.0.0)
SERVER_PORT - the port on which the service will be available (8080)
DB_CONNECTION_STRING - the connection string to connect to H2 database(jdbc:h2:mem:watchesdb;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE)
LOGGING_PATH - the path to the log file (logs)

MARVEL_API_PAGE_SIZE_LIMIT - limit on the page size when retrieving collections(1000)
```
