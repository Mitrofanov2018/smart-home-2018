package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.RemoteControl.MyRC;
import com.coolcompany.smarthome.RemoteControl.RemoteControl;
import com.coolcompany.smarthome.RemoteControl.RemoteControlRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.sbt.mipt.oop.HomeComponents.SmartHome;
import ru.sbt.mipt.oop.Loaders.FileSmartHomeLoader;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(HomeConfiguration.class);

        EventManager sensorEventsManager = context.getBean(EventManager.class);
        sensorEventsManager.runEventsCycle();


        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("Пульт:");


       // Проверяем пульт
        SmartHome smartHome = new FileSmartHomeLoader().loadSmartHome();

        MyRC remote = new MyRC(smartHome);
        remote.initializateMyRC();

        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControlRegistry.registerRemoteControl(remote, "1");


        RemoteControl remote1 = remoteControlRegistry.getRemoteControl("1");

        remote1.onButtonPressed("B");


    }
}