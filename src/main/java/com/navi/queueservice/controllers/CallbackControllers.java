package com.navi.queueservice.controllers;
import com.fasterxml.jackson.databind.JsonNode;
import com.navi.queueservice.model.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallbackControllers {

    @PostMapping("/callback")
    public void callback(@RequestBody Message message){
        for(JsonNode jsonNode : message.getMessages()){
            System.out.println(jsonNode);
        }
    }
}
