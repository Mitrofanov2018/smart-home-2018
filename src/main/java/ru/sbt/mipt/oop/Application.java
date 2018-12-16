package ru.sbt.mipt.oop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Application {

    private static SmartHomeLoader smartHomeLoader;
    private static HomeEventsObserver homeEventsObserver = new HomeEventsObserver(
            new RandomSensorEventProvider());

    public static void setSmartHomeLoader(SmartHomeLoader smartHomeLoader) {
        Application.smartHomeLoader = smartHomeLoader;
    }


    public static void main(String... args) throws IOException {

        setSmartHomeLoader(new FileSmartHomeLoader());

        SmartHome smartHome = smartHomeLoader.loadSmartHome();

        homeEventsObserver.configureEventProcessors();
        homeEventsObserver.runEventsCycle(smartHome);

    }

}