package ru.azoft.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;
import ru.azoft.test.models.User;
import ru.azoft.test.service.RabbitConfiguration;
import ru.azoft.test.service.receiver.MessageBrokerUserReceiver;
import ru.azoft.test.service.registrar.MessageBrokerUserRegistrar;

import java.sql.Date;

@SpringBootTest(classes = {MessageBrokerUserReceiver.class})
public class MessageBrokerUserReceiverTest {
    @MockBean
    private AmqpTemplate template;
    @Autowired
    MessageBrokerUserReceiver receiver;

    User user;

    @BeforeEach
    public void init(){
        user = new User("id",
                "login",
                "password",
                "Name",
                "Surname",
                "email@email.com",
                new Date(System.currentTimeMillis()));
    }

    @Test
    public void testRightUser(){
        Mockito.when(template.convertSendAndReceive(RabbitConfiguration.GET_USER_QUEUE, "id")).thenReturn(user);
        User user = receiver.getUser("id");

        Assert.isTrue(user.equals(this.user), "Wrong data in user");
    }
}
