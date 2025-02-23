
# Expense-tracker-api

💰 Expense Tracker REST API
A RESTful API built with Spring Boot to manage expense categories and transactions. This application allows users to create, read, update, and delete (CRUD) categories and transactions, making it easy to track and organize expenses.

Features
Manage expense categories (e.g., Food, Travel, Utilities).
Record and track transactions with details like amount, date, and category.
RESTful endpoints for seamless integration with front-end applications or other services.
Built with Spring Boot, leveraging Spring jdbctemplete for database interactions.
Configurable database (default: H2 in-memory, easily switchable to MySQL/PostgreSQL).



I have added JSON Web Token (JWT) authentication to secure endpoints, ensuring only logged-in users can access protected resources.


🚀 Setup and Installation
Follow these steps to get the API up and running on your machine.

1. Clone the Repository
bash
Wrap

git clone https://github.com/sunil1289/Expense-tracker-api.git

cd expense-tracker-api


2. Spin Up PostgreSQL Database
You have two options to set up the database:



Option 1: Local Installation
Download PostgreSQL from here and install it locally.



Option 2: Docker Container
Run a PostgreSQL container using Docker:


bash
Wrap

docker container run --name postgresdb -e POSTGRES_PASSWORD=admin -d -p 5432:5432 postgres



3. Create Database Objects
An SQL script (expensetracker_db.sql) is included in the root directory (expense-tracker-api) to create all necessary database objects.

If Using Docker:
Copy the script to the container:
bash
Wrap



docker container cp expensetracker_db.sql postgresdb:/

Access the container:
bash
Wrap



docker container exec -it postgresdb bash
Run the script using psql:
bash
Wrap



psql -U postgres --file expensetracker_db.sql
If Installed Locally:
Run the script directly in your PostgreSQL client (e.g., pgAdmin or psql).



4. (Optional) Update Database Configuration
If your database is hosted on a cloud platform or you’ve customized the username/password in the SQL script,
 update the src/main/resources/application.properties file:

properties
Wrap


spring.datasource.url=jdbc:postgresql://localhost:5432/expensetrackerdb
spring.datasource.username="your_databse_username"
spring.datasource.password="your_databse_password"


5. Run the Spring Boot Application
Start the application using Maven:

bash
Wrap
./mvnw spring-boot:run
The API will be available at http://localhost:8080.



🌟 Features
RESTful Endpoints: Manage expenses with ease.
JWT Authentication: Secure access to protected routes.
PostgreSQL: Reliable and scalable database storage.
JdbcTemplate: Simplified database operations.


📡 Accessing the API
Once the app is running, all endpoints are accessible starting from:

text
Wrap
http://localhost:8080


🛠️ Tech Stack
Spring Boot: Backend framework
PostgreSQL: Database
JdbcTemplate: Database interaction
JWT: Authentication
