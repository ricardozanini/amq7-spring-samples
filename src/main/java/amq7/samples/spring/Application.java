package amq7.samples.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * @see <a href="https://spring.io/guides/gs/messaging-jms/">Spring JMS Messaging Guide</a>
 */
@SpringBootApplication
@EnableJms
public class Application {

    public Application() {
    }

    public static void main(String[] args) {
        // Launch the application
        SpringApplication.run(Application.class, args);
    }
}
