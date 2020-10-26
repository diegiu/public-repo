# Tiny Weather Bulletin 

Spring Boot-based project that will be used by employees to check the weather conditions 
in the cities where other team mates work.

## Authors

* **Diego Giudici**

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Compile and Install

Run the *tiny-weather-bulletin/pom.xml* with goals clean install -U spring-boot:repackage as Maven Build.

## Run application

Run as command **java -jar tiny-weather-bulletin-1.0.0.jar** with java 1.8+ 

**N.W.** During the startup and execution of the application the log file will be created at the path *tiny-weather-bulletin\logs* 

See below the application runtime examples:

* Bootstrap HTML can be reached at [http://localhost:8082](http://localhost:8082) Fill out the form and then click the **Execute** button 

* Swagger UI can be reached at [http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html) Click the **Try it out** button, fill out the form and then click the **Execute** button 

* *curl command* **curl -X GET "http://localhost:8082/weather/bulletin/averages?cityName=Milan&fromHours=9&toHours=18" -H "accept: application/json"** 

* *Request URL* [http://localhost:8082/weather/bulletin/averages?cityName=Milan&fromHours=9&toHours=18](http://localhost:8082/weather/bulletin/averages?cityName=Milan&fromHours=9&toHours=18) 