package lab3.states;

import lab3.ElevatorController;

import java.util.List;

public class DoorClosedState extends ElevatorState {

    public DoorClosedState(ElevatorController controller) {
        super(controller);
    }

    @Override
    public void openDoor() {
        controller.getDoorMotor().open();
        controller.setState(new DoorOpeningState(controller));
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
    public void reachedFloor() {

    }

    @Override
    public void doorOpened() {

    }

    @Override
    public void doorClosed() {
        List<Integer> targetFloors = controller.getTargetFloors();
        if (!targetFloors.isEmpty()) {
            Integer integer = targetFloors.get(0);
            if (integer < controller.getCurrentFloor()) {
                controller.setState(new MovingDownState(controller));
                controller.getElevatorMotor().moveDown();
            } else if (integer > controller.getCurrentFloor()) {
                controller.setState(new MovingUpState(controller));
                controller.getElevatorMotor().moveUp();
            } else {
                openDoor();
            }
        }
    }

    @Override
    public void doorBlocked() {

    }

    @Override
    public void pressFloorButton(int floor) {
        List<Integer> floors = controller.getTargetFloors();
        if (floor == controller.getCurrentFloor()) {
            openDoor();
            return;
        }
        if (!floors.contains(floor)) {
            floors.add(floor);
        }
        doorClosed();
    }
}
