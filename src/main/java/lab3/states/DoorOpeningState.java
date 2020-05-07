package lab3.states;

import lab3.ElevatorController;

import java.util.List;

public class DoorOpeningState extends ElevatorState {


    public DoorOpeningState(ElevatorController controller) {
        super(controller);
    }

    @Override
    public void openDoor() {

    }

    @Override
    public void closeDoor() {
        controller.getDoorMotor().stop();
        controller.getDoorMotor().close();
        controller.setState(new DoorClosingState(controller));
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

    }

    @Override
    public void doorOpened() {
        controller.getDoorMotor().stop();
        controller.setState(new DoorOpenedState(controller));
    }

    @Override
    public void doorClosed() {

    }

    @Override
    public void pressFloorButton(int floor) {
        List<Integer> floors = controller.getTargetFloors();
        if (floor == controller.getCurrentFloor()) {
            return;
        }
        if (!floors.contains(floor)) {
            floors.add(floor);
        }
    }
}
