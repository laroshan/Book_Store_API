# Book Store API

The Book Store API is a RESTful service for managing book-related data, including books, authors, customers, orders, and carts. Built with Java and JAX-RS, it supports CRUD operations for each entity.

## Setup and Installation

To run the Book Store API, follow these steps:

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/laroshan/Book_Store_API.git
   cd Book_Store_API
   ```

2. **Ensure Java and Maven are Installed**:

   ```bash
   java -version
   mvn -version
   ```

3. **Compile and Run the Application**:

   ```bash
   mvn clean package
   mvn exec:java -Dexec.mainClass="com.bookStore.Main"
   ```

4. **Access the API**:
   The server will be running at `http://localhost:8087`.

## API Endpoints

Here are the key endpoints:

- **Books**:

  - `POST /books/add`
  - `GET /books/getAll`
  - `GET /books/get/{id}`
  - `PUT /books/update/{id}`
  - `DELETE /books/delete/{id}`

- **Authors**:

  - `POST /authors/add`
  - `GET /authors/getAll`
  - `GET /authors/get/{id}`
  - `PUT /authors/update/{id}`
  - `DELETE /authors/delete/{id}`

- **Customers**:

  - `POST /customers/add`
  - `GET /customers/getAll`
  - `GET /customers/get/{id}`
  - `PUT /customers/update/{id}`
  - `DELETE /customers/delete/{id}`

- **Orders**:

  - `POST /orders/add`
  - `GET /orders/getAll`
  - `GET /orders/get/{id}`
  - `PUT /orders/update/{id}`
  - `DELETE /orders/delete/{id}`

- **Carts**:

  - `POST /carts/add`
  - `GET /carts/getAll`
  - `GET /carts/get/{id}`
  - `PUT /carts/update/{id}`
  - `DELETE /carts/delete/{id}`

## Usage

To interact with the API, you can use tools like `curl` or `Postman`. Here's an example of how to add a new book using `curl`:

```bash
curl -X POST 'http://localhost:8087/books/add' \
  -H 'Content-Type: application/json' \
  -d '{"id": "Book1", "title": "Effective Java", "authorId": "Author1", "price": 45.99, "stock": 10}'
```
