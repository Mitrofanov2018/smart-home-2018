package ru.sbt.mipt.oop.AlarmState;

public interface AlarmState {
    void activate(String code);
    void deactivate(String code);
    void startAlert();
}
