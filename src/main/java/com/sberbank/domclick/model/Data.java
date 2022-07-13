package com.sberbank.domclick.model;

public class Data {
    private final String code;
    private final String jsonData;

    public Data(String code, String jsonData) {
        this.code = code;
        this.jsonData = jsonData;
    }

    public String getCode() {
        return code;
    }

    public boolean equals(Data data) {
        return this.code.equals(data.code);
    }
}
