package lab3.states;

import lab3.ElevatorController;

import java.util.List;

public class MovingUpState extends ElevatorState {

    public MovingUpState(ElevatorController controller) {
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
    public void reachedFloor() {
        controller.setCurrentFloor(controller.getCurrentFloor() + 1);
        List<Integer> targetFloors = controller.getTargetFloors();
        if (targetFloors.contains(controller.getCurrentFloor())) {
            targetFloors.removeIf(integer -> integer.equals(controller.getCurrentFloor()));
            controller.setState(new DoorOpeningState(controller));
            controller.getDoorMotor().open();
            controller.getElevatorMotor().stop();
        }
    }

    @Override
    public void doorOpened() {

    }

    @Override
    public void doorClosed() {

    }

    @Override
    public void pressFloorButton(int floor) {
        List<Integer> floors = controller.getTargetFloors();
        if (!floors.contains(floor)) {
            floors.add(floor);
        }
    }
}
