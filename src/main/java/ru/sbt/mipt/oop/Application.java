package ru.sbt.mipt.oop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Application {

    private static SmartHomeLoader smartHomeLoader;

    public static void setSmartHomeLoader(SmartHomeLoader smartHomeLoader) {
        Application.smartHomeLoader = smartHomeLoader;
    }


    public static void main(String... args) throws IOException {

        // считываем состояние дома из файла
        setSmartHomeLoader(new FileSmartHomeLoader());

        SmartHome smartHome = smartHomeLoader.loadSmartHome();

        //      smartHome.printToSystem();

        runEventsCycle(smartHome);

    }


    private static void runEventsCycle(SmartHome smartHome) {

        // Получаем событие
        SensorEvent event = RandomSensorEventProvider.getNextSensorEvent();
        Collection<EventProcessor> eventProcessors = configureEventProcessors();

        // начинаем цикл обработки событий
        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventProcessor eventProcessor : eventProcessors) {
                eventProcessor.processEvent(smartHome, event);
            }
            event = RandomSensorEventProvider.getNextSensorEvent();
        }
    }

    private static Collection<EventProcessor> configureEventProcessors() {
        Collection<EventProcessor> eventProcessors = new ArrayList<>();
        eventProcessors.add(new LightEventProcessor());
        eventProcessors.add(new DoorEventProcessor());
        eventProcessors.add(new HallDoorEventProcessor());
        return eventProcessors;
    }
}