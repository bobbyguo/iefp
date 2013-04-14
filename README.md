iefp
====

Internal Email Finder Provider

Current API implementation uses dropwizard framework. Follow these instructions to start the API services

1. mvn clean package (to build the fat jar) - This produces a fat jar XXXX-executable.jar
2. from the api directory,run "java -Dorg.jboss.logging.provider=slf4j -jar target/dfiefp-x.0-SNAPSHOT-executable.jar server src/main/resources/api-config.yaml"

To run from eclipse, set up the following run configuration:

1. Project: dfiefp
2. Main class: com.demandforce.api.spring.common.SpringService
3. Include system libraries when searching for a main class
4. Program arguments: server src/main/resources/api-config.yaml
5. VM arguments: -Dorg.jboss.logging.provider=slf4j -Ddw.http.port=8080 -Ddw.http.adminPort=8081

To run the services on different ports other than 8080 and 8081(for admin), run the java process with following port config -Ddw.http.port=9090 -Ddw.http.adminPort=9091.
