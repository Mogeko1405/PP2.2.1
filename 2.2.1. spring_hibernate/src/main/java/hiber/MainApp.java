package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("AAA", 111);
      Car car2 = new Car("BBB", 222);
      Car car3 = new Car("CCC", 333);
      Car car4 = new Car("DDD", 444);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      User userWithCar1 = userService.getUserOfCar("AAA", 111);
      User userWithCar2 = userService.getUserOfCar("BBB", 222);
      User userWithCar3 = userService.getUserOfCar("CCC", 333);
      User userWithCar4 = userService.getUserOfCar("DDD", 444);

      System.out.println(userWithCar1);
      System.out.println(userWithCar2);
      System.out.println(userWithCar3);
      System.out.println(userWithCar4);

      context.close();
   }
}
