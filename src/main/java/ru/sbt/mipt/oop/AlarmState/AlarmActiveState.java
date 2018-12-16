package ru.sbt.mipt.oop.AlarmState;

import ru.sbt.mipt.oop.HomeComponents.Alarm;

public class AlarmActiveState implements AlarmState {
    Alarm alarm;

    public AlarmActiveState(Alarm alarm) {
        this.alarm = alarm;
        System.out.println("Alarm activated.");
    }

    @Override
    public void activate(String password) {
        if(this.alarm.checkPassword(password)) {
            System.out.println("Alarm is already active!");
        } else {
            System.out.print("Wrong password to activate:  ");
            startAlert();
        }
    }

    @Override
    public void deactivate(String password) {
       if( alarm.checkPassword(password)){
           alarm.changeState(new AlarmDeactiveState(alarm));
       }
       else {
           System.out.print("Wrong password: ");
           startAlert();
       }
    }

    @Override
    public void startAlert() {
        alarm.changeState(new AlarmAlertState(alarm));
    }
}
