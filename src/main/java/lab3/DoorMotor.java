package lab3;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import lab3.events.ClosingDoorEvent;
import lab3.events.DoorClosedEvent;
import lab3.events.DoorOpenedEvent;
import lab3.events.OpeningDoorEvent;

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
        simulateElevator.broadCastEvents(new OpeningDoorEvent());
        disposable = Observable.just(this).delay(1, TimeUnit.SECONDS).map(m -> {
            left_interval++;
            return this;
        }).repeatUntil(() -> left_interval >= interval).doOnComplete(() -> {
            simulateElevator.getDoorSensor().sendDoorOpenedSignal();
            simulateElevator.broadCastEvents(new DoorOpenedEvent());
        }).subscribe();
    }

    public void close() {
        System.out.println("Closing door");
        simulateElevator.broadCastEvents(new ClosingDoorEvent());
        disposable = Observable.just(this).delay(1, TimeUnit.SECONDS).map(m -> {
            left_interval--;
            return this;
        }).repeatUntil(() -> left_interval <= 0).doOnComplete(() -> {
            simulateElevator.getDoorSensor().sendDoorClosedSignal();
            simulateElevator.broadCastEvents(new DoorClosedEvent());
        }).subscribe();
    }

    public void stop() {
        System.out.println("Door motor stop");
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
