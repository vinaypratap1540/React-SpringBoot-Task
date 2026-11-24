# ESD - Movie Booking System

## Project

The project is an existing Movie Booking System with Movies, Customers and Bookings. The existing application already contains the required entities, repositories, services, controllers, response DTOs, exception handling, seeded H2 data and existing APIs.

The existing Movie and Customer APIs include response DTO examples. Use the existing classes and project structure as references when creating the request DTO for Task 1. Complete the following tasks without changing the existing project structure.

## Task 1 — API Development: Create a Booking

The request DTO, POST controller method and corresponding service method are **not provided**. Create all three yourself.

Requirements:
- Create a request DTO containing `movieId`, `customerId` and `seatsBooked`.
- Apply request validation for required input.
- Create a `POST /api/bookings` endpoint.
- Call the service layer from the controller.
- Verify that the requested movie exists.
- Verify that the requested customer exists.
- Create the booking with status `CONFIRMED`.
- Save the booking and return the created booking.
- Use the existing exception handling.
- A successful response may use HTTP `200` or `201`.

## Task 2 — Debugging: Booking Cancellation

The existing booking cancellation API contains a bug.

An already cancelled booking must not be cancelled again.

Find and fix the bug so that the API follows this business rule.

## Task 3 — Debugging: Movie Search

The movie search API must apply both the requested genre and language filters.

Identify and fix the existing bug so that the API returns the correct search results.

## Running

Run the tests:

```bash
./mvnw test
```

Run the application:

```bash
./mvnw spring-boot:run
```

Swagger:

`http://localhost:8080/swagger-ui/index.html`

H2 Console:

`http://localhost:8080/h2-console`
