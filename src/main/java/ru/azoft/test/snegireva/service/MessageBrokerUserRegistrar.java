package ru.azoft.test.snegireva.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.azoft.test.snegireva.models.User;

@Service
@Qualifier("userRegistrar")
public class MessageBrokerUserRegistrar implements UserRegistrar{

    final private AmqpTemplate template;

    public MessageBrokerUserRegistrar(AmqpTemplate template) {
        this.template = template;
    }

    @Override
    public String register(User user){
        template.convertAndSend("queue1", user.getEmail());
        return "Emit to queue";
    }

    public String getMessage(String message){

        return (String) template.convertSendAndReceive("query-example-6",message);
    }
}
