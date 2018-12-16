package ru.sbt.mipt.oop;


import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable, Printable {
    Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }


    @Override
    public void executeAction(Action action) {
        action.execute(this);
        for(Room room : rooms){
            room.executeAction(action);
        }
    }

    @Override
    public void printToSystem() {
        System.out.println("Smart home consists of:");
        for(Room room : rooms){
            room.printToSystem();
        }
    }

    public void turnOffLights() {
        for (Room homeRoom : getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                SensorCommandExecutor.sendCommand(command);
                System.out.println("Light " + light.getId() + " was turn on.");

            }
        }
    }
}

