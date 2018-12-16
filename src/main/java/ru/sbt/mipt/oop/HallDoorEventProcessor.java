package ru.sbt.mipt.oop;

import java.io.IOException;
import java.util.Collection;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class HallDoorEventProcessor implements EventProcessor {

/*    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (room.getName().equals("hall")) {
                    for (Room homeRoom : smartHome.getRooms()) {
                        for (Light light : homeRoom.getLights()) {
                            light.setOn(false);
                            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                            SensorCommandExecutor.sendCommand(command);
                        }
                    }
                }
            }
        }
    }
    */

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        smartHome.executeAction(object -> {
            if (!isDoorCloseEvent(event)) return;

            if (object instanceof Door) {
                Door door = (Door) object;
                if (door.getId().equals(event.getObjectId())) {
                    if (isHallDoor(smartHome, door))
                        smartHome.turnOffLights();
                }
            }
        });
    }

    private boolean isHallDoor(SmartHome smartHome, Door door) {
        Collection<Room> rooms = smartHome.getRooms();

        for (Room room : rooms) {
            if (room.getName().equals("hall")) {
                Collection<Door> doors = room.getDoors();
                for (Door door1 : doors) {
                    if (door1.getId().equals(door.getId())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isDoorCloseEvent(SensorEvent event) {
        return (event.getType() == DOOR_CLOSED);
    }


}
