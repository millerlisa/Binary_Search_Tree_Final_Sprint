Binary Search Tree Visualization - Spring Boot Application

Objective: 
This project is a Spring Boot application that allows users to create a binary search tree (BST) from a series of numbers, visualize the resulting tree graphically and in JSON format, and view previously created trees stored in a database.

Features
1. User Input Interface
Route: /enter-numbers
Displays an HTML page where users can:
Enter a series of numbers separated by commas.
Submit the input to generate and visualize a binary search tree.
View previously created trees by clicking the "Show Previous" button.

2. Processing Route
Route: /process-numbers
Handles user input:
Accepts a comma-separated list of numbers.
Constructs a binary search tree by inserting the numbers sequentially.
Serializes the tree into JSON format.
Stores the input numbers and the resulting tree structure in a database.
Returns the graphical and JSON representation of the tree to the user.

3. Display Previous Trees
Route: /previous-trees
Features:
Retrieves input numbers and corresponding tree structures stored in the database.
Displays links to visualize each previously created tree.
Allows users to navigate back to the input page.

4. Tree Visualization
Interactive visualization of the binary search tree:
Graphical representation using D3.js.
JSON representation for debugging and detailed inspection.

5. Testing
Includes three unit tests to validate core application functionalities:
Tree creation and structure validation.
Database persistence and retrieval.
Backend route functionality.

Database
Purpose: Store input numbers and their corresponding binary search tree structures.
Implementation:
  Each entry contains:
    Input numbers (comma-separated string).
    Serialized tree structure (JSON).
    The database is accessed via a Spring Data JPA repository.

Project Setup
  Prerequisites
    JDK 11 or higher
    Maven
    A database (e.g., MySQL or H2 for in-memory testing, I used MySQL)
    A modern web browser for accessing the UI

Setup Steps
1. Clone the Repository:
  git clone <repository-url>
  cd <project-folder>

2. Configure the Database:
  Update the database connection details in application.properties:
    spring.datasource.url=jdbc:mysql://localhost:3306/bst_visualizer
    spring.datasource.username=your-username
    spring.datasource.password=your-password
    spring.jpa.hibernate.ddl-auto=update

3. Build and Run:
     mvn clean install
     mvn spring-boot:run

4. Access the Application:
     Open your browser and navigate to http://localhost:8080/enter-numbers

Usage
Create a New Tree:
  Enter a series of numbers in the input field on the /enter-numbers page.
  Click "Submit" to visualize the binary search tree.

View Previous Trees:
  Click "Show Previous" to navigate to /previous-trees.
  Click on any listed tree to visualize it.

Explore JSON Representation:
  Each tree visualization includes a formatted JSON representation.

  
Testing
Run Unit Tests
  Execute the following command to run unit tests:
    mvn test
  Tests validate:
    Tree creation logic.
    Data persistence and retrieval.
    API functionality.

Technologies Used
  Backend:
    Spring Boot
    Spring Data JPA
  Frontend:
    Thymeleaf
    Bootstrap
    D3.js for tree visualization
  Database:
    MySQL
  Testing:
    JUnit 5

Future Enhancements:
Add options for balancing the binary search tree.
Include visual options for displaying tree traversal (e.g., in-order, pre-order, post-order).
Provide export options for tree structures (e.g., as JSON or PNG).





