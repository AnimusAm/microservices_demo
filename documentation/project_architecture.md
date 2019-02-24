# *microservices_demo* project architecture

Project ***microservices_demo*** demonstrates different possibilities of building microservices based application, and its sole purpose is to explore and learn different approaches, mechanisms and possibilities in building microservices based application.
It represents a suite of different implementations, using different technologies, that all together demonstrate unique ecosystem. 

It's basic structure consists of several components:

 - **configuration** - contains implementations of configuration servers' instances
 - **cloud_servers** - servers that provide service discovery for all the services in this suite 
    - in the 'microservice ecosystem' these servers represent purely technical backbone for microservices architecture
 - **application servers** - servers that provide 'services' that would naturally reside on the server side inside business applications domain 
    - in the 'microservice ecosystem' these servers represent service providers for the other business domain applications - which qualifies them as 'servers' in business logic domain, but form the microservices architecture point of view, they are considered 'clients microservices'
 - **application_clients** - 'end point' applications that are possibly, but not necessarily consumers of the other microservices in the 'eco system'
 
 Project contains these concrete demo implementations for each of the previously mentioned components:

 - **configuration** 
    - **cloud_config_server** - a SpringCloud based configuration server app that provides configuration properties for all services/apps in the project
        -  configuration server uses remote git repository to store configuration properties. This repository is maintained by independent project ***demo_config***
 - **cloud_servers** 
    - **discovery_server** - an Eureka based discovery server that manages services discovery for all services in this suite
 - **application servers**
    - **nosql_db_service_rest_templete** - an Spring Cloud Client Discovery service that at one point exposes REST API (based on RestTemplate) interface and on the other point connects to the NoSQL DB
 - **application_clients**
    - **db_client_demo** - stand alone 'monolithic' application based on Spring Boot in standard MVC layout using Spring Hibernate as ORM for accessing ER (MySQL) DB, and Spring MVC structure with Vaadin UI representation
    - **client_service_rest_template** - microservice based client application that consumes other microservice's services over RestTemplate interface at one point, and provides customised RestTemplate based interface to possible other consumers on the other side