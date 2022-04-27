package com.example.whatsappgroupapi.configurations;

import com.example.whatsappgroupapi.services.SenderService;
import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    public static final String EXCHANGE = "whatsapp.direct";
    public static final String QUEUE = "group.queue";

    @Bean
    public DirectExchange direct() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public SenderService sender() {
        return new SenderService();
    }

}