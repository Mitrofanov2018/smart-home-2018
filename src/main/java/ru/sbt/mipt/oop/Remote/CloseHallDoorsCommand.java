package ru.sbt.mipt.oop.Remote;

import ru.sbt.mipt.oop.HomeComponents.Door;
import ru.sbt.mipt.oop.HomeComponents.Room;
import ru.sbt.mipt.oop.HomeComponents.SmartHome;

import java.util.Collection;

public class CloseHallDoorsCommand implements RemoteCommand {
    private final SmartHome smartHome;

    public CloseHallDoorsCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
       Collection<Door> hallDoors = findHallDoors();
       for (Door hallDoor : hallDoors){
           System.out.println("Hall door with id: " +  hallDoor.getId() + " was close by Remote");
           hallDoor.setOpen(false);
       }
    }

    private Collection<Door> findHallDoors() {
        for(Room room : smartHome.getRooms()){
            if(room.getName().equals("hall")){
                return room.getDoors();
            }
        }
        return null;
    }


}
