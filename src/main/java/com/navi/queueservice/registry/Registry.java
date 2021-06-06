package com.navi.queueservice.registry;
import com.navi.queueservice.model.Context;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Registry<K,V, C extends Context> {
    protected Map<K,V>  map = new ConcurrentHashMap<>();

    public abstract V get(C context);

    public abstract void add(C context,V value);

    public abstract void remove(C context);
}
