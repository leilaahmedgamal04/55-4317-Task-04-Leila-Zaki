# Task 04 — Micro-communications (CourseHub mini)

A three-service mini-platform that practises the two core patterns from Lab 8:
**OpenFeign** for one synchronous service-to-service call, and **RabbitMQ** for
one asynchronous publish / subscribe.

## Stack
- JDK 25, Spring Boot 4.0.3, Spring Cloud 2025.1.1
- PostgreSQL 17, RabbitMQ 4.2-management
- Maven multi-module

## Modules
| Module | Port | Status |
|---|---|---|
| `course-service` | 8081 | Fully implemented — exposes the Feign endpoints |
| `enrollment-service` | 8082 | Partial — you add the Feign client and the publisher |
| `notification-service` | 8083 | Partial — you add the listener |

## Quick start
```bash
docker compose up -d postgres rabbitmq
mvn clean install -DskipTests
# then start services from the IDE, or:
docker compose up --build
```

RabbitMQ UI: http://localhost:15672 (guest / guest).

See `Task4.tex` for the full task spec.
