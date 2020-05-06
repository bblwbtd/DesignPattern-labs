package lab3;

import java.util.LinkedList;
import java.util.List;

public class FloorSensor {
    private final List<FloorSensorListener> listeners = new LinkedList<>();

    public void addListeners(FloorSensorListener listener) {
        listeners.add(listener);
    }

    public void sendFloorChangeSignal() {
        System.out.println("the floor has changed");
        for (FloorSensorListener listener : listeners) {
            listener.floorChanged();
        }
    }
}
