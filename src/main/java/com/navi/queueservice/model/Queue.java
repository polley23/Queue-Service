package com.navi.queueservice.model;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

import java.util.*;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public abstract class Queue {
    protected String id= UUID.randomUUID().toString();
    @NonNull
    protected String name;
    protected Vector<Consumer> consumers= new Vector<>();
    protected Vector<JsonNode> messages=  new Vector<>();
    public void add(JsonNode jsonNode){
        messages.add(jsonNode);
        notifyAllConsumers();
    }
    public abstract void notifyAllConsumers();
    public abstract void notifyConsumer(Consumer consumer);
}
