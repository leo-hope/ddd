package com.runssnail.ddd.demo.infra.message;

import com.runssnail.ddd.demo.domain.message.MessageQueue;
import org.springframework.stereotype.Component;

@Component
public class MessageQueueImpl implements MessageQueue {

    @Override
    public void push(String message) {

    }
}
