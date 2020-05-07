package lab3.states;

import lab3.ElevatorController;

public abstract class ElevatorState {
    protected ElevatorController controller;

    public ElevatorState(ElevatorController controller) {
        this.controller = controller;
    }

    public abstract void openDoor();

    public abstract void closeDoor();

    public abstract void stopDoor();

    public abstract void MoveUp();

    public abstract void MoveDown();

    public abstract void reachedFloor();

    public abstract void doorOpened();

    public abstract void doorClosed();

    public abstract void pressFloorButton(int floor);
}
