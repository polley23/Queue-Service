package com.navi.queueservice.controllers;
import com.navi.queueservice.model.ConsumerRequest;
import com.navi.queueservice.model.QueueNotFoundException;
import com.navi.queueservice.model.QueuePresentException;
import com.navi.queueservice.model.QueueRequest;
import com.navi.queueservice.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class QueueControllers {
    @Autowired
    private QueueService queueService;
    @PostMapping("/v1/queue")
    public ResponseEntity create(@RequestBody QueueRequest queueRequest ){
        try {
            queueService.addQueue(queueRequest.getName());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (QueuePresentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/v1/queue/{queue}/consumer")
    public ResponseEntity create(@RequestBody ConsumerRequest consumerRequest, @PathVariable("queue") String queue){
        try {
            queueService.addConsumer(queue,consumerRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (QueueNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/v1/queue/{queue}")
    public ResponseEntity delete(@PathVariable("queue") String queue){
        queueService.removeQueue(queue);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/v1/queue/{queue}/consumer/{consumer_id}")
    public ResponseEntity deleteConsumer(@PathVariable("queue") String queue,@PathVariable("consumer_id") String consumer){
        queueService.removeConsumer(queue,consumer);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
