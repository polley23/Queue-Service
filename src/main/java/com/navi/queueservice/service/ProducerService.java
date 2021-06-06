package com.navi.queueservice.service;
import com.fasterxml.jackson.databind.JsonNode;
import com.navi.queueservice.model.Queue;
import com.navi.queueservice.model.QueueNotFoundException;
import com.navi.queueservice.registry.QueueContext;
import com.navi.queueservice.registry.Registry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerService implements Producer{
    @Autowired
    private Registry<String, Queue, QueueContext> queueRegistry;
    @Override
    public void produce(final String name, final JsonNode message) throws QueueNotFoundException {
        Queue queue = queueRegistry.get(new QueueContext(name));
        if(queue==null){
            throw new QueueNotFoundException("queue is not found");
        }
        queue.add(message);
    }
}
