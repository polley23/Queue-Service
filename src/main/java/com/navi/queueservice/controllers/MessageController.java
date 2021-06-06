package com.navi.queueservice.controllers;
import com.fasterxml.jackson.databind.JsonNode;
import com.navi.queueservice.model.QueueNotFoundException;
import com.navi.queueservice.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired
    private Producer producer;

    @PostMapping("/v1/queue/{name}/message")
        public ResponseEntity post(@RequestBody JsonNode message, @PathVariable("name") String queue){
        try {
            producer.produce(queue,message);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (QueueNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
