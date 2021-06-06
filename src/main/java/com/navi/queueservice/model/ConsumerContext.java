package com.navi.queueservice.model;
import com.navi.queueservice.model.Context;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConsumerContext extends Context {
    private String key;
}
