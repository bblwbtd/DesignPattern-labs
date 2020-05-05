package lab3.states;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import lab3.ElevatorController;

import java.util.concurrent.TimeUnit;

public class OpeningDoorState extends ElevatorState {
    private final int interval;
    private final Disposable disposable;
    private int left_interval;

    public OpeningDoorState(ElevatorController controller, int interval) {
        super(controller);
        this.interval = interval;
        left_interval = interval;
        disposable = Observable.just(this).delay(1, TimeUnit.SECONDS).map(state -> {
            state.left_interval--;
            return this;
        }).repeatUntil(() -> left_interval <= 0).subscribe(state -> {
            controller.setState(new OpenedDoorState(controller));
        });
    }

    @Override
    public void openDoor() {

    }

    @Override
    public void closeDoor() {
        controller.getDoorMotor().stop();
        controller.getDoorMotor().close();
        disposable.dispose();
        controller.setState(new ClosingDoorState(controller, interval - left_interval));
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
