package com.sberbank.domclick.system;

import com.sberbank.domclick.model.Data;

public class Application {

    private final ExternalSystem externalSystem = new ExternalSystem();

    public void handleData(Data data) throws InterruptedException {
        synchronized (data.getCode().intern()) {
            externalSystem.process(data);
        }
    }
}