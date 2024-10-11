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

## JPA

- Hibernate 5 is complying with JPA 2.1

### JPA Cascade Type

By default, no operation are cascaded.

* PERSIST: Save operations will cascade to related entities
* MERGE: related entities are merged when the owning entity is merged
* REFRESH: related entities are refreshed when the owning entity is refreshed
* REMOVED: removes all related entities when the owning entity is deleted
* DETACH: detaches all related entities if a manual detach occurs
* ALL: Applies all the above cascade options

### Embeddable Type

* JPA/Hibernate do support an embeddable tpes and this is basically a POJO
* There are used to define a common set of propertie. e.g. an order might have a billing address and a shipping address
* An embeddable type could be used for the address properties.

#### Inheritance

Hibernate supports inheritance by using 'MappedSuperClass' . A database table is NOT created for the super class.

* Single Table: (Hibernate Default) - One table is used for all subclasses
* Joined Tables: Based class and subclasses have their own tables. Fetching subclass entities require a join to the
  parent table
* Table Per Class: Each subclass has its own table

#### Create and update Timpestamp

* Often a best practice to use create and update timestamps on your entities for audit proposes
* JPA supports @PrePersist and @PreUpdate which can be used to support audit timestamps via JPA lifecycle callbacks
* Hibernate provides @CreationTimestamp and @UpdateTimestamp (This is hibernate specific)

#### Hibernate DDL Auto

* DDL = Data Definition Language
* DML = Data Manipulation Language
* Hibernate property in Spring is --> spring.jpa.hibernate.ddl-auto
* Option are none, validate, update, create, create-drop
* Default option for embedded db such as hsql, h2, derby is create-drop , otherwise, it will be 'none' as default.
* Data can be loaded from import.sql
  * This is hibernate not Spring feature
  * Must be on root of the class path
* Only executed if hibernate's ddl-auto property is set to create or create-drop

#### Spring JDBC

* Spring's DataSource initializer via Spring Boot will be default load schema.sql and data.sql from the root of the
  classpath
* Spring Boot will also load from scheam-${platform}.sql and data-${platfor}.sql
  * Must set spring.datasource.platform property
* May conflict with Hibernate's DDL Auto property
  * Should use setting of 'none' or 'validate' 