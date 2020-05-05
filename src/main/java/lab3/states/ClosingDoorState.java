package lab3.states;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import lab3.ElevatorController;

import java.util.concurrent.TimeUnit;

public class ClosingDoorState extends ElevatorState {
    Disposable disposable;
    private int interval;
    private int left_interval;

    public ClosingDoorState(ElevatorController controller, int interval) {
        super(controller);
        this.interval = interval;
        this.left_interval = interval;
        disposable = Observable.just(this).delay(1, TimeUnit.SECONDS).map(state -> {
            state.left_interval--;
            return this;
        }).repeatUntil(() -> this.left_interval <= 0).subscribe(state -> {
            controller.setState(new ClosedDoorState(controller));
        });
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    @Override
    public void openDoor() {
        controller.getDoorMotor().stop();
        controller.getDoorMotor().open();
        disposable.dispose();
        controller.setState(new OpeningDoorState(controller, interval));
    }

    @Override
    public void closeDoor() {

    }

    @Override
    public void stopDoor() {
        controller.getDoorMotor().stop();
        controller.getDoorMotor().open();
        disposable.dispose();
        controller.setState(new OpeningDoorState(controller, interval - left_interval));
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
