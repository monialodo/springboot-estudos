package academy.devdojo.springbootessentials2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@EnableAutoConfiguration
@ComponentScan
public class Springbootessentials2Application {
    public static void main(String[] args) {

        SpringApplication.run(Springbootessentials2Application.class, args);


    }
}
