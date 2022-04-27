package com.example.whatsappgroupapi.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SenderService {

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange direct;

    @Autowired
    private Queue queue;

    public void send(String groupUniqueId , String message) {
        template.convertAndSend(direct.getName(), groupUniqueId, message);
        log.info("Mensaje Enviado: '" + message + "'");
    }

    public void createNewBinding(String groupUniqueId){
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(direct).with(groupUniqueId));
    }
}