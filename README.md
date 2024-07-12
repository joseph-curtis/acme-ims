# Inventory Management System

## Overview

This project is a Java-based desktop application developed for a small manufacturing organization (Acme) to manage their inventory system. The application replaces the manual process of using spreadsheets for inventory management, allowing for more sophisticated and automated inventory tracking. The system was designed and implemented to meet specific business requirements, incorporating various object-oriented principles and Java programming constructs.

## Documentation

Source documentation provided via [JavaDocs here](https://htmlpreview.github.io/?https://raw.githubusercontent.com/joseph-curtis/acme-ims/master/javadocs/index.html "JavaDocs here")

## Features

### Classes and Interfaces
- Designed software solutions with appropriate classes, objects, methods, and interfaces.
- Implemented a class diagram and adhered to the provided UML class structure.

### Object-Oriented Principles
- Utilized inheritance, encapsulation, and abstraction to ensure scalability.
- Developed a modular and maintainable codebase.

### Application Development
- Developed using Java programming language constructs.
- Integrated JavaFX for building the user interface.
- Used JDK 11 for development.

### Exception Handling
- Incorporated exception handling to improve user experience and application stability.
- Ensured robust error management to handle unexpected scenarios gracefully.

### User Interface Development
- Developed the user interface to meet project requirements using JavaFX and Scene Builder.
- Followed the provided GUI mock-up for design consistency.

## Requirements

### Software
- JDK 11
- JavaFX SDK or Module (for NetBeans or IntelliJ IDEA)
- Scene Builder

### Functional Requirements
1. **Add Inventory Items:**
   - Allows users to add new inventory items.
   - Captures item details such as name, stock, price, and associated parts.
   
2. **Modify Inventory Items:**
   - Enables modification of existing inventory items.
   - Supports updating item details and associated parts.
   
3. **Delete Inventory Items:**
   - Provides functionality to delete inventory items from the system.
   - Ensures safe deletion with confirmation prompts to prevent accidental data loss.

4. **Manage Parts:**
   - Supports adding, modifying, and deleting parts associated with inventory items.
   - Implements a searchable parts list for easy management.

5. **Search Functionality:**
   - Implements search functionality for both inventory items and parts.
   - Allows users to quickly locate specific items using various search criteria.

6. **Error Handling:**
   - Includes error handling for user inputs and system operations.
   - Provides user-friendly error messages and prompts for corrective actions.

## How to Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/joseph-curtis/acme-ims.git
   ```
2. **Open the project in your IDE:**
   - Use NetBeans or IntelliJ IDEA with JavaFX support.

3. **Configure JavaFX:**
   - Ensure that JavaFX SDK is properly configured in your IDE.
   - Follow the setup guide for JavaFX in your specific IDE.

4. **Run the application:**
   - Locate the `IMS_Application.java` file and run it.
   - The application will launch with the main inventory management interface (MainForm.fxml).

## Project Structure

```
acme-ims/
│
├── javadocs/
├── src/
│   ├── controller/
│   │   ├── MainController.java
│   │   ├── PartController.java
│   │   ├── ProductController.java
│   ├── main/
│   │   ├── IMS_Application.java
│   ├── model/
│   │   ├── Inventory.java
│   │   ├── Part.java
│   │   ├── Product.java
│   ├── util/
│   │   ├── GuiUtil.java
│   │   ├── BlankInputException.java
│   │   ├── InvObjNotFoundException.java
│   │   ├── InvalidInputException.java
│   ├── view/
│   │   ├── MainForm.fxml
│   │   ├── PartForm.fxml
│   │   ├── ProductForm.fxml
└── README.md
```

## License

This project is licensed under the [ECL-2.0 License](http://opensource.org/licenses/ECL-2.0). See the [LICENSE](LICENSE) file for details.
