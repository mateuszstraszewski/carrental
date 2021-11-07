### **Recruitment process project**

This is a sample **car rental** backend app project.

This app was based on the following frameworks and tools:

1. Spring Boot
2. JPA
3. H2 (database)
4. jUnit

REST API has the following endpoints:
1. **/cars**
   1. GET - returns list of all cars
   2. POST - allows to add new car to the list. "brand", "model", "regNumber" are required in the body of request. 
2. **/cars/{id}**
   1. GET - returns details of car of {id}
   2. PUT - allows to modify details of car of {id} 
   3. DELETE - deletes car of {id}
3. **/cars/rent/{id}**
   1. PUT - allows to rent a car of {id} by changing its availability status (if possible)
4. **/cars/return/{id}**
   1. PUT - allows to return a car of {id} by changing its availability status (if possible)

Source code also contains exemplary unit and integration tests for controller, repository and service layers.