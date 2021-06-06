package com.navi.queueservice.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConsumerRequest {
    private String clientId;
    private Integer batchSize;
    private String callbackUrl;
}
