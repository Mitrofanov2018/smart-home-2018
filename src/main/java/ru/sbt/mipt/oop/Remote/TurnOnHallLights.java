package ru.sbt.mipt.oop.Remote;

import ru.sbt.mipt.oop.HomeComponents.Light;
import ru.sbt.mipt.oop.HomeComponents.Room;
import ru.sbt.mipt.oop.HomeComponents.SmartHome;

import java.util.Collection;

public class TurnOnHallLights implements RemoteCommand{
    private final SmartHome smartHome;

    public TurnOnHallLights(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        Collection<Light> hallLights = findHallLights();
        for (Light hallDoor : hallLights){
            System.out.println("Hall light with id: " +  hallDoor.getId() + " was turn on by Remote");
            hallDoor.setOn(true);
        }
    }

    private Collection<Light> findHallLights() {
        for(Room room : smartHome.getRooms()){
            if(room.getName().equals("hall")){
                return room.getLights();
            }
        }
        return null;
    }

}
