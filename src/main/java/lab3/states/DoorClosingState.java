package lab3.states;

import lab3.ElevatorController;

import java.util.List;

public class DoorClosingState extends ElevatorState {


    public DoorClosingState(ElevatorController controller) {
        super(controller);
    }


    @Override
    public void openDoor() {
        controller.getDoorMotor().stop();
        controller.getDoorMotor().open();
        controller.setState(new DoorOpeningState(controller));
    }

    @Override
    public void closeDoor() {

    }

    @Override
    public void stopDoor() {
        controller.getDoorMotor().stop();
        controller.getDoorMotor().open();
        controller.setState(new DoorOpeningState(controller));
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

    }

    @Override
    public void doorClosed() {
        controller.getDoorMotor().stop();
        controller.setState(new DoorClosedState(controller));
        if (controller.getTargetFloors().size() > 0) {
            controller.getState().doorClosed();
        }
    }

    @Override
    public void pressFloorButton(int floor) {
        List<Integer> floors = controller.getTargetFloors();
        if (controller.getCurrentFloor() == floor) {
            openDoor();
            return;
        }
        if (!floors.contains(floor)) {
            floors.add(floor);
        }
    }
}
