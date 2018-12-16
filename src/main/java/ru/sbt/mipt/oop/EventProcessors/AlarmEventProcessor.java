package ru.sbt.mipt.oop.EventProcessors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.HomeComponents.SmartHome;

public class AlarmEventProcessor implements EventProcessor {
    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if(!isAlarmEvent(event)) return;

        if(event.getType() == SensorEventType.ALARM_ACTIVATE){
            smartHome.activateAlarm(SensorEventType.ALARM_ACTIVATE.getPassword());
        }
        else {
            smartHome.deactivateAlarm(SensorEventType.ALARM_DEACTIVATE.getPassword());
        }
    }

    private boolean isAlarmEvent(SensorEvent event) {
        return event.getType().equals(SensorEventType.ALARM_ACTIVATE)
                || event.getType().equals(SensorEventType.ALARM_DEACTIVATE);
    }
}
