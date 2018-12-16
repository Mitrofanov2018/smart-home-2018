package ru.sbt.mipt.oop.EventProcessors;

import ru.sbt.mipt.oop.AlarmState.AlarmActiveState;
import ru.sbt.mipt.oop.AlarmState.AlarmAlertState;
import ru.sbt.mipt.oop.HomeComponents.Light;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.HomeComponents.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventProcessor implements EventProcessor {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {

        if(smartHome.isAlarmDeactivated()) {

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
        } else if(smartHome.getAlarm().getState() instanceof AlarmActiveState) {
            smartHome.getAlarm().startAlarm();
            System.out.println("Sending sms!");
        } else if(smartHome.getAlarm().getState() instanceof AlarmAlertState){
            System.out.println("Sending sms!");
        }
    }


    private void changeLightState(Light light, boolean isOn, String s) {
        light.setOn(isOn);
        System.out.println("Light " + light.getId() + s);

    }

    private boolean isLightEvent(SensorEvent event) {
        return (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF);
    }

}