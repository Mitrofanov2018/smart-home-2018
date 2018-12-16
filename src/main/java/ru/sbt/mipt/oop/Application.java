package ru.sbt.mipt.oop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.sbt.mipt.oop.HomeComponents.SmartHome;

public class Application {

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(HomeConfiguration.class);
        EventManager sensorEventsManager = context.getBean(EventManager.class);
        sensorEventsManager.runEventsCycle();
    }
}