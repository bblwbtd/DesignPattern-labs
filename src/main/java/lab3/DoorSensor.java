package lab3;

import java.util.LinkedList;
import java.util.List;

public class DoorSensor {
    private final List<DoorSensorListener> listeners = new LinkedList<>();

    public void addListener(DoorSensorListener listener) {
        listeners.add(listener);
    }

    public void sendDoorOpenedSignal() {
        System.out.println("the door is opened");
        listeners.forEach(DoorSensorListener::doorOpened);
    }

    public void sendDoorClosedSignal() {
        System.out.println("the door is closed");
        listeners.forEach(DoorSensorListener::doorClosed);
    }

    public void sendDoorBlockedSignal() {
        System.out.println("the door is blocked");
        listeners.forEach(DoorSensorListener::doorBlocked);
    }
}
