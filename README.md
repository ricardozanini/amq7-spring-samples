## AMQ7 client with Spring JMS

This is a small sample of how to send and consume messages using [Spring JMS](), [QPID]() and [JMS Pool]() to a Red Hat AMQ7 broker. Use it as a reference for your own implementation.

### Configuration

1. Edit the `src/main/resources/application.yml` file and add your broker credentials to it.

2. Create the queue at your `broker.xml` file:

```xml
<addresses>
  <address name="mailbox">
     <anycast>
         <queue name="mailbox">
             <durable>true</durable>
         </queue>
     </anycast>
   </address>
</addresses>
```

3. Run the application with `mvn spring-boot:run`. The server should start at `8080` port and exposes `/produce` endpoint: http://localhost:8080/produce

4. Hit it and a message should be sent to `mailbox` queue.

You may also use the hawtio console to send messages directly to the queue, use the following format:

```json
{
   "to":"test@test.com",
   "body":"this is my body"
}

```

