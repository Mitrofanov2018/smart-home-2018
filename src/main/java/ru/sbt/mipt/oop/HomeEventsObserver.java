package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public class HomeEventsObserver {

    private final Collection<EventProcessor> eventProcessors = new ArrayList<>();
    private SensorEventProvider sensorEventProvider;

    public HomeEventsObserver(SensorEventProvider sensorEventProvider) {
        this.sensorEventProvider = sensorEventProvider;
    }

    public void registerEventProcessor(EventProcessor eventProcessor){
        eventProcessors.add(eventProcessor);
    }

    public void runEventsCycle(SmartHome smartHome) {

        SensorEvent event = sensorEventProvider.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventProcessor eventProcessor : eventProcessors) {
                eventProcessor.processEvent(smartHome, event);
            }
            event = sensorEventProvider.getNextSensorEvent();
        }
    }

    public void configureEventProcessors(){
       registerEventProcessor(new LightEventProcessor());
       registerEventProcessor(new DoorEventProcessor());
       registerEventProcessor(new HallDoorEventProcessor());
    }

}