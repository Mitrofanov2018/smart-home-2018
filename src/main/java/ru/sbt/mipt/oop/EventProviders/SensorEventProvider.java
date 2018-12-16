package ru.sbt.mipt.oop.EventProviders;

import ru.sbt.mipt.oop.SensorEvent;

public interface SensorEventProvider {
    public SensorEvent getNextSensorEvent();
}
