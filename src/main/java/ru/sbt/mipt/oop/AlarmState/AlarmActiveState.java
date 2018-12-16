package ru.sbt.mipt.oop.AlarmState;

import ru.sbt.mipt.oop.HomeComponents.Alarm;

public class AlarmActiveState implements AlarmState {
    Alarm alarm;

    public AlarmActiveState(Alarm alarm) {
        this.alarm = alarm;
        System.out.println("AlarmState activated.");
    }

    @Override
    public void activate(String password) {
        if(this.alarm.checkPassword(password)) {
            System.out.println("AlarmState is already active!");
        } else {
            System.out.print("Wrong password to activate:  ");
            startAlert();
        }
    }

    @Override
    public void deactivate(String password) {
       if( alarm.checkPassword(password)){
           System.out.println("AlarmState successfully deactivated!");
           alarm.changeState(new AlarmDeactiveState(alarm));
       }
       else {
           System.out.print("Wrong password: ");
           startAlert();
       }
    }

    @Override
    public void startAlert() {
        System.out.println("Alert from active state!!!");
        alarm.changeState(new AlarmAlertState(alarm));
    }
}
