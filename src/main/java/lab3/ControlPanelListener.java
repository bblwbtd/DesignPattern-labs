package lab3;

import lab3.exceptions.WrongOperationException;

public interface ControlPanelListener {
    void pressFloorButton(int floor) throws WrongOperationException;

    void pressCloseDoorButton();

    void pressOpenDoorButton();
}
