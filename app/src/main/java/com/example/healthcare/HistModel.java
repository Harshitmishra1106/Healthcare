package com.example.healthcare;

public class HistModel {
    private String date;
    private String time;
    private String temperature;
    private String pulse;
    private String oxygen;
    public HistModel(String date,String time,String temperature,String pulse,String oxygen) {
        this.date = date;
        this.time = time;
        this.temperature = temperature;
        this.pulse = pulse;
        this.oxygen = oxygen;
    }

    //<----gettersSetters---->

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getPulse() {
        return pulse;
    }

    public String getOxygen() {
        return oxygen;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public void setOxygen(String oxygen) {
        this.oxygen = oxygen;
    }
}
