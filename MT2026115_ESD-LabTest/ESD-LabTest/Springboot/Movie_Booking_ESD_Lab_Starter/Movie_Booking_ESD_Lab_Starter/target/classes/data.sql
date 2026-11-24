INSERT INTO movies (id, title, genre, language, duration, total_seats, available_seats) VALUES
(1, 'Interstellar', 'ACTION', 'ENGLISH', 169, 100, 80),
(2, 'Warrior', 'ACTION', 'HINDI', 150, 120, 90),
(3, 'The Intern', 'COMEDY', 'ENGLISH', 121, 80, 60),
(4, 'Inception', 'ACTION', 'ENGLISH', 148, 100, 75);

INSERT INTO customers (id, name, email) VALUES
(1, 'Arjun Kumar', 'arjun@example.com'),
(2, 'Meera Sharma', 'meera@example.com');

INSERT INTO bookings (id, movie_id, customer_id, booking_date, seats_booked, status) VALUES
(1, 1, 1, '2026-11-10 10:00:00', 2, 'CONFIRMED'),
(2, 2, 2, '2026-11-11 11:00:00', 1, 'CANCELLED');
