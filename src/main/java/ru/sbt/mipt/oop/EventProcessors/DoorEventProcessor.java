package ru.sbt.mipt.oop.EventProcessors;

import ru.sbt.mipt.oop.AlarmState.AlarmActiveState;
import ru.sbt.mipt.oop.AlarmState.AlarmAlertState;
import ru.sbt.mipt.oop.HomeComponents.Door;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.HomeComponents.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {

    public void processEvent(SmartHome smartHome, SensorEvent event){
        if(smartHome.isAlarmDeactivated()) {

            smartHome.executeAction(object -> {
                if(!isDoorEvent(event)) return;

                if (object instanceof Door) {
                    Door door = (Door) object;

                    if (door.getId().equals(event.getObjectId())) {
                        if (event.getType() == DOOR_OPEN) {
                            changeDoorState(door, true, " was opened.");
                        } else {
                            changeDoorState(door, false, " was closed.");
                        }
                    }
                }
            });

        } else if(smartHome.getAlarm().getState() instanceof AlarmActiveState) {
            smartHome.getAlarm().startAlarm();
            System.out.println("Sending sms!");
        } else if(smartHome.getAlarm().getState() instanceof AlarmAlertState){
            System.out.println("Sending sms!");
        }
    }

    private void changeDoorState(Door door, boolean opened, String s) {
        door.setOpen(opened);
        System.out.println("Door " + door.getId() +  s);
    }

    private boolean isDoorEvent(SensorEvent event) {
       return( event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED);
    }
}