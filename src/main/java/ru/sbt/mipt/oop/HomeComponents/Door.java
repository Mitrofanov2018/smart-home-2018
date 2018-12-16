package ru.sbt.mipt.oop.HomeComponents;

public class Door implements Actionable {
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setOpen(boolean open) {
        isOpen = open;

        if(open){
            System.out.println("Door №: " + id + " was open.");
        }
        else {
            System.out.println("Door №: " + id + " was close.");
        }

    }

    public boolean getIsOpen() {
        return isOpen;
    }

    @Override
    public void executeAction(Action action) {
        action.execute(this);
    }
}
