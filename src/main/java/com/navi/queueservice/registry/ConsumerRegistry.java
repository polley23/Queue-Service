package com.navi.queueservice.registry;
import com.navi.queueservice.model.Consumer;
import com.navi.queueservice.model.ConsumerContext;
import org.springframework.stereotype.Component;

@Component
public class ConsumerRegistry extends Registry<String, Consumer, ConsumerContext> {
    @Override
    public Consumer get(final ConsumerContext context) {
        return map.get(context.getKey());
    }

    @Override
    public void add(final ConsumerContext context, final Consumer consumer) {
        map.putIfAbsent(context.getKey(),consumer);
    }

    @Override
    public void remove(final ConsumerContext context) {
        if(map.containsKey(context.getKey())){
            map.remove(context.getKey());
        }
    }
}
