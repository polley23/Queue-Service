package com.navi.queueservice.service;
import com.fasterxml.jackson.databind.JsonNode;
import com.navi.queueservice.model.QueueNotFoundException;

public interface Producer {
    void produce(String queue, JsonNode message) throws QueueNotFoundException;
}
