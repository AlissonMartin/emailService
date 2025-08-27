# Email Service

A service for sending emails and managing periodic subscriptions. Built with Clean Architecture, allowing easy replacement of email providers (currently AWS SES and Gmail are supported).

## Features

- **Email Sending**: Trigger emails to subscribers or specific recipients.
- **Subscription with Periodic Emails**: Users can subscribe to topics and receive emails at scheduled intervals.

## Project Structure

The project is organized following Clean Architecture principles:

- **core/**: Contains business logic, entities, use cases, gateways (interfaces), and DTOs.
  - `entities/`: Domain models such as Email, Subscriber, Topic, etc.
  - `usecases/`: Application use cases (e.g., send email, create subscriber).
  - `gateway/`: Interfaces for external dependencies (e.g., email providers).
  - `dto/`: Data transfer objects for input/output between layers.
  - `enums/`, `exception/`: Enumerations and custom exceptions.
- **infrastructure/**: Implements gateways, persistence, schedulers, and presentation logic.
  - `gateway/`: Concrete implementations for AWS SES, Gmail, etc.
  - `persistence/`: Database access and repositories.
  - `scheduler/`: Periodic email scheduling logic.
  - `presentation/`: Controllers and API endpoints.
  - `mapper/`, `dto/`: Data mapping and infrastructure-specific DTOs.
- **resources/**: Configuration files and database migrations.
  - `application.yml`: Main configuration file for providers and database.
  - `db/migration/`: Flyway migrations for database schema (topics, subscribers, emails, queue).

## How It Works

- **Email Sending**: Use cases in `core/usecases/` handle the logic for sending emails, delegating to provider implementations in `infrastructure/gateway/`.
- **Subscribe & Periodic Emails**: Users subscribe to topics; the scheduler in `infrastructure/scheduler/` triggers periodic emails based on configured intervals.
- **Provider Agnostic**: To add a new provider, implement the gateway interface in `core/gateway/` and register it in the configuration.

## Getting Started

### Prerequisites
- Java 17+
- Maven
- Docker (optional, for containerized execution)

### Running Locally

1. Clone the repository:
   ```bash
   git clone <your-repo-url>
   cd emailService
   ```
2. Configure your email provider credentials in `src/main/resources/application.yml`.
3. Build the project:
   ```bash
   ./mvnw clean install
   ```
4. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
5. (Optional) Run with Docker:
   ```bash
   docker build -t email-service .
   docker-compose up
   ```

### Database Migrations

Flyway is used for database migrations. Migration scripts are located in `src/main/resources/db/migration/` and are applied automatically on startup.

## How to Contribute

1. Fork the repository.
2. Create your feature branch (`git checkout -b feature/my-feature`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature/my-feature`).
5. Open a Pull Request.

Please follow the code style and add tests for new features or bug fixes.

## Email Provider Configuration

The service is designed to be provider-agnostic. By default, it uses AWS SES and Gmail, but you can implement other providers by creating a new gateway in the infrastructure layer and updating the configuration.
