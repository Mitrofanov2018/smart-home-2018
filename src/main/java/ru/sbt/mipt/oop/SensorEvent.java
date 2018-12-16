package ru.sbt.mipt.oop;

public class SensorEvent {
    private final SensorEventType type;
    private final String objectId;

    public SensorEvent(SensorEventType type, String objectId) {
        this.type = type;
        this.objectId = objectId;
    }

    public SensorEventType getType() {
        return type;
    }

    public String getObjectId() {
        return objectId;
    }

    @Override
    public String toString() {
        String string;

        switch (type) {
            case ALARM_DEACTIVATE:
                string = "Deactivate alarm";
                break;

            case ALARM_ACTIVATE:
                string = "Activate alarm";
                break;

            case DOOR_CLOSED:
                string = "Close door №: " + getObjectId();
                break;

            case DOOR_OPEN:
                string = "Open door №: " + getObjectId();
                break;

            case LIGHT_OFF:
                string = "Turn off light №: " + getObjectId();
                break;

            case LIGHT_ON:
                string = "Turn off light №: " + getObjectId();
                break;

             default:
                 string = type +  " is unknown sensor type. Please register it! " ;
                 break;

        }

        return string;
    }
}
