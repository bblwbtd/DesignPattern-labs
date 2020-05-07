package lab3;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class DoorMotor {

    private final SimulateElevator simulateElevator;
    private final int interval;
    private int left_interval;
    private Disposable disposable;

    public DoorMotor(SimulateElevator simulateElevator) {
        this.simulateElevator = simulateElevator;
        this.interval = simulateElevator.getConfig().door_speed;
        this.left_interval = 0;
    }

    public void open() {
        System.out.println("Opening door");
        disposable = Observable.just(this).delay(1, TimeUnit.SECONDS).map(m -> {
            left_interval++;
            return this;
        }).repeatUntil(() -> left_interval >= interval).doOnComplete(() -> {
            simulateElevator.getDoorSensor().sendDoorOpenedSignal();
        }).subscribe();
    }

    public void close() {
        System.out.println("Closing door");
        disposable = Observable.just(this).delay(1, TimeUnit.SECONDS).map(m -> {
            left_interval--;
            return this;
        }).repeatUntil(() -> left_interval <= 0).doOnComplete(() -> {
            simulateElevator.getDoorSensor().sendDoorClosedSignal();
        }).subscribe();
    }

    public void stop() {
        System.out.println("Door motor stop");
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
