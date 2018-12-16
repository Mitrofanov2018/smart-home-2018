package ru.sbt.mipt.oop.AlarmState;

import ru.sbt.mipt.oop.HomeComponents.Alarm;

public class AlarmAlertState implements AlarmState {
    Alarm alarm;

    public AlarmAlertState(Alarm alarm) {
        this.alarm = alarm;
        System.out.println("Alert!!!");
    }

    @Override
    public void activate(String password) {
        System.out.println("Someone try to activate alarm from alert state");
    }

    @Override
    public void deactivate(String password) {
        if(alarm.checkPassword(password)){
            alarm.changeState(new AlarmDeactiveState(alarm));
        }
        else {
            System.out.println("Alert!!! Wrong password!!!");
        }
    }

    @Override
    public void startAlert() {
        System.out.println("Alarm is already started!");

    }
}
