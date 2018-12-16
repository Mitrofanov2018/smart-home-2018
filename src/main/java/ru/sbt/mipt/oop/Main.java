package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.HomeComponents.SmartHome;
import ru.sbt.mipt.oop.Loaders.FileSmartHomeLoader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        SmartHome smartHome = new FileSmartHomeLoader().loadSmartHome();
        smartHome.printToSystem();

    }
}
