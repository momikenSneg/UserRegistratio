package ru.azoft.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;
import ru.azoft.test.models.User;
import ru.azoft.test.service.registrar.MessageBrokerUserRegistrar;

import java.sql.Date;

@SpringBootTest(classes = {MessageBrokerUserRegistrar.class})
public class MessageBrokerUserRegistrarTest {
    @MockBean
    private AmqpTemplate template;
    @Autowired
    MessageBrokerUserRegistrar registrar;

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
        String id = registrar.register(user);
        Assert.notNull(id, "Returned id is null");
        Assert.notNull(user.getID(), "Returned user id is null");
        Assert.notNull(user.getRegistrationDate(), "Returned user id is null");

        Assert.isTrue(id.equals(user.getID()), "Returned id must be equals to user id");
    }

    @Test
    public void testNullUser(){
        user = null;
        boolean cached = false;
        try {
            registrar.register(user);
        } catch (NullPointerException e){
            cached = true;
        }
        Assert.isTrue(cached, "Exception must be thrown");
    }
}
