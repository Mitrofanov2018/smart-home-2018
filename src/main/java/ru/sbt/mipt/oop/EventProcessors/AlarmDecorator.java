package ru.sbt.mipt.oop.EventProcessors;

import ru.sbt.mipt.oop.AlarmState.AlarmActiveState;
import ru.sbt.mipt.oop.AlarmState.AlarmAlertState;
import ru.sbt.mipt.oop.HomeComponents.SmartHome;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;

public class AlarmDecorator implements EventProcessor {

    private final EventProcessor processor;
    private final SmartHome smartHome;


    public AlarmDecorator(EventProcessor processor, SmartHome smartHome) {
        this.processor = processor;
        this.smartHome = smartHome;
    }



    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {

        if(smartHome.getAlarm().getState() instanceof AlarmActiveState && !isDeacivateEvent(event)){
            smartHome.getAlarm().startAlert();
            return;
        }

        if(smartHome.getAlarm().getState() instanceof AlarmAlertState && !isDeacivateEvent(event) ){
            return;
        }

        processor.processEvent(smartHome, event);
    }


    private boolean isDeacivateEvent(SensorEvent event){
        return event.getType().equals(SensorEventType.ALARM_DEACTIVATE);
    }
}
