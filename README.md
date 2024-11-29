# VRV Project

The User Management platform is a web-based platform designed for task and user profile management. It provides a seamless experience by offering role-based access to users and administrators. The platform allows users to create, update, and view tasks, while admins can manage tasks and user profiles with full access. The application uses Spring MVC for the backend, Hibernate for ORM, MySQL as the database, and Spring Security for authentication and authorization.

## Features

- **User Management**: Users can register, log in, and manage their profile. Role-based access is implemented, with distinct roles for users and admins.
- **Task Management**: Users can create tasks with detailed information. Admins can manage tasks for all users.
- **Security**: Uses Spring Security, JWT for authentication, and BCrypt Password Encoder for secure password storage and access control.
- **Role-Based Access Control**: Different access levels are provided to Users and Admins to manage resources based on roles.

## Technology Stack

- **Backend**: Spring MVC
- **Database**: MySQL with Hibernate ORM
- **Security**: Spring Security, JWT, BCrypt Password Encoder
- **Frontend**: Minimalistic, intuitive design (supports interaction with RESTful APIs)

## Installation

Follow these steps to set up the project locally.

### Prerequisites

Make sure you have the following installed:

- JDK 17 (or later)
- Maven (for building the project)
- MySQL Database Server

### Use My Hosted URL and Postman APIs

To test the API endpoints, you can import the provided Postman collection and use the hosted URL.

1. **Import the Postman Collection**:  
   Download the Postman collection file `User Management Backend.postman_collection.json` and import it into Postman.

   - Open Postman.
   - Click on the **Import** button in the top left corner.
   - Choose **File** and select the `User Management Backend.postman_collection.json` file to import the collection.

2. **Test the APIs Using the Hosted URL**:
   **Base URL**: http://13.201.21.187:8080/VRV/
   The hosted URL for testing the API endpoints is:http://13.201.21.187:8080/VRV/endpoints

