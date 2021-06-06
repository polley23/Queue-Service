package com.navi.queueservice.service;
import com.navi.queueservice.factory.Factory;
import com.navi.queueservice.model.*;
import com.navi.queueservice.registry.QueueContext;
import com.navi.queueservice.registry.Registry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueServiceImpl implements QueueService{
    @Autowired
    private Factory<Queue,String> queueFactory;
    @Autowired
    private Factory<Consumer, ConsumerRequest> consumerFactory;
    @Autowired
    private Registry<String,Queue, QueueContext> queueRegistry;
    @Autowired
    private Registry<String,Consumer, ConsumerContext> consumerRegistry;
    @Override
    public void addQueue(final String queueName) throws QueuePresentException {
        Queue queue = queueFactory.get(queueName);
        if(queueRegistry.get(new QueueContext(queue.getName()))!=null){
            throw new QueuePresentException("queue is present");
        }
        queueRegistry.add(new QueueContext(queue.getName()),queue);
    }

    @Override
    public void removeQueue(final String queueName) {
        queueRegistry.remove(new QueueContext(queueName));
    }

    @Override
    public void addConsumer(final String name, final ConsumerRequest consumerRequest) throws QueueNotFoundException {
        Queue queue = queueRegistry.get(new QueueContext(name));
        if(queue==null){
            throw new QueueNotFoundException("queue is not present");
        }
        Consumer consumer = consumerRegistry.get(new ConsumerContext(consumerRequest.getClientId()));
        if(consumer!=null){
            return;
        }
        consumer = consumerFactory.get(consumerRequest);
        queue.getConsumers().add(consumer);
        consumerRegistry.add(new ConsumerContext(consumer.getClientId()),consumer);
        //notify old messages
        queue.notifyConsumer(consumer);
    }

    @Override
    public void removeConsumer(final String name, final String clientId) {
        Queue queue = queueRegistry.get(new QueueContext(name));
        if(queue==null){
            return;
        }
        Consumer consumer = consumerRegistry.get(new ConsumerContext(clientId));
        if(consumer==null){
            return;
        }
        queue.getConsumers().remove(consumer);
        consumerRegistry.remove(new ConsumerContext(clientId));
    }
}
