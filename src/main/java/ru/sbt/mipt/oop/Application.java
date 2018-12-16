package ru.sbt.mipt.oop;

import java.io.IOException;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    private static SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();

    public static void setSmartHomeLoader(SmartHomeLoader smartHomeLoader) {
        Application.smartHomeLoader = smartHomeLoader;
    }


    public static void main(String... args) throws IOException {

        // считываем состояние дома из файла
       setSmartHomeLoader(new FileSmartHomeLoader());

       SmartHome smartHome = smartHomeLoader.loadSmartHome();

       runEventsCycle(smartHome);


    }


    private static void runEventsCycle(SmartHome smartHome) {

        // Получаем событие
        SensorEvent event = RandomSensorEventProvider.getNextSensorEvent();


        // начинаем цикл обработки событий
        while (event != null) {
            System.out.println("Got event: " + event);

            if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
                // событие от источника света
                LightEventProcessor.processLightEvent(smartHome, event);

            }
            if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
                // событие от двери
                DoorEventProccesor.processDoorEvent(smartHome, event);
            }
            event = RandomSensorEventProvider.getNextSensorEvent();
        }
    }


}