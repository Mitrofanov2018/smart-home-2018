package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.EventProcessors.HallDoorEventProcessor;
import ru.sbt.mipt.oop.HomeComponents.Door;
import ru.sbt.mipt.oop.HomeComponents.Light;
import ru.sbt.mipt.oop.HomeComponents.Room;
import ru.sbt.mipt.oop.HomeComponents.SmartHome;
import ru.sbt.mipt.oop.Loaders.FileSmartHomeLoader;
import ru.sbt.mipt.oop.Loaders.SmartHomeLoader;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HallDoorsEventProcessorTest {

    private static SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();


    @Test
    public void testHallDoorsprocessEvent() throws IOException {


        SmartHome smartHome = smartHomeLoader.loadSmartHome(); // загружаем умный дом

        String doorId = hallDoorId(smartHome).getId();

        System.out.println("Hall door id is: " + doorId );

        SensorEvent event1 = new SensorEvent(SensorEventType.DOOR_CLOSED, doorId);


        new HallDoorEventProcessor().processEvent(smartHome, event1);


        assertEquals( 0, numberOfOnLigths( smartHome ) );


    }


    public static Door hallDoorId(SmartHome smartHome) {
        for (Room room : smartHome.getRooms()) {
            if (room.getName().equals("hall")) {
                return room.getDoors().iterator().next();
            }


        }

        return null;
    }


    public int numberOfOnLigths(SmartHome smartHome){
        int counLightsIsOn = 0;

        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                if(light.isOn()){
                    counLightsIsOn ++;
                }
            }

        }

        return counLightsIsOn;
    }

}
