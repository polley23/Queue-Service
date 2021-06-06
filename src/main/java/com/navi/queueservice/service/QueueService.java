package com.navi.queueservice.service;
import com.navi.queueservice.model.Consumer;
import com.navi.queueservice.model.ConsumerRequest;
import com.navi.queueservice.model.QueueNotFoundException;
import com.navi.queueservice.model.QueuePresentException;

public interface QueueService {
    void addQueue(String queueName) throws QueuePresentException;
    void removeQueue(String queueName);
    void addConsumer(String queue, ConsumerRequest consumer) throws QueueNotFoundException;
    void removeConsumer(String queue, String clientId) ;
}
