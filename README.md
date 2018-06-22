# tulaa-partB
tulaa-part B Interview Questions

# Environment Setup
* Make sure that the host computer has JAVA 8 
* Make Sure that the host computer has maven installed 

# How to run the project
* Clone the project on your host machine
* cd into the cloned cloned project i.e you should be inside the cloned directory
* Run the project using this command, ./mvnw spring-boot:run
* You can now access the project using this url; localhost:8080

# Endpoint urls
* http://localhost:8080/rev
  * on the end point url supply values eg http://localhost:8080/rev?value=uueerr
  * on postman you will see that the value supplied is being reversed
  
* http://localhost:8080/pascal
  * on the end point url supply values eg http://localhost:8080/pascal?value=2
  * on postman you will see status of {
                                        	"response": "ok"
                                        } , and on the console you will see the results

  
* http://localhost:8080/parentheses
  * on the end point url supply values eg http://localhost:8080/parentheses?value=3
  * on postman you will see status of {
                                      	"response": "ok"
                                      } , and on the console you will see the results
                                      
                                      
# Project Client
* Run the project using this command, ./mvnw spring-boot:run
* You can now access the project using this url; localhost:8080
* On the web browser access the url http://localhost:8080 and on the interface enter any random string containing special characters then click on the button reverse the string
