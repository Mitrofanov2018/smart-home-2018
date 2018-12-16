package ru.sbt.mipt.oop.HomeComponents;

import java.util.Collection;

public class Room implements Actionable, Printable{
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    @Override
    public void executeAction(Action action) {
        action.execute(this);
        for(Door door : this.getDoors()){
            door.executeAction(action);
        }

        for(Light light : this.getLights()){
            light.executeAction(action);
        }
    }

    @Override
    public void printToSystem() {
        System.out.println("Room " + getName() + " consist of:");
        for(Door door : doors){
            System.out.println("Door " + door.getId() + " is open: " + door.getIsOpen() + ".");
        }

        for(Light light : lights){
            System.out.println("Light " + light.getId() + " is on: " + light.isOn() + ".");
        }

    }
}
