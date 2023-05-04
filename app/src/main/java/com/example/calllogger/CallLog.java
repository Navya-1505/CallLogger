package com.example.calllogger;

public class CallLog {
    private final String number;
    private final String type;
    private final String date;

    public CallLog(String number, String type, String date) {
        this.number = number;
        this.type = type;
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

}
