package ru.sbt.mipt.oop.Remote;

import ru.sbt.mipt.oop.HomeComponents.Light;
import ru.sbt.mipt.oop.HomeComponents.Room;
import ru.sbt.mipt.oop.HomeComponents.SmartHome;

public class TurnOffAllLights implements RemoteCommand {
    SmartHome smartHome;

    public TurnOffAllLights(SmartHome smartHome){
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        turnOffLights();
    }

    private void turnOffLights() {
        System.out.println("");
        System.out.println("All lights was turn off by Remote");
        System.out.println("");

        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                System.out.println("Light " + light.getId() + " was turn off.");

            }
        }
    }

}
