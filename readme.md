# restful API example:
1. create the project: 
   - Add the H2 Database, Spring Web Starter, and the Spring Data JPA dependencies before generating the project.
2. set up H2 in-memory database:
    - Go to application.properties Enable the console, add a path for the console, and create a url for the datasource using H2.
after set up, you can open the localhost:8082/h2 to open h2 console.
## Dog API 
1. create the dog API: [Dog.java](./src/main/java/com/udacity/RestAPIexample/entity/Dog.java)
   - @Entity:
   - @Id: as a primary key.
2. create a web controller using @RestController  [DogController.java](./src/main/java/com/udacity/RestAPIexample/web/DogController.java);
3. Step 1: Create a repository that extends CrudRepository for creating, reading, updating, and deleting Dog objects.
   - [DogRepository.java](./src/main/java/com/udacity/RestAPIexample/repository/DogRepository.java);
   - findBreedById;
   - findAllBreed;
   - findAllName;
   - use @Query annotation in spring JPA to execute both JPQL and native sql queries
4. Step 2: Create a dog service that performs the following operations:
   - retrieveDogs 
   - retrieveDogBreeds
   - retrieveDogBreedById
   - retrieveDogNames
   1. create an interface called [DogService](./src/main/java/com/udacity/RestAPIexample/service/DogService.java),
   2. create the implement of dog service [DogServiceImpl](./src/main/java/com/udacity/RestAPIexample/service/DogServiceImpl.java);
5. Step 3: Update the web controller using @RestController that handles requests for retrieving:
   - [DogController](./src/main/java/com/udacity/RestAPIexample/web/DogController.java)
   - a list of Dog breeds
   - a list of Dog breeds by Id
   - a list of Dog names
   - @RestController; @Autowired; @ResponseEntity<List<Dog>>； @PathVariable
6. Step 4: Make sure errors are handled appropriately.
7. Step 5: Create a data.sql file to create sample dog data in the database.
   - [data.sql](./src/main/resources/data.sql)
   - insert some initial data into the database
   - why not create the dog table?: [application.properties](./src/main/resources/application.properties)
8. Step 6: Check that you are able to access your API.
   ![img.png](img.png)


# problem fix:
## fix plugin not found problem:
[java - Plugin 'org.springframework.boot:spring-boot-maven-plugin:' not found - Stack Overflow](https://stackoverflow.com/questions/64639836/plugin-org-springframework-bootspring-boot-maven-plugin-not-found)
```xml
<version>${project.parent.version}</version>
```
## why we need service interface?
[为什么dao层和service层要用接口？ - 简书](https://www.jianshu.com/p/64abdd29bdf6)
1. 可以在尚未实现具体Service情况下编写上层改代码,如Controller对Service的调用
2. Spring无论是AOP还是事务管理的实现都是基于动态代理的,而动态代理的实现依赖于接口,所以必须有接口的定义才能使用这些功能
3. 可以对Service进行多实现

## @Service是标记在接口上还是实现类上？
@Service注解是标注在实现类上的，因为@Service是把spring容器中的bean进行实例化，也就是等同于new操作，
只有实现类是可以进行new实例化的，而接口则不能，所以是加在实现类上的。

## .sql 相关：
名称固定：
1. 数据全部放在data.sql中
2. 创建数据表放在schema.sql中

## table Dog not found:
reference: https://stackoverflow.com/questions/67695069/spring-boot-datasource-initialization-error-with-data-sql-script-after-2-5-0-upg
By default, data.sql scripts are now run before Hibernate is initialized.
This aligns the behavior of basic script-based initialization with that of Flyway and Liquibase.
If you want to use data.sql to populate a schema created by Hibernate,
set spring.jpa.defer-datasource-initialization to true.
While mixing database initialization technologies is not recommended,
this will also allow you to use a schema.sql script to build upon a Hibernate-created schema before it’s populated via data.sql.