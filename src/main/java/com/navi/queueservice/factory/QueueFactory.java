package com.navi.queueservice.factory;
import com.navi.queueservice.model.Queue;
import com.navi.queueservice.model.SimpleQueue;
import org.springframework.stereotype.Component;

@Component
public class QueueFactory implements Factory<Queue,String> {
    @Override
    public Queue get(final String name) {
        return new SimpleQueue(name);
    }
}
