package com.navi.queueservice.model;
public class QueueNotFoundException extends Exception {
    public QueueNotFoundException(final String msg) {
        super(msg);
    }
}
