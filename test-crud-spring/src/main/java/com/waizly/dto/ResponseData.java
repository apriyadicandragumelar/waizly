package com.waizly.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponseData<Data> {
    private boolean status;
    private List <String> messages = new ArrayList<>();
    private Data data;

    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public List<String> getMessages() {
        return messages;
    }
    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
    public Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data;
    } 
}

