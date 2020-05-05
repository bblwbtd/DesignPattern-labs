package lab3.states;

import lab3.ElevatorController;

public class OpenedDoorState extends ElevatorState {


    public OpenedDoorState(ElevatorController controller) {
        super(controller);
    }

    @Override
    public void openDoor() {
    }

    @Override
    public void closeDoor() {
        controller.getDoorMotor().close();
        controller.setState(new ClosingDoorState(controller, controller.getConfig().door_speed));
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

    }
}
