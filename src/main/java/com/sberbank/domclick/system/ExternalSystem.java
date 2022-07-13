package com.sberbank.domclick.system;

import com.sberbank.domclick.model.Data;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class ExternalSystem {

    private final Collection<String> executing = ConcurrentHashMap.newKeySet();

    public void process(Data data) throws InterruptedException {
        if (executing.contains(data.getCode())) {
            throw new RuntimeException();
        }
        executing.add(data.getCode());
        Thread.sleep((long) (10 + (1900 * Math.random())));
        executing.remove(data.getCode());
    }
}
