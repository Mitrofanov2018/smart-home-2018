/* package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.EventProcessors.EventProcessor;
import ru.sbt.mipt.oop.EventProviders.SensorEventProvider;
import ru.sbt.mipt.oop.HomeComponents.SmartHome;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HomeEventsObserverTest {


    public static class OneTimeEventProvider implements SensorEventProvider {

        private SensorEvent event;
        private int callsCount = 0;

        public OneTimeEventProvider(SensorEvent event){
            this.event = event;
        }

        @Override
        public SensorEvent getNextSensorEvent() {
            if(callsCount > 0) return null;
            callsCount ++;
            return event;
        }
    }

    public static class CountingEventProcessor implements EventProcessor {

        private int count = 0;
        private SensorEvent receivedEvent;

        @Override
        public void processEvent(SmartHome smartHome, SensorEvent event) {
            receivedEvent = event;
            count++;
        }

        public SensorEvent getReceivedEvent() {
            return receivedEvent;
        }

        public int getCount() {

            return count;
        }
    }


    @Test
    public void test(){
        SensorEvent sensorEvent = new SensorEvent(SensorEventType.DOOR_OPEN, "1");

        HomeEventsObserver homeEventsObserver = new HomeEventsObserver(
                new OneTimeEventProvider(sensorEvent));

        CountingEventProcessor processor1 = new CountingEventProcessor();
        CountingEventProcessor processor2 = new CountingEventProcessor();

        homeEventsObserver.registerEventProcessor(processor1);
        homeEventsObserver.registerEventProcessor(processor2);
        homeEventsObserver.registerEventProcessor(processor2);
        homeEventsObserver.registerEventProcessor(processor2);

        homeEventsObserver.runEventsCycle(new SmartHome());

        assertEquals(1, processor1.getCount());
        assertEquals(sensorEvent, processor1.getReceivedEvent());

        assertEquals(3, processor2.getCount());
        assertEquals(sensorEvent, processor1.getReceivedEvent());

    }
}

*/