package ru.sbt.mipt.oop.HomeComponents;


import ru.sbt.mipt.oop.AlarmState.AlarmDeactiveState;
import ru.sbt.mipt.oop.CommandType;
import ru.sbt.mipt.oop.SensorCommand;
import ru.sbt.mipt.oop.SensorCommandExecutor;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable, Printable {
    private Collection<Room> rooms;
    private Alarm alarm;

    public Alarm getAlarm(){
        return alarm;
    }

    public SmartHome() {
        rooms = new ArrayList<>();
        alarm = new Alarm();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
        alarm = new Alarm();
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
                System.out.println("Light " + light.getId() + " was turn off.");

            }
        }
    }

    public boolean isAlarmDeactivated(){
        return this.alarm.getState() instanceof AlarmDeactiveState;
    }

    public void activateAlarm(String password){
        this.alarm.activate(password);
    }

    public void deactivateAlarm(String password){
        this.alarm.deactivate(password);
    }
}

