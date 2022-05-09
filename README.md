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

It's a creational design pattern that lets you to assure that a class only has one instance while yet offering global access to that instance.
Used this pattern to store the data for the entire application. 

Class implemented using singleton pattern is Inventory Database.An instance of the Inventory Database is created and the getter methods is called in the required classes by only one instance and it can be reused.

## Factory Pattern:

Factory Method is a creational design pattern that provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created.
Used this pattern by creating an interface "OrderResultGenerator" which is implemented by "OrderBillGenerator" and "OrderErrorGenerator"
