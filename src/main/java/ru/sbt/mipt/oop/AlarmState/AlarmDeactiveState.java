package ru.sbt.mipt.oop.AlarmState;

import ru.sbt.mipt.oop.HomeComponents.Alarm;

public class AlarmDeactiveState implements AlarmState {

    private Alarm alarm;

    public AlarmDeactiveState(Alarm alarm) {
        this.alarm = alarm;
        System.out.println("AlarmState was deactivate.");
    }


    @Override
    public void activate(String password) {
        if(alarm.checkPassword(password)) {
            alarm.changeState(new AlarmActiveState(alarm));
            System.out.println("AlarmState was activate. The SmartHome is under control.");
        }
        else {
            System.out.print("Password is wrong: ");
            startAlert();
        }
    }

    @Override
    public void deactivate(String code) {
        System.out.println("AlarmState is already deactivated.");
    }

    @Override
    public void startAlert() {
        System.out.println("Alert from deactivate state!!!");
        alarm.changeState(new AlarmAlertState(alarm));
    }
}
