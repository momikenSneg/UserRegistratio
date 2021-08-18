package ru.azoft.test.snegireva.service;

import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit //нужно для активации обработки аннотаций @RabbitListener
@Component
public class Receiver {
    private final Logger log;

    public Receiver(Logger log) {
        this.log = log;
    }

    @RabbitListener(queues = "queue1")
    public void processQueue1(String message) {
        log.info("Received from queue 1: " + message);
    }

    @RabbitListener(queues = "query-example-6")
    public String worker1(String message) throws InterruptedException {
        log.info("received on worker : " + message);
        Thread.sleep(3000); //эмулируем полезную работу
        return "received on worker : " + message;
    }
}
