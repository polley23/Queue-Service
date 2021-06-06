package com.navi.queueservice.factory;
public interface Factory<T,S> {
    T get(S arg);
}
