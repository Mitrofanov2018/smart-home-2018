package ru.sbt.mipt.oop;

public enum SensorEventType {
    LIGHT_ON(""), LIGHT_OFF(""), DOOR_OPEN(""), DOOR_CLOSED(""), ALARM_ACTIVATE("0000"), ALARM_DEACTIVATE("0000");
    private final String password;

    SensorEventType(String password){
        this.password = password;
    }

    public String getPassword(){return password;}
}
