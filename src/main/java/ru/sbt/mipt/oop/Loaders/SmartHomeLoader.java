package ru.sbt.mipt.oop.Loaders;

import ru.sbt.mipt.oop.HomeComponents.SmartHome;

import java.io.IOException;

public interface SmartHomeLoader {
        SmartHome loadSmartHome() throws IOException;
    }

