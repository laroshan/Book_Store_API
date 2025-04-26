````markdown
# Health System API

The Health System API is a RESTful service for managing healthcare-related data, including doctors, patients, appointments, medical records, prescriptions, and billing information. Built with Java and JAX-RS, it supports CRUD operations for each entity.

## Setup and Installation

To run the Health System API, follow these steps:

1. **Clone the Repository**:
   ```bash
   git clone <your-repository-url>
   cd <your-repository-directory>
   ```
````

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

- **Doctor**:

  - `POST /doctors/add`
  - `GET /doctors/getAll`
  - `GET /doctors/get/{id}`
  - `PUT /doctors/update/{id}`
  - `DELETE /doctors/delete/{id}`

- **Patient**:

  - `POST /patients/add`
  - `GET /patients/getAll`
  - `GET /patients/get/{id}`
  - `PUT /patients/update/{id}`
  - `DELETE /patients/delete/{id}`

- **Appointment**:

  - `POST /appointments/add`
  - `GET /appointments/getAll`
  - `GET /appointments/get/{id}`
  - `PUT /appointments/update/{id}`
  - `DELETE /appointments/delete/{id}`

- **Medical Record**:

  - `POST /medicalrecords/add`
  - `GET /medicalrecords/getAll`
  - `GET /medicalrecords/get/{id}`
  - `PUT /medicalrecords/update/{id}`
  - `DELETE /medicalrecords/delete/{id}`

- **Prescription**:

  - `POST /prescriptions/add`
  - `GET /prescriptions/getAll`
  - `GET /prescriptions/get/{id}`
  - `PUT /prescriptions/update/{id}`
  - `DELETE /prescriptions/delete/{id}`

- **Billing**:
  - `POST /billings/add`
  - `GET /billings/getAll`
  - `GET /billings/get/{id}`
  - `PUT /billings/update/{id}`
  - `DELETE /billings/delete/{id}`

## Usage

To interact with the API, you can use tools like `curl` or `Postman`. Here's an example of how to add a new doctor using `curl`:

```bash
curl -X POST 'http://localhost:8087/doctors/add' \
  -H 'Content-Type: application/json' \
  -d '{"id": "Doc1", "name": "Dr. Alice Johnson", "contact": "123-456-7891", "address": "123 Health St.", "specialty": "Dermatology"}'
```
