package ru.azoft.test.snegireva.service.receiver;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.azoft.test.snegireva.models.User;
import ru.azoft.test.snegireva.service.RabbitConfiguration;

@Service
@Qualifier("userReceiver")
public class MessageBrokerUserReceiver implements UserReceiver{

    final private AmqpTemplate template;

    public MessageBrokerUserReceiver(AmqpTemplate template) {
        this.template = template;
    }

    @Override
    public User getUser(String id) {
        User user = (User) template.convertSendAndReceive(RabbitConfiguration.GET_USER_QUEUE, id);
        return user;
    }

}
