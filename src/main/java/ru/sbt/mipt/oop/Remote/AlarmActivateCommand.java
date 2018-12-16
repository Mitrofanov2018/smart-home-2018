package ru.sbt.mipt.oop.Remote;

import ru.sbt.mipt.oop.HomeComponents.SmartHome;

public class AlarmActivateCommand implements RemoteCommand {
    private final SmartHome smartHome;
    private final String password;

    public AlarmActivateCommand(SmartHome smartHome, String password) {
        this.smartHome = smartHome;
        this.password = password;
    }

    @Override
    public void execute() {
        System.out.println("Alarm was activate by Remote");
        smartHome.getAlarm().activate(password);
    }
}
