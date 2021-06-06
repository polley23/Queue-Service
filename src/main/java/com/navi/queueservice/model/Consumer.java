package com.navi.queueservice.model;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedDeque;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Consumer {
    protected String clientId= UUID.randomUUID().toString();
    protected Integer batchSize=1;
    protected Integer offset=0;
    protected Integer retry=3;
    @NonNull
    protected String callbackUrl;
    public abstract void notify(Vector<JsonNode> messages);
}
