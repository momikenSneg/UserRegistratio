package ru.azoft.test.service;

import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.azoft.test.models.User;
import ru.azoft.test.repository.UserRepository;

@EnableRabbit
@Component
public class Receiver {
    private final Logger log;
    private final UserRepository userRepository;

    public Receiver(UserRepository userRepository, Logger log) {
        this.log = log;
        this.userRepository = userRepository;
    }

    @RabbitListener(queues = RabbitConfiguration.SAVE_USER_QUEUE)
    public void saveUser(User user) {
        userRepository.save(user);
        log.info("Received from save queue: " + user);
    }

    @RabbitListener(queues = RabbitConfiguration.GET_USER_QUEUE)
    public User getUser(String id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            log.info("No such user");
        else{
            user.setPassword("");
            log.info("Returned user : " + user);
        }
        return user;
    }
}
