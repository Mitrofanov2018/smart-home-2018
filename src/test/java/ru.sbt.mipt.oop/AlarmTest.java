package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.AlarmState.AlarmActiveState;
import ru.sbt.mipt.oop.AlarmState.AlarmAlertState;
import ru.sbt.mipt.oop.AlarmState.AlarmDeactiveState;
import ru.sbt.mipt.oop.HomeComponents.Alarm;

public class AlarmTest {
    Alarm alarm;

    @Test
    public void activateFromDeactiveState() {
        alarm = new Alarm();

        alarm.activate("0000");
        Assert.assertTrue(alarm.getState() instanceof AlarmActiveState);

        alarm.changeState(new AlarmDeactiveState(alarm));
        alarm.activate("12");
        Assert.assertTrue(alarm.getState() instanceof AlarmAlertState);

    }

    @Test
    public void activateFromActiveStateTest() {
        alarm = new Alarm();
        alarm.activate("0000");
        Assert.assertTrue(alarm.getState() instanceof AlarmActiveState);
        alarm.activate("31");
        Assert.assertTrue(alarm.getState() instanceof AlarmAlertState);
        Assert.assertTrue(!alarm.checkPassword("31"));
    }

    @Test
    public void activateFromAlertStateTest() {
        alarm = new Alarm();
        alarm.changeState(new AlarmAlertState(alarm));

        alarm.activate("0000");
        Assert.assertTrue(alarm.getState() instanceof AlarmAlertState);
        alarm.activate("31");
        Assert.assertTrue(alarm.getState() instanceof AlarmAlertState);
        alarm.activate("1");
        Assert.assertTrue(alarm.getState() instanceof AlarmAlertState);
        Assert.assertTrue(!alarm.checkPassword("1"));

    }

    @Test
    public void deactivateFromActiveStateTest() {
        alarm = new Alarm();
        alarm.activate("0000");
        alarm.deactivate("0000");

        Assert.assertTrue(alarm.getState() instanceof AlarmDeactiveState);
        Assert.assertTrue(alarm.checkPassword("0000"));

        alarm.activate("0000");
        alarm.deactivate("12");
        Assert.assertTrue(alarm.getState() instanceof AlarmAlertState);
        Assert.assertTrue(!alarm.checkPassword("12"));

    }

    @Test
    public void deactivateFromDeactiveStateTest() {
        alarm = new Alarm();

        alarm.deactivate("0000");
        Assert.assertTrue(alarm.getState() instanceof AlarmDeactiveState);

        alarm.deactivate("31");
        Assert.assertTrue(alarm.getState() instanceof AlarmDeactiveState);
        Assert.assertTrue(!alarm.checkPassword("31"));
    }

    @Test
    public void deactivateFromAlertStateTest() {
        alarm = new Alarm();

        alarm.activate("0000");
        alarm.deactivate("31");
        Assert.assertTrue(alarm.getState() instanceof AlarmAlertState);

        alarm.deactivate("03");
        Assert.assertTrue(alarm.getState() instanceof AlarmAlertState);

        alarm.deactivate("0000");

        Assert.assertTrue(alarm.getState() instanceof AlarmDeactiveState);
        Assert.assertTrue(!alarm.checkPassword("03"));
    }


}
