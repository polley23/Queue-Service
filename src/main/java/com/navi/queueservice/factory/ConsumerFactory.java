package com.navi.queueservice.factory;
import com.navi.queueservice.model.Consumer;
import com.navi.queueservice.model.ConsumerRequest;
import com.navi.queueservice.model.SimpleConsumer;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ConsumerFactory implements Factory<Consumer, ConsumerRequest> {
    @Override
    public Consumer get(final ConsumerRequest consumerRequest) {
        SimpleConsumer simpleConsumer = new SimpleConsumer();
        simpleConsumer.setCallbackUrl(consumerRequest.getCallbackUrl());
        if(Objects.nonNull(consumerRequest.getBatchSize())){
            simpleConsumer.setBatchSize(consumerRequest.getBatchSize());
        }
        if(Objects.nonNull(consumerRequest.getClientId())){
            simpleConsumer.setClientId(consumerRequest.getClientId());
        }
        return simpleConsumer;
    }
}
