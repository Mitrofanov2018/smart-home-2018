package ru.sbt.mipt.oop.EventProcessors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.HomeComponents.SmartHome;

public interface EventProcessor {
    public void processEvent(SmartHome smartHome, SensorEvent event);
}
