# Spring Bean Lifecycle

Instantiate --> Populate Properties --> Call setBeanName of BeanNameAware -->
Call setBeanFactory of BeanFactoryAware --> Call setApplicationContext of ApplicationContestAware --> Preinitialization (Bean PostProcessors) -->
afterPropertiesSet of Initializing Beans --> Custom Init Method --> Post Initialization (BeanPostProcessors) --> Bean ready to use



When the container is shutdown
Container Shutdown --> @PreDestroy annotated Method --> Disposable Bean's destroy() --> Terminated



* [Tutorials point](https://www.tutorialspoint.com/spring/spring_bean_life_cycle.htm)
* [Geeks For Geeks](https://www.geeksforgeeks.org/bean-life-cycle-in-java-spring/)

Spring has 14 'Aware' interfaces. These are can be useful when you want to modify Spring framework.

### Spring Bean Scope

* Singleton (default) - Only one instance of the bean is created in the IoC container
* Prototype - A new instance is created each time the bean is requested
* Request - A single instance per http request. Only valid in the context of the web-aware Spring ApplicationContext
* Session - A single instance per http session. Only valid in the context of the a web-aware Spring ApplicationContext
* Global Session - A single instance per global session. Typically only used in a portlet context. only valid in the
  context of a web-aware Spring ApplicationContext
* Application - bean is scoped to lifecycle of the ServletContext. only valid in the context of the web aware.
* Websocket - scope a single bean definition to the lifecycle of a webscoket . only valid in the context of a web-aware
  Spring ApplicationContext
* Custom Scope - Spring scope are extensible and you define your own scope by implementing spring 'scope' interface. You
  cannot override in the build in Singleton and Prototype Scope

#### Declaring Bean Scope

* No declaration needed for singleton scope
* in Java Configuration use @Scope annotation
* in XML configuration scope in an xml attribute of the bean tag
* 99% of the time singleton scope is fine

### Setting External Properties

* Command Line Arguments
* SPRING_APPLICATION_JSON
* JNDI
* OS Environment variables
* Property files / YAML (most comnon)

#### Property Hierarchy

- Review Section 24 - Externalized Configuration of Spring Boot
- Properties can be overridden depending on how they are defined
- Lowest are properties defined in JAR/WAR properties or YAML files
- NExt are external properties files to JAR via file system
- Higher are profile specific properties files (in jar then external)
- OS Environment Variables
- Java System properties
- JNDI
- SPRING_APPLICATION_JSON
- Command line argument
- Test Properties (for testing)

