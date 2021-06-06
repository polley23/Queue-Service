package com.navi.queueservice.registry;
import com.navi.queueservice.model.Context;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QueueContext extends Context {
    private String key;
}
