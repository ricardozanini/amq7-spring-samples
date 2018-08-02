package amq7.samples.spring;

import javax.jms.ConnectionFactory;
import org.apache.qpid.jms.JmsConnectionFactory;
import org.messaginghub.pooled.jms.JmsPoolConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

/**
 * @see <a href="https://spring.io/guides/gs/messaging-jms/">Spring JMS Messaging Guide</a>
 */
@SpringBootApplication
@EnableJms
public class Application {

    @Value("${amq.url}")
    private String connectionUrl;

    @Value("${amq.username}")
    private String user;

    @Value("${amq.password}")
    private String password;

    public Application() {
    }

    @Bean
    public JmsPoolConnectionFactory jmsPoolConnectionFactory() {
        final JmsConnectionFactory jmsConnectionFactory = new JmsConnectionFactory(user, password, connectionUrl);
        final JmsPoolConnectionFactory poolConnectionFactory = new JmsPoolConnectionFactory();
        poolConnectionFactory.setConnectionFactory(jmsConnectionFactory);
        poolConnectionFactory.setUseAnonymousProducers(false);
        return poolConnectionFactory;
    }

    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        // You could still override some of Boot's default if necessary.
        return factory;
    }

    public static void main(String[] args) {
        // Launch the application
        SpringApplication.run(Application.class, args);
    }
}
