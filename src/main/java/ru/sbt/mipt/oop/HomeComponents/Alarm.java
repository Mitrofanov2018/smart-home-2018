package ru.sbt.mipt.oop.HomeComponents;

import ru.sbt.mipt.oop.AlarmState.AlarmActiveState;
import ru.sbt.mipt.oop.AlarmState.AlarmDeactiveState;
import ru.sbt.mipt.oop.AlarmState.AlarmState;

public class Alarm {
    private String password;
    private AlarmState state;

   public void setPassword(String password) {
        this.password = password;
    }

    public Alarm() {
        this.password = "0000";
        this.state = new AlarmDeactiveState(this);

    }

    public Alarm(String password) {
        this.password = password;
        this.state = new AlarmDeactiveState(this);
    }

    public void changeState(AlarmState state) {
        this.state = state;
    }

    public AlarmState getState() {
        return state;
    }

    public void activate(String password) {
        state.activate(password);
    }

    public void deactivate(String password) {
        state.deactivate(password);
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void startAlarm() {
        state.startAlert();
    }

    public boolean isActivated(){
        return (this.state instanceof AlarmActiveState);
    }
}
