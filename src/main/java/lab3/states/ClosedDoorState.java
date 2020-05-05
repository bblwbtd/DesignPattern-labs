package lab3.states;

import lab3.ElevatorController;

public class ClosedDoorState extends ElevatorState {

    public ClosedDoorState(ElevatorController controller) {
        super(controller);
    }

    @Override
    public void openDoor() {
        controller.getDoorMotor().open();
        controller.setState(new OpeningDoorState(controller, controller.getConfig().door_speed));
    }

    @Override
    public void closeDoor() {
    }

    @Override
    public void stopDoor() {
    }

    @Override
    public void MoveUp() {
        controller.getElevatorMotor().moveUp();
        controller.setState(new MovingUpState(controller));
    }

    @Override
    public void MoveDown() {
        controller.getElevatorMotor().moveDown();
        controller.setState(new MovingDownState(controller));
    }

    @Override
    public void StopMoving() {
    }
}
