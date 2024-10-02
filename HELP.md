# Spring Bean Lifecycle

Instantiate --> Populate Properties --> Call setBeanName of BeanNameAware -->
Call setBeanFactory of BeanFactoryAware --> Call setApplicationContext of ApplicationContestAware --> Preinitialization (Bean PostProcessors) -->
afterPropertiesSet of Initializing Beans --> Custom Init Method --> Post Initialization (BeanPostProcessors) --> Bean ready to use



When the container is shutdown
Container Shutdown --> @PreDestroy annotated Method --> Disposable Bean's destroy() --> Terminated



* [Tutorials point](https://www.tutorialspoint.com/spring/spring_bean_life_cycle.htm)
* [Geeks For Geeks](https://www.geeksforgeeks.org/bean-life-cycle-in-java-spring/)

Spring has 14 'Aware' interfaces. These are can be useful when you want to modify Spring framework.
