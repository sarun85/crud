# Spring Boot - Jersey

This is a simple CRUD project using Spring Boot - Jersey, Hibernate validator, Spring JDBC and H2 Database.

## Annotations

### SpringBootApplication
* **@EnableAutoConfiguration**: enable Spring Boot’s auto-configuration mechanism
* **@ComponentScan**: enable @Component scan on the package where the application is located 
* **@Configuration**: allow to register extra beans in the context or import additional configuration classes  
[more details](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-using-springbootapplication-annotation.html)

### CommandLineRunner and ApplicationRunner

Spring Boot provides two interfaces, CommandLineRunner and ApplicationRunner, to run specific pieces of code when an application is fully started. These interfaces get called just before run() once SpringApplication completes.

### H2 in-memory Database

[H2 Cheatsheet](http://www.h2database.com/html/cheatSheet.html)
`
