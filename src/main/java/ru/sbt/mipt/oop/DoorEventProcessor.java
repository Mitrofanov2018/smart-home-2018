package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {

  /*  @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_OPEN) {
                        door.setOpen(true);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                    } else {
                        door.setOpen(false);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                        if (room.getName().equals("hall")) {
                           new HallDoorEventProcessor().processEvent(smartHome, event);
                        }
                    }
                }
            }
        }
    }
    */


    public void processEvent(SmartHome smartHome, SensorEvent event){
        if(!isDoorEvent(event)) return;

        smartHome.executeAction(object -> {
            if(object instanceof Door){
                Door door = (Door) object;

                if(door.getId().equals(event.getObjectId())){
                    if(event.getType() == DOOR_OPEN){
                        changeDoorState(door, true, " was opened.");
                    }
                    else{
                        changeDoorState(door, false, " was closed.");
                    }
                }
            }
        });

    }

    private void changeDoorState(Door door, boolean opened, String s) {
        door.setOpen(opened);
        System.out.println("Door " + door.getId() +  s);
    }

    private boolean isDoorEvent(SensorEvent event) {
       return( event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED);
    }
}