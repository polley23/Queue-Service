package com.navi.queueservice.model;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimpleQueue extends Queue{
    public SimpleQueue(final String name) {
        super(name);
    }
    @Override
    public void notifyAllConsumers() {
        for(Consumer consumer: getConsumers()){
            consumer.notify(getMessages());
        }
    }

    @Override
    public void notifyConsumer(final Consumer consumer) {
        consumer.notify(getMessages());
    }
}
