package ru.azoft.test.snegireva.service.registrar;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.azoft.test.snegireva.models.User;
import ru.azoft.test.snegireva.service.RabbitConfiguration;

import java.sql.Date;
import java.util.UUID;

@Service
@Qualifier("userRegistrar")
public class MessageBrokerUserRegistrar implements UserRegistrar {

    final private AmqpTemplate template;

    public MessageBrokerUserRegistrar(AmqpTemplate template) {
        this.template = template;
    }

    @Override
    public String register(User user){
        user.setID(UUID.randomUUID().toString());
        user.setRegistrationDate(new Date(System.currentTimeMillis()));
        template.convertAndSend(RabbitConfiguration.SAVE_USER_QUEUE, user);
        return user.getID();
    }
}
