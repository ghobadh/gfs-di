package ca.gforcesoftware.gfsdi;

import ca.gforcesoftware.gfsdi.controllers.ConstructorInjectController;
import ca.gforcesoftware.gfsdi.controllers.MyController;
import ca.gforcesoftware.gfsdi.controllers.PropertyInjectController;
import ca.gforcesoftware.gfsdi.controllers.SetterInjectedController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

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


	}

}
