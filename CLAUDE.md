# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Test Commands

```bash
# Build all modules
mvn clean install

# Run all tests
mvn test

# Run a single test class
mvn test -Dtest=ProductAppServiceTest -pl ddd-module-demo/ddd-bootstrap

# Run the demo application
cd ddd-module-demo/ddd-bootstrap && mvn spring-boot:run
```

The demo app starts on port 8080. Tests live in `ddd-module-demo/ddd-bootstrap/src/test/`.

## Architecture Overview

This is a **DDD framework** plus a **demo application** showing how to use it.

### Framework Modules (the library itself)

| Module | Purpose |
|--------|---------|
| `ddd-common` | Base classes: `Entity`, `ValueObject`, `BaseCommand`, `BaseResult` |
| `ddd-core` | `CommandBus`, `EventBus`, handler/validator/interceptor contracts |
| `ddd-spring` | Spring `FactoryBean` wiring for the bus components |
| `ddd-spring-boot-autoconfigure` | Auto-configures `CommandBus` and `EventBus` beans |
| `ddd-spring-boot-starter` | Starter dependency for consumers |

### Demo Application (`ddd-module-demo`)

Four-layer clean architecture with strict dependency direction: `adapter → application → domain ← infrastructure`

```
ddd-adapter/        REST controllers, Dubbo services, MQ consumers, scheduled tasks
ddd-application/    App services, CommandHandlers, validators, interceptors, event handlers
ddd-domain/         Entities, Value Objects, Domain Services, Repository interfaces
ddd-infrastructure/ Repository implementations, converters, persistence (MyBatis), ACL adapters
ddd-client/         Public API: commands, DTOs, result types (consumed by external callers)
ddd-bootstrap/      Spring Boot entry point, integration tests
```

## Key Patterns

**Command flow**: `Controller → AppService → CommandBus → CommandValidator → CommandInterceptor(s) → CommandHandler → Domain → Repository`

- Each command (`CreateProductCommand`, etc.) lives in `ddd-client` and maps 1:1 to a `CommandHandler` in `ddd-application`
- Validators run before handlers; interceptors wrap handlers (ordered via `@Order`)
- Handlers publish domain events via `EventBus` after persisting

**Domain objects**:
- Entities extend `Entity` / `IdentityEntity` / `ConcurrencySafeEntity`; aggregate roots hold business logic (activate, deactivate, update)
- Value Objects extend `ValueObject` (e.g., `Money`, `ProductId`, `OrderId`)
- Repository interfaces are defined in `ddd-domain`; implementations live in `ddd-infrastructure` and convert between domain objects and data objects (`ProductDO` ↔ `Product` via `ProductConverter`)

**Bounded contexts**: Product (aggregate root: `Product`) and Order (aggregate root: `Order`, child: `OrderItem`)

**Concurrency**: Optimistic locking via `ConcurrencyConflicts` utility; raises `ConcurrencyConflictException` on version mismatch.

## Tech Stack

- Java 8, Spring Boot 2.0, MyBatis, Hibernate Validator, Lombok, Guava, FastJSON
- JUnit 4 + Mockito + `@SpringBootTest` for integration tests
