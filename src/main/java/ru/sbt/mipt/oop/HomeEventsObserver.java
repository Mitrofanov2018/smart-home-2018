package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.EventProcessors.*;
import ru.sbt.mipt.oop.EventProviders.RandomSensorEventProvider;
import ru.sbt.mipt.oop.EventProviders.SensorEventProvider;
import ru.sbt.mipt.oop.HomeComponents.SmartHome;

import java.util.ArrayList;
import java.util.Collection;

public class HomeEventsObserver implements EventManager{

    private SmartHome smartHome;
    private final Collection<EventProcessor> eventProcessors = new ArrayList<>();
    private SensorEventProvider sensorEventProvider;

    public HomeEventsObserver(SmartHome smartHome) {
        this.smartHome = smartHome;
        sensorEventProvider = new RandomSensorEventProvider();
    }

    @Override
    public void registerEventProcessor(EventProcessor eventProcessor){
        eventProcessors.add(eventProcessor);
    }

    @Override
    public void runEventsCycle() {

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
       registerEventProcessor(new AlarmEventProcessor());
    }




}
