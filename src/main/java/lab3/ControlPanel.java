package lab3;

import lab3.exceptions.WrongOperationException;

import java.util.LinkedList;
import java.util.List;

public class ControlPanel {
    private final List<ControlPanelListener> listeners = new LinkedList<>();

    public void addListener(ControlPanelListener listener) {
        listeners.add(listener);
    }

    public void pressFloorButton(int floor) throws WrongOperationException {
        for (ControlPanelListener listener : listeners) {
            listener.pressFloorButton(floor);
        }
    }

    public void pressCloseButton() {
        for (ControlPanelListener listener : listeners) {
            listener.pressCloseDoorButton();
        }
    }

    public void pressOpenButton() {
        for (ControlPanelListener listener : listeners) {
            listener.pressOpenDoorButton();
        }
    }
}
