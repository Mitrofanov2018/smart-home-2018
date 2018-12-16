package ru.sbt.mipt.oop;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class DoorEventProcessorTest {

    private static SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();


    @Test
    public void testDoorprocessEvent() throws IOException {


        SmartHome smartHome = smartHomeLoader.loadSmartHome(); // загружаем умный дом

        String doorId1 = "4";

        SensorEvent event1 = new SensorEvent(SensorEventType.DOOR_OPEN, doorId1);

        SensorEvent event2 = new SensorEvent(SensorEventType.DOOR_CLOSED, doorId1);



        if (getDoorById(smartHome, doorId1).getIsOpen()) {  // если дверь открыта, то закроем ее и проверим закралась ли

            new DoorEventProcessor().processEvent(smartHome, event2);

            assertTrue(!getDoorById(smartHome, doorId1).getIsOpen());

        } else {                    // если дверь закрыта, то откроем ее и посмотрим открылась ли
            new DoorEventProcessor().processEvent(smartHome, event1);

            assertTrue(getDoorById(smartHome, doorId1).getIsOpen());
        }

    }



    public static Door getDoorById(SmartHome smartHome, String Id) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(Id)) {
                    return door;
                }
            }
        }
        return null;

    }


}
