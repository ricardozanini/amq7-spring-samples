package amq7.samples.spring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
    
    @Autowired
    private ObjectMapper mapper;
    
    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${amq.queue}")
    private String queue;
    
    
    public ProducerController() {
    }

    @RequestMapping(method = RequestMethod.GET, path = "/produce", value = "/produce")
    public String produce(@RequestParam(value = "to", defaultValue = "test@test.com") String to, @RequestParam(value = "body", defaultValue = "test body") String body) throws JmsException, JsonProcessingException {
        final Email email = new Email();
        email.setTo(to);
        email.setBody(body);
        jmsTemplate.convertAndSend(queue, mapper.writeValueAsString(email));

        return "mail sent, check your logs";
    }
}
