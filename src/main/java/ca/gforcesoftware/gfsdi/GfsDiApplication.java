package ca.gforcesoftware.gfsdi;

import ca.gforcesoftware.gfsdi.config.GfsConfiguration;
import ca.gforcesoftware.gfsdi.controllers.*;
import ca.gforcesoftware.gfsdi.datasource.DummyDataSource;
import ca.gforcesoftware.gfsdi.datasource.DummyDataSourceArg;
import ca.gforcesoftware.gfsdi.services.PrototypeBean;
import ca.gforcesoftware.gfsdi.services.SingletonBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/*
Because we moved all services under com.gargamel.pets packages which is NOT under ca.gforcesoftware.gfsdi , we need to use @ComponentScan to explicitly tell Spring to check all Spring Stereotype over there also. In
this way, we overwrite the default component scan
Otherwhise, the pet controller will because due to failing find the right service
Please note, over writing the defualt component scan, it raise the reflection and if we have heavy load application, this will slow down the application eventually
 */

/*
In this new changes since we used the Pet Factory to create the objects we wanted now we don't need to explicitly ask spring to scan all components in other directory.
That is why we commented out ComponentScan in below
 */
//@ComponentScan(basePackages = {"ca.gforcesoftware.gfsdi", "com.gargamel.pets"})
@SpringBootApplication
public class GfsDiApplication {

	public static void main(String[] args) {
		/*
		- Dependency Injection is where a needed dependency is injected by another object
		- DI can be at instantiation via constructor or after via setter
		- The class being injected has no responsibility in instantiating the object being injected
		- Some say you avoid declaring object using "new" but this is not 100% correct

		Types of DI
			- class properties (least preferred)
			- by setter
			- by constructor (most preferred

		Concrete classes VS interfaces
		-DI can be done by concrete class or interfaces
		-Generally DI with Concrete classes should be AVOIDED
		-DI via interfaces are highly preferred
			* allow runtime to decide implementation of inject
			* follow interface segregation principle of SOLID
			* make your code more testable


		Inversion of Control (IoC)
			- is a technique to allow dependencies to be injected in runtime
			- dependencies are not predetermined
			- allows the framework to compose the application by controlling which implementation is injected (e.g. H2 in memory or MySQL data source)

		IoC VS DI
		DI refers much to the composition of your class
		IoC is the runtime environment of your code

		BEST Practice for DI
			- Favor using constructor injection over setter injection
			- use 'private final' properties for injected component
			- Whenever practical code to an interface



		 */
		ApplicationContext ctx =SpringApplication.run(GfsDiApplication.class, args);

		//Another way to do it is
		//MyController myController = (MyController) ctx.getBean("myController");

		//In here we did not create new object, in fact spring framework did in our behalf.
		//Spring framework create application context0 list and with line below we ask spring to provide an instance of it
		//In other words, spring is mana0ging the construction of all beans
		//How we did , by using '@Controller' annotation in myController
		MyController myController = ctx.getBean(MyController.class);

		String greeting = myController.sayHello();
		System.out.println(greeting);

		/*This part of code unlike the code above , we try to use spring framework to instantiate our bean
		* for this reason these are steps we did
		*  1- we add @Service to the GreetingServiceImpl
		*  2- we add @Controller to class of each controller
		*  3- We add @Autowired (except to the ConstructorInjectController), the tell spring how to implement the interface
		*  If we don't add these annotation Spring will not be able to start the app and work with beans
		 */

		System.out.println("----- Property");
		PropertyInjectController propertyInjectController = ctx.getBean(PropertyInjectController.class);
		System.out.println(propertyInjectController.getGreeting());

		System.out.println("----- Setter");
		SetterInjectedController setterInjectedController = ctx.getBean(SetterInjectedController.class);
		System.out.println(setterInjectedController.getGreeting());

		System.out.println("----- Constructor");
		ConstructorInjectController constructorInjectController = ctx.getBean(ConstructorInjectController.class);
		System.out.println(constructorInjectController.getGreeting());


		/* This time we use primary service (check PrimaryGreeting Service which we added @Primary to it,
		Therefore, in MyController when we created the constructor , we did not mention @Autowired nor @Qualifier. Now
		because of @Primary , PrimaryGreetingService will be automatically selected.
		Please note, since we added @Primary to our service, we can even remove @Qualifier and when we do it, the controller automatically
		will pick the default primary service from now on.
		 */
		System.out.println("----- Primary Constructor");
		System.out.println(myController.getGreeting());

		/*
		In this brand we are using profile.
		1- we defined two different Services (I18nEnglishGreetingService and I18nSpanishGreetingService) to implement the GreetingService interface
		2- In each service we added @Service ("QUALIFIER_NAME") instead of using @qualifier annotation. both qualifier name are the same in both
		3- In each service we added a new annotation @Profile("PROFILE_NAME")
		4- we defined a new controller I18NController and we added added the (@Qualifier("i18nService")
		4- in resources/application.properties we defined which prifle should be active with using "spring.profiles.active=ES" we don't embrace of profile name with double quote in here

		When we look as the output console , we see spring chose which profile
		--- [gfs-di] [           main] c.gforcesoftware.gfsdi.GfsDiApplication  : The following 1 profile is active: "ES"
		 */
		System.out.println("----- Using ACTIVE PROFILE");
		I18NController i18NController = ctx.getBean(I18NController.class);
		System.out.println(i18NController.sayHello());

		PetServiceController petServiceController = ctx.getBean(PetServiceController.class);
		System.out.println(petServiceController.getPetInfo());

		System.out.println("-------------------- BEAN scope -----------------------");
		SingletonBean singletonBean1 = ctx.getBean(SingletonBean.class);
		System.out.println(singletonBean1.getMyScope());
		SingletonBean singletonBean2 = ctx.getBean(SingletonBean.class);
		System.out.println(singletonBean2.getMyScope());

		PrototypeBean prototypeBean1 = ctx.getBean(PrototypeBean.class);
		System.out.println(prototypeBean1.getMyScope());
		PrototypeBean prototypeBean2 = ctx.getBean(PrototypeBean.class);
		System.out.println(prototypeBean2.getMyScope());


		DummyDataSource dummyDataSource = ctx.getBean(DummyDataSource.class);
		System.out.println(dummyDataSource.getUsername());
		System.out.println(dummyDataSource.getPassword());
		System.out.println(dummyDataSource.getJdbcURL());

        DummyDataSourceArg dummyDataSourceArg = ctx.getBean(DummyDataSourceArg.class);
        System.out.println(dummyDataSourceArg.getArgument1());
        System.out.println(dummyDataSourceArg.getArgument2());
        System.out.println(dummyDataSourceArg.getArgument3());

		System.out.println("-----------Binging proprities -------------");
		GfsConfiguration gfsConfiguration = ctx.getBean(GfsConfiguration.class);
		System.out.println(gfsConfiguration.getArg1());
		System.out.println(gfsConfiguration.getArg2());
		System.out.println(gfsConfiguration.getArg3());



	}

}
