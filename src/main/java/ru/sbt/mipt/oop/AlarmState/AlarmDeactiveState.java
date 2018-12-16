package ru.sbt.mipt.oop.AlarmState;

import ru.sbt.mipt.oop.HomeComponents.Alarm;

public class AlarmDeactiveState implements AlarmState {

    private Alarm alarm;

    public AlarmDeactiveState(Alarm alarm) {
        this.alarm = alarm;
        System.out.println("Alarm was deactivate.");
    }


    @Override
    public void activate(String password) {
        if(alarm.checkPassword(password)) {
            alarm.changeState(new AlarmActiveState(alarm));
        }
        else {
            System.out.print("Password is wrong: ");
            startAlert();
        }
    }

    @Override
    public void deactivate(String code) {
        System.out.println("Alarm is already deactivated.");
    }

    @Override
    public void startAlert() {
        alarm.changeState(new AlarmAlertState(alarm));
    }
}
