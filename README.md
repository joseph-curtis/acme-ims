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

## UML Class Diagram

[![](https://mermaid.ink/img/pako:eNrtV1tr2zAU_itG7MGmTohzrymF0g0WaLtC2cvIi2JpqaktBVnukmXpb59uji1bzQobbA_zS-RzvvOdi46OlT1IKMIgBr1eb8mWhKc8w7H3-fbGu85gUXjvU7hmMJc6A0mkvCH2xHMPGfcufvR63oJ8pGWBu4pPJS9oyRKMKt2CPGPCKdt5_f6lQr6iYRSVyVFpXjtGKq7adF_J5SPcrwrMnuEqwzdpwV-k4YsHs0wuinensdqhhuu1bXHmQYQkka_SJfib_A3eec80RV2gpvCrPCRcL50WGaVP5Uaxp4R7G7FYIIFspm4hDb0C67XG21XskD9wlpK14r-DORYWrpKd8lgx6NdXSEwtbZ5ygyDHxxxTgvA21L1T4AwnHKNXK2psG1lX5m-rLxIOjG-XwxWlGYbEadPayKPl0ZvTeI35lek7_01VNgam8_xfF_VgHwlJaZ2Gi4urVcEZTPjlZS3veap4qCkxW0rEXjbFiJbCt9jpNMFtgoLT5KktzFPSEcFtLTrz6s1HYdNtaDkLaxehVzGHFV_QJCyw6HvDGLT2XWllg_oNT07QvfTqN0Nwwh5kRP4xNifmNiW-idith1u_SqSjX6tsAplpS6zSCEzJWjodfWBK2FLqmF2UMlKnXERoyduNpvtw39xq99wtCpqk0Jyy4p_pweYQeXMb_u_Cv9yF5rt6ZTWVHubya9ZNQ49vF74a4bYu6A7yairbQDGcT87zQ_uuou5K-25bJo8pwQsraAP-zeYMbQdBZ_eNwrdhrk6osadmQn3t2zsOdULzDSS7O-ts_7lcu17aCV_XKt-BduXdNGl3_AGEYM1SBGLOShyCHLMcylewl4Al4I9YpApisUSQPS3BkkgbQfiF0rwyY7RcP4L4K8wK8aZvOObGXUFgyenDjiTVuwZ9QKm4-x4tGRZXIXZNS8JBPJ7MlCMQ78EWxNF82I9G89F0ej4bR8PRaBqCHYgns350Hk2H0XA4mMyHg-n4EILvKrZBfz6bDMQTzaLR5HwyH4cAK4e3-j-E-itx-AmBg_KQ?type=png)](https://mermaid.live/edit#pako:eNrtV1tr2zAU_itG7MGmTohzrymF0g0WaLtC2cvIi2JpqaktBVnukmXpb59uji1bzQobbA_zS-RzvvOdi46OlT1IKMIgBr1eb8mWhKc8w7H3-fbGu85gUXjvU7hmMJc6A0mkvCH2xHMPGfcufvR63oJ8pGWBu4pPJS9oyRKMKt2CPGPCKdt5_f6lQr6iYRSVyVFpXjtGKq7adF_J5SPcrwrMnuEqwzdpwV-k4YsHs0wuinensdqhhuu1bXHmQYQkka_SJfib_A3eec80RV2gpvCrPCRcL50WGaVP5Uaxp4R7G7FYIIFspm4hDb0C67XG21XskD9wlpK14r-DORYWrpKd8lgx6NdXSEwtbZ5ygyDHxxxTgvA21L1T4AwnHKNXK2psG1lX5m-rLxIOjG-XwxWlGYbEadPayKPl0ZvTeI35lek7_01VNgam8_xfF_VgHwlJaZ2Gi4urVcEZTPjlZS3veap4qCkxW0rEXjbFiJbCt9jpNMFtgoLT5KktzFPSEcFtLTrz6s1HYdNtaDkLaxehVzGHFV_QJCyw6HvDGLT2XWllg_oNT07QvfTqN0Nwwh5kRP4xNifmNiW-idith1u_SqSjX6tsAplpS6zSCEzJWjodfWBK2FLqmF2UMlKnXERoyduNpvtw39xq99wtCpqk0Jyy4p_pweYQeXMb_u_Cv9yF5rt6ZTWVHubya9ZNQ49vF74a4bYu6A7yairbQDGcT87zQ_uuou5K-25bJo8pwQsraAP-zeYMbQdBZ_eNwrdhrk6osadmQn3t2zsOdULzDSS7O-ts_7lcu17aCV_XKt-BduXdNGl3_AGEYM1SBGLOShyCHLMcylewl4Al4I9YpApisUSQPS3BkkgbQfiF0rwyY7RcP4L4K8wK8aZvOObGXUFgyenDjiTVuwZ9QKm4-x4tGRZXIXZNS8JBPJ7MlCMQ78EWxNF82I9G89F0ej4bR8PRaBqCHYgns350Hk2H0XA4mMyHg-n4EILvKrZBfz6bDMQTzaLR5HwyH4cAK4e3-j-E-itx-AmBg_KQ)

## License

This project is licensed under the [ECL-2.0 License](http://opensource.org/licenses/ECL-2.0). See the [LICENSE](LICENSE) file for details.
