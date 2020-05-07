package lab3.states;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import lab3.ElevatorController;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DoorOpenedState extends ElevatorState {

    Disposable disposable;

    public DoorOpenedState(ElevatorController controller) {
        super(controller);
        autoClose();
    }

    private void autoClose() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        disposable = Observable
                .just(controller)
                .delay(controller.getConfig().door_max_idle_time, TimeUnit.SECONDS)
                .subscribe(c -> closeDoor());
    }

    /**
     * If the door is opened, press open door button will fresh the door waiting time.
     */
    @Override
    public void openDoor() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        doorOpened();
    }

    @Override
    public void closeDoor() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
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

    }

    @Override
    public void doorClosed() {

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
    }
}
