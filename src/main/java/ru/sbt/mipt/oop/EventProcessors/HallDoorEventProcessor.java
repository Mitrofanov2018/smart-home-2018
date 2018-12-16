package ru.sbt.mipt.oop.EventProcessors;

import ru.sbt.mipt.oop.AlarmState.AlarmActiveState;
import ru.sbt.mipt.oop.AlarmState.AlarmAlertState;
import ru.sbt.mipt.oop.HomeComponents.Door;
import ru.sbt.mipt.oop.HomeComponents.Light;
import ru.sbt.mipt.oop.HomeComponents.Room;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.HomeComponents.SmartHome;

import java.util.Collection;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class HallDoorEventProcessor implements EventProcessor {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (!isDoorCloseEvent(event)) return;

        if(smartHome.isAlarmDeactivated()) {
            smartHome.executeAction(object -> {
                if (object instanceof Door) {
                    Door door = (Door) object;
                    if (door.getId().equals(event.getObjectId())) {
                        if (isHallDoor(smartHome, door)) {
                            System.out.println("Hall door closed, turning off all lights in the SmartHome:");
                            turnOffLights(smartHome);
                        }
                    }
                }
            });
        }
    }

    public void turnOffLights(SmartHome smartHome) {
        for (Room homeRoom :  smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
            }
        }
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
