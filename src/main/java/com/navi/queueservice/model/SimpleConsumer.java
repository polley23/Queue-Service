package com.navi.queueservice.model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Vector;

@AllArgsConstructor
@Data
@Slf4j
public class SimpleConsumer extends Consumer {


    @Override
    public void notify(final Vector<JsonNode> messages) {
        RestTemplate restTemplate = new RestTemplate();
        if(messages==null || messages.size()==0 || getOffset()>=messages.size())
            return;
        Integer numOfMessages= messages.size();
        Integer batch = getBatchSize();
        Integer offset = getOffset();

        while(offset<numOfMessages){
            int loopBatchSize = Math.min(offset+batch,numOfMessages);
            List<JsonNode> messagesToProcess =  messages.subList(offset,loopBatchSize);
            Message message = new Message(messagesToProcess);
            if(sendMessage(restTemplate,message)){
                offset=loopBatchSize;
                this.setOffset(offset);
                log.info("consumer {} offset commited : {} ",clientId,offset);
            }else{
                break;
            }

        }
    }

    private boolean sendMessage(RestTemplate restTemplate, Message message){
        int retryCount = 0;
        ResponseEntity responseEntity;
        while (retryCount<retry) {
            try {
                responseEntity = restTemplate.postForObject(getCallbackUrl(), message, ResponseEntity.class);
                return true;

            } catch (RestClientException e) {
                log.error(e.getMessage());
                retryCount++;
                log.info("retrying for consumer : {} tries : {}",clientId,retryCount);
            }
        }
        return false;
    }
}
