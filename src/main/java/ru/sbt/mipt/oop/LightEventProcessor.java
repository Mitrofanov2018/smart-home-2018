package ru.sbt.mipt.oop;

import java.awt.*;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventProcessor implements EventProcessor {

  /*  @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    if (event.getType() == LIGHT_ON) {
                        light.setOn(true);
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                    } else {
                        light.setOn(false);
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                    }
                }
            }
        }

    }
    */

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        smartHome.executeAction(object -> {
            if (!isLightEvent(event)) return;

            if (object instanceof Light) {
                Light light = (Light) object;

                if (light.getId().equals(event.getObjectId())) {
                    if (event.getType() == LIGHT_ON) {
                        changeLightState(light, true, " was turned on.");
                    } else {
                        changeLightState(light, false, " was turned off.");
                    }
                }
            }
        });

    }


    private void changeLightState(Light light, boolean isOn, String s) {
        light.setOn(isOn);
        System.out.println("Light " + light.getId() + s);

    }

    private boolean isLightEvent(SensorEvent event) {
        return (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF);
    }

}