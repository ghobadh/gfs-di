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
* Camel naming will be changed with "_" . for example : UnitOfMeasure --> unit_of_measure
* Hibernate property in Spring is --> spring.jpa.hibernate.ddl-auto
* Option are none, validate, update, create, create-drop
* Default option for embedded db such as hsql, h2, derby is create-drop , otherwise, it will be 'none' as default.
* Data can be loaded from import.sql
  * This is hibernate not Spring feature
  * Must be on root of the class path
  * You may NEED to add spring.jpa.defer-datasource-initialization=true property into application.properties
* Only executed if hibernate's ddl-auto property is set to create or create-drop

#### Spring JDBC

* Spring's DataSource initializer via Spring Boot will be default load schema.sql and data.sql from the root of the
  classpath
* Spring Boot will also load from scheam-${platform}.sql and data-${platfor}.sql
  * Must set spring.datasource.platform property
* May conflict with Hibernate's DDL Auto property
  * Should use setting of 'none' or 'validate' 

# Testing In Spring

Pleas check 'RecetteProject' for different test style .
Also, in 'Gargamel Pet Clinic' you can fine the upgrading JUnit5

## Testing Terminology

* TDD - Test Driven Development - write test first, which will fail , then code to 'fix' test
* BDD - Behaviour Driven Development - Builds on TDD and specifies that tests of any unit of software should be
  specified in term of desired behaviour of the unit
* Mock - A face implementation of a class used for testing. Like a test double
* Spy - A partial mock, allowing you to override select methods of a real class

## Test Scope Dependencies

Using spring-boot-starter-test (default from Spring Initalizr will load the following dependenciese)

* JUnit - De-facto standard for unit testing java application
* Spring Test and Spring Boot Test - utilities and integration test support for Spring Boot applications
* AssertJ - A fluent assertion of matcher objects
* Hamcrest- A library fo matcher objects
* Mockito - A Java mocking framework
* JSONassert - An assertion library for JSON
* JSONPath - XPath for JSON

## JUnit 4 Annotation

| Annotation                            | Description                                                                                                                              |
|---------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------|
| @Test                                 | Identiies a method as a test method                                                                                                      |
| @Before                               | Executed before each test. It is used to prepare  the test environment (e.g. read inputdata, initialize the class)                       |
| @After                                | Extecuted after each test. It is used to cleanup the test environment. It can also save memory by cleaning up expensive memory structure |
| @BeforeClass                          | Executed once, before the start of all tests. Methods marked with this annotation need to be defined as static to work with JUnit        |
| @AfterClass                           | Executed once, after all tests have been finished. Methos annotated with this annotation need to be defined as static to work with JUnit |
| @Ignore                               | Marchs that the test should be disabled                                                                                                  |
| @Test(expected =<br/> Exception.class | Fails if the method does not throw the named exception                                                                                   |
| @Test(timeout = 10)                   | Fails if the method takes longer than 10 milliseconds                                                                                    |

## Spring Boot Annotations

| Annotation                   | Description                                                             |
|------------------------------|-------------------------------------------------------------------------|
| @RunWith(SpringRunner.class) | Run test with Spring context                                            |
| @SpringBootTest              | Search for Spring Boot Application for configuration                    |
| @TestConfiguration           | Specify a Spring configuration for your test                            |
| @MockBean                    | Inject Mockito Mock                                                     |
| @SpyBean                     | Inject Mockito Spy                                                      |
| @JsonTest                    | Create a Jackson or Gson object mapper via Spring Boot                  |
| @WebMvcTest                  | Used to test web context w/o a full http server                         |
| @DataJpaTest                 | Used to test data layer with embedded database                          |
| @JdbcTest                    | Like @DataJpaTest, but does not configure entity manager                |
| @DataMongoTest               | Configures an embedded MongoDB for testing                              |
| @RestClientTest              | Creates a mock server for testing rest clients                          |
| @AutoConfigureRestDocks      | Allows you to use Spring Rest Docs in tests, creating API Documentation |
| @BootStrapWith               | Used to configure how the TestContext is bootstrapped                   |
| @ConextConfiguration         | Used to direct Spring how to configure the context for the test         |
| @ContextHierarchy            | Allows you to create a context hierarchy with @ConextConfiguration      |
| @ActiveProfile               | Set which Spring Profiles are active for the test                       |

## JUnit 5

| JUnit4       | Junit5      |
|--------------|-------------|
| @Before      | @BeforeEach |
| @After       | @AfterEach  |
| @BeforeClass | @BeforeAll  |
| @AfterClass  | @AfterAll   |
| @Ignore      | @Disabled   |
| @Catgory     | @Tag        |

Please note still you can run Junit 4 or 3 in Junit 5.
Junit 5 needs Java 8 or higher

# Data Binding in Spring

* Command Objects aka Backing Beans : Are used to transfer data to and from web forms
* Spring will automatically bind data of form posts
* Biding done by property name (less 'get'/ 'set')
* Example of a 'PersonBean'
  * 'firstNAme' would bind to property firstName
  * 'address.addressLine1' would bind to the addressLine1one of the address property of the PersonBean
  * email[0]/email[1] would bind to index zero and one of the email ist of Set property of Person

# Handling Exception in Spring framework

## HTTP Status Codes

* HTTP 5XX Server Error (e.g. HTTP 500 is Internal Server Error)
* Other 500 errors are generally not used with Spring MVC
* HTTP 4XX Client errors - Generally Checked exception
  * 400 Bad Request - cannot process due to client error
  * 401 Unauthorized - Authentication required
  * 404 Not found - Resource not found
  * 405 Method not allowed - HTTP method not allowed
  * 409 - conflict - Possible with simultaneous updates
  * 417 Expectation failed - Sometimes used with RESTful interfaces
  * 418 - I'm a teapot - April fools joke from IETS in 1998

## Spring for exception handling

* @ResponseStatus - Allow you to annotate custom exception classes to indicate to the framework the HTTP status you want
  retured when that exception is throw. It is 'global' to the application
* @ExceptionHandler - it works at the controller level and it allows you to define custom exception handling:
  * can be used with @ResponseStatus for just returning a http status
  * can be used to return a specific view
  * also can take total control and work with the model and view
    * 'Model' cannot be a parameter of an ExceptionHandler method
* HandlerExceptionResolver - it is an interface you can implement for custom exception handling
  * Used internally by Spring MVC
  * Note 'Model' is not passed
    * Example:
      public interface HandlerExceptionResolver {
      @Nullable
      ModelAndView resolveException( HttpServletRequest request,
      HttpServletResponse response, @Nullable Object handler, Exception ex );
      }
* Internal Spring MVC Exception Handlers
  * Spring MVC has 3 implementations of HandlerExceptionResolver
    * ExceptionHandlerExceptionResolver - matches uncaught exceptions to @ExceptionHandler
    * ResponseStatusExceptionResolver - looks for uncaught exceptions matching @ResponseStatus
    * DefaultHandlerExceptionResolver - Convert standard Spring Exception to HTTP status codes ( Internal to Spring MVC)
* Custom HandlerExceptionResolver
  * You can provide your own implementations of HandlerExceptionResolver
  * Typically implemented with Spring's Ordered Interface to define order the handlers with run in
  * Custom implementations are uncommon due to Spring robust exception handling
* SimplMappingExceptionResolver
  * A Spring Bean you can define to map exceptions to specific views
  * You only define the exception class name ( no package ) and the view name
  * You can optionally define a default error page

### Which to use them

    * Depends on your specific needs
      * if just setting the HTTP status - use @ResponseStatus
      * if redirection to a view , use SimpleMappingExceptionResolver
      * if both , consider @ExceptionHandler on the controller