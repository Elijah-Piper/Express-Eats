# Express-Eats
A full-stack restaurant meal delivery web application using Spring Boot + MySQL on the backend and React.js on the frontend.

## Installation
Upon cloning the github repo:
1. The "LocalBuySellAPI" directory will contain the Spring Boot backend API (JDK 18 required)
    - Two configuration files must be changed to for database to work locally: hibernate.cfg.xml and application.properties
        - Must replace with your own MySQL schema, MySQL username, and password
    - Can be finally started by running LocalBuySellApplication.java
        - If successfully configured, the API should be open to requests through localhost:8080
2. The "frontend" directory should contain the ReactJS frontend code (NodeJS required)
    - Within the "frontend" directory, use the command "npm install" to install required dependencies
    - "npm start" wll then start up the development server for the frontend
    - The frontend configuration is already set up to interface with the backend, although by default, the backend will not populate any test data automatically.
    - The frontend will, by default, be accessible from localhost:3030

## API endpoints
