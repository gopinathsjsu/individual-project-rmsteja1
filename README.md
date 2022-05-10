# individual-project-rmsteja1

individual-project-rmsteja1 created by GitHub Classroom


PROBLEM:
Design and implement a Java application for the following requirements. You may choose up to 3 design patterns. Include a class diagram for your design. All submissions should be committed to the assigned Github repo. Please include a README file with very clear instructions on how to build and run your application.

Steps to run the application:

- Open terminal.
- Run "mvn compile mvn exec:java -Dexec.mainClass=com.inventory.OrderBilling"

# Design Patterns

* Singleton

* Factory

## Singleton:
Singleton comes under creational pattern, it provides one of the best ways to create an object.In singleton pattern a single class is responsible to create and object. It also ensures that only single objects gets created.This class provides a way to access its only object which can be accessed directly without need to instantiate the object of the class.This class offeres global access to that instance.Used this pattern to store the data for the entire application. Class implemented using singleton pattern is Stock Database.An instance of the Stock Database is created and the getter methods is called in the required classes by only one instance and it can be reused.

## Factory Pattern:
This type of design pattern comes under creational pattern.Factory pattern is one of the most used design patterns in Java.In factory pattern, a new object is created without exposing the logic to the user and refer to the newly created object using a common interface.In factory design pattern provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created.Used this pattern by creating an interface "OrderResultCreator" which is implemented by "OrderBillCreator" and "OrderErrorCreator".

## Class Diagram:
![Alt text](https://github.com/gopinathsjsu/individual-project-rmsteja1/blob/main/images/202_individual_project_class_diagram.png "Optional title")

## Input Values:
![Alt text](https://github.com/gopinathsjsu/individual-project-rmsteja1/blob/main/images/Example_for_valid_input.png "Optional title")

## Output Values:
![Alt text](https://github.com/gopinathsjsu/individual-project-rmsteja1/blob/main/images/output_result_for_valid_input.png "Optional title")

## Error Handling:
![Alt text](https://github.com/gopinathsjsu/individual-project-rmsteja1/blob/main/images/Example_for_invalid_input.png "Optional title")

## Error Log:
![Alt text](https://github.com/gopinathsjsu/individual-project-rmsteja1/blob/main/images/error_log_for_invalid_input.png "Optional title")
