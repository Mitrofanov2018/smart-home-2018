package ru.sbt.mipt.oop.Remote;

import ru.sbt.mipt.oop.HomeComponents.SmartHome;

public class AlarmDeactivateCommand implements RemoteCommand {
    private final SmartHome smartHome;
    private final String password;

    public AlarmDeactivateCommand(SmartHome smartHome, String password) {
        this.smartHome = smartHome;
        this.password = password;
    }

    @Override
    public void execute() {
        System.out.println("");
        System.out.println("Alarm was deactivate by Remote");
        System.out.println("");
        smartHome.getAlarm().deactivate(password);
    }
}
