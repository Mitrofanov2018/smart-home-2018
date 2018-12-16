package ru.sbt.mipt.oop.Remote;

import ru.sbt.mipt.oop.HomeComponents.SmartHome;

public class AlarmAlertCommand implements RemoteCommand {
    private final SmartHome smartHome;

    public AlarmAlertCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        System.out.println("");
        System.out.println("Alarm was move to alert state by Remote");
        System.out.println("");
        smartHome.getAlarm().startAlert();
    }
}
