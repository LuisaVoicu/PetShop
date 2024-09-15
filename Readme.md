# Pet Shop Application

**Author:** Laura-Luisa Voicu  
**Group:** 30236

## Table of Contents
1. [Project Overview](#project-overview)
2. [Deliverables](#deliverables)
   - [Deliverable 1](#deliverable-1)
     - [Project Specification](#project-specification)
     - [Functional Requirements](#functional-requirements)
     - [Use Case Model](#use-case-model)
     - [UML Use Case Diagrams](#uml-use-case-diagrams)
     - [Supplementary Specification](#supplementary-specification)
     - [Non-functional Requirements](#non-functional-requirements)
     - [Design Constraints](#design-constraints)
     - [Glossary](#glossary)
   - [Deliverable 2](#deliverable-2)
     - [Domain Model](#domain-model)
     - [Architectural Design](#architectural-design)
     - [Component and Deployment Diagrams](#component-and-deployment-diagrams)
   - [Deliverable 3](#deliverable-3)
     - [Design Model](#design-model)
     - [System Testing](#system-testing)
     - [Future Improvements](#future-improvements)
3. [Conclusion](#conclusion)
4. [Bibliography](#bibliography)

## Project Overview
The project represents a pet shop application with functionalities for adopting and giving up pets for adoption. Different user roles include Administrator, Sellers, Customers, and Pet Fosterers. The application features an authentication system using Spring Security.

## Deliverables

### Deliverable 1
#### Project Specification
A pet shop application offering functionalities like product management, pet adoption, and role-based access for administrators, sellers, customers, and pet fosterers.

#### Functional Requirements
- User registration and authentication
- Customer features: Browse, filter, wishlist, and purchase products
- Seller features: Create, update, and manage products
- Pet Foster features: Manage pet adoption listings
- Administrator features: Manage users, products, and pet types

#### Use Case Model
Includes scenarios like Admin managing users, Customers buying products, and Sellers managing their products.

#### UML Use Case Diagrams
Graphical representation of system interactions between users and the application.

#### Supplementary Specification
Defines non-functional requirements such as security, maintainability, usability, and reliability.

#### Non-functional Requirements
- **Security:** Passwords are hashed for security.
- **Maintainability:** Adheres to software design principles.
- **Usability:** Easy to navigate UI.
- **Reliability:** Extensive testing to ensure stability.

#### Design Constraints
Constraints include using Java, Spring Boot, and MySQL.

#### Glossary
Noteworthy terms and their definitions.

### Deliverable 2
#### Domain Model
Conceptual class diagrams representing the structure of the application.

#### Architectural Design
- **Conceptual Architecture:** Utilizes a Layered Architecture.
- **Package Design:** Organized into packages based on functionality.

#### Component and Deployment Diagrams
Graphical representation of the system's components and their deployment.

### Deliverable 3
#### Design Model
- **Dynamic Behavior:** Interaction diagrams for login and product shopping.
- **Class Diagram:** UML class diagrams applying GoF patterns.

#### System Testing
Unit tests using JUnit and Mockito for controllers and services.

#### Future Improvements
- Two-Factor Authentication
- Pet fostering pop-ups
- User report and ban functionality
- Animated gifs for interactive experience

## Conclusion
This pet shop application offers a user-friendly experience for customers, sellers, and pet fosterers with secure authentication and a robust feature set. Future enhancements aim to improve trust and personalization.

## Bibliography
1. [Implementing Token and Role Base Authentication Using Spring Boot + JWT+ MySQL - YouTube](#)
2. [Spring Boot Testing](https://www.baeldung.com/spring-boot-testing)
3. [Angular Tutorial](https://www.youtube.com/watch?v=UIWK5_DrZ7w&list=PLpaspowtqj-dlt-C7FvRG42LNRFcXdRDD&ab_channel=CodeWithNasir)

