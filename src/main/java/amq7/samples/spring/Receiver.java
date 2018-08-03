package amq7.samples.spring;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    private ObjectMapper mapper;

    public Receiver() {
    }

    @JmsListener(destination = "${amq.queue}")
    public void receiveMessage(String emailJson) throws JsonParseException, JsonMappingException, IOException {
        final Email email = mapper.readValue(emailJson, Email.class);
        LOGGER.info("Received: {}", email);
    }
}
