package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.RemoteControl.MyRC;
import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.AlarmState.AlarmActiveState;
import ru.sbt.mipt.oop.AlarmState.AlarmAlertState;
import ru.sbt.mipt.oop.AlarmState.AlarmDeactiveState;
import ru.sbt.mipt.oop.HomeComponents.Door;
import ru.sbt.mipt.oop.HomeComponents.Light;
import ru.sbt.mipt.oop.HomeComponents.Room;
import ru.sbt.mipt.oop.HomeComponents.SmartHome;
import ru.sbt.mipt.oop.Loaders.FileSmartHomeLoader;

import java.io.IOException;

public class RemoteTest {

    /*
        codesOfCommand.put("1", new AlarmActivateCommand(smartHome, "0000"));
        codesOfCommand.put("2", new AlarmDeactivateCommand(smartHome, "0000"));
        codesOfCommand.put("3", new AlarmAlertCommand(smartHome));
        codesOfCommand.put("A", new TurnOffAllLights(smartHome));
        codesOfCommand.put("B", new TurnOnAllLights(smartHome));
        codesOfCommand.put("C", new TurnOnHallLights(smartHome));
        codesOfCommand.put("D", new CloseHallDoorsCommand(smartHome));

     */

    SmartHome smartHome = new FileSmartHomeLoader().loadSmartHome();

    public RemoteTest() throws IOException {
    }

    MyRC remote = new MyRC(smartHome);

    @Test
    public void checkingOne() {
        remote.initializateMyRC();
        remote.onButtonPressed("1");
        Assert.assertTrue(smartHome.getAlarm().getState() instanceof AlarmActiveState);
    }

    @Test
    public void checkingTwo() {
        remote.initializateMyRC();
        remote.onButtonPressed("2");
        Assert.assertTrue(smartHome.getAlarm().getState() instanceof AlarmDeactiveState);
    }

    @Test
    public void checkingThree() {
        remote.initializateMyRC();
        remote.onButtonPressed("3");
        Assert.assertTrue(smartHome.getAlarm().getState() instanceof AlarmAlertState);
    }

    @Test
    public void checkingA() {
        remote.initializateMyRC();
        remote.onButtonPressed("A");
        int countOnLights = 0;

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.isOn()) {
                    countOnLights++;
                }
            }
        }

        Assert.assertEquals(countOnLights, 0);
    }

    @Test
    public void checkingB() {
        remote.initializateMyRC();
        remote.onButtonPressed("B");
        int countOffLights = 0;

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (!light.isOn()) {
                    countOffLights++;
                }
            }
        }

        Assert.assertEquals(countOffLights, 0);
    }

    @Test
    public void checkingC() {
        remote.initializateMyRC();
        remote.onButtonPressed("C");
        int countHallOffLights = 0;

        for (Room room : smartHome.getRooms()) {
            if (room.getName().equals("hall")) {
                for (Light light : room.getLights()) {
                    if (!light.isOn()) {
                        countHallOffLights++;
                    }
                }
            }
        }

        Assert.assertEquals(countHallOffLights, 0);
    }

    @Test
    public void checkingD() {
        remote.initializateMyRC();
        remote.onButtonPressed("D");
        int countHallOpenDoor = 0;

        for (Room room : smartHome.getRooms()) {
            if (room.getName().equals("hall")) {
                for (Door door : room.getDoors()) {
                    if (door.getIsOpen()) {
                        countHallOpenDoor ++;
                    }
                }
            }
        }

        Assert.assertEquals(countHallOpenDoor, 0);
    }

}
