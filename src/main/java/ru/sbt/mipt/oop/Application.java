package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.EventProviders.RandomSensorEventProvider;
import ru.sbt.mipt.oop.HomeComponents.SmartHome;
import ru.sbt.mipt.oop.Loaders.FileSmartHomeLoader;
import ru.sbt.mipt.oop.Loaders.SmartHomeLoader;

import java.io.IOException;

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