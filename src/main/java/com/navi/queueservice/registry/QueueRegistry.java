package com.navi.queueservice.registry;
import com.navi.queueservice.model.Queue;
import org.springframework.stereotype.Component;

@Component
public class QueueRegistry extends Registry<String, Queue, QueueContext> {
    @Override
    public Queue get(final QueueContext context) {
        return map.get(context.getKey());
    }

    @Override
    public void add(final QueueContext context, Queue queue) {
        map.putIfAbsent(context.getKey(),queue);
    }

    @Override
    public void remove(final QueueContext context) {
        if(map.containsKey(context.getKey())){
            map.remove(context.getKey());
        }
    }
}
