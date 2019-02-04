# AbacusTestProject

## Securing API endpoints
Securing the endpoint is extremely important in the production environment as our whole business would depend on it. The best way to secure the endpoints are
* Using HTTPS: Data could be easily stolen by man-in-the-middle-attack if unsecured transfer protocol is, like HTTP, is used. Getting a TSL certificate to make the endpoints secure would avoid those attacks.
* Using Authentication: By applying a strong authentication method like OAuth or OAuth2 with a token expiry would be another good idea to secure the endpoints to avoid unauthorized access to it.
* Validating Inputs: By validating inputs to enter only the correctly formatted data in the application would avoid attacks like SQL injection and would also avoid storing garbage data in the database.
* Tools: By using tools, like Metasploit, would help us detect any vulnerabilities in our APIs. It would also tell us if our API would give away any application-specific technology information.

## Monitoring Endpoints
Using the Spring Boot Actuator we can monitor the endpoints. It provides us with many endpoints to monitor, like /health, /metrics, /info, /prometheus etc. Monitoring these endpoints helps us in maintaining the application.
* The /health endpoint is used to monitor the health of the application. It basically tells us if the application is running in a healthy state, i.e if there is any connectivity issue or if the application is down for some reason.
* The /metrics endpoint gives us information about the JVM, OS, and other application-level metrics. When we enable it, it gives us information about memory, threads, processors, classes loaded, classes unloaded etc.
* The /prometheus endpoint is another very useful tool provided by Spring Boot. Once the API endpoints are exposed, it would collect the metrics by scraping the endpoints regularly.

## Autoscaling a Spring Boot application
The Actuator library provided by Spring Boot can expose metrics under /actuator/metrics endpoint. This would provide us with a lot of information about JVM, CPU metrics, number of requests coming in. A dedicated Jenkins pipeline is responsible for monitoring the application’s metrics by polling the endpoint /actuator/metrics periodically. If any metrics that are being monitored are above the target range, then it would run a new instance.
* Spring Boot allows us to run multiple instances of an application on a single machine by changing the port numbers.
* By providing the Actuator dependency information in pom.xml file and by activating it in the properties file, we can easily set it up.
* The Actuator also provides a mechanism to shut down instances of the application if it is not being used.
* Enabling a discovery server, like Eureka. To add this we would need to Eureka dependency in pom.xml and enable it.
* The next step would be to create a Jenkins pipeline and integrate it with Eureka. It will provide Jenkins with a list of all the instances and the URL.
* By integrating Jenkins with Spring Boot Actuator, it would allow us to access all the metrics exposed by it and we can create a script to scale it up or down based on the number of requests.
