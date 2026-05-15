package com.scalable.enrollment.messaging;

import com.scalable.enrollment.config.RabbitMQConfig;
import com.scalable.enrollment.domain.Enrollment;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentPublisher {

    private final RabbitTemplate rabbitTemplate;

    public EnrollmentPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishConfirmed(Enrollment e) {
        EnrollmentConfirmedEvent event = new EnrollmentConfirmedEvent(
                e.getId(),
                e.getStudentId(),
                e.getSectionId(),
                e.getAmount()
        );

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                event
        );
    }
}