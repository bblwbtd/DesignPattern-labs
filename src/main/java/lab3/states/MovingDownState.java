package lab3.states;

import lab3.ElevatorController;

public class MovingDownState extends ElevatorState {

    public MovingDownState(ElevatorController controller) {
        super(controller);
    }

    @Override
    public void openDoor() {

    }

    @Override
    public void closeDoor() {

    }

    @Override
    public void stopDoor() {

    }

    @Override
    public void MoveUp() {

    }

    @Override
    public void MoveDown() {

    }

    @Override
    public void StopMoving() {
        controller.getElevatorMotor().stop();
        controller.setState(new ClosedDoorState(controller));
    }
}
