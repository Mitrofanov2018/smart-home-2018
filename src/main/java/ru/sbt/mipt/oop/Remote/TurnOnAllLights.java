package ru.sbt.mipt.oop.Remote;

import ru.sbt.mipt.oop.HomeComponents.Light;
import ru.sbt.mipt.oop.HomeComponents.Room;
import ru.sbt.mipt.oop.HomeComponents.SmartHome;

public class TurnOnAllLights implements RemoteCommand {
    SmartHome smartHome;

    public TurnOnAllLights(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        turnOnLights();
    }

    public void turnOnLights() {
        System.out.println("All lights was turn on by remote");

        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(true);
                System.out.println("Light " + light.getId() + " was turn off.");

            }
        }
    }
}
