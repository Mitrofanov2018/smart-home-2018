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
        if(!isDoorEvent(event)) return;

        if(smartHome.isAlarmDeactivated()) {

            smartHome.executeAction(object -> {

                if (object instanceof Door) {
                    Door door = (Door) object;

                    if (door.getId().equals(event.getObjectId())) {
                        if (event.getType() == DOOR_OPEN) {
                            door.setOpen(true);
                        } else {
                            door.setOpen(false);
                        }
                    }
                }
            });

        } else if(smartHome.getAlarm().getState() instanceof AlarmActiveState) {
            System.out.println("Some one try to change door №: " + event.getObjectId() + "  state, but alarm is active");
            smartHome.getAlarm().startAlarm();
            System.out.println("Sending sms!");

        } else if(smartHome.getAlarm().getState() instanceof AlarmAlertState){
            System.out.println("Some one try to change door №: " + event.getObjectId() + "  state, but alarm is Alert");
            System.out.println("Sending sms!");
        }
    }


    private boolean isDoorEvent(SensorEvent event) {
       return( event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED);
    }
}