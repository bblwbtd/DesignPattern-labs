package lab3;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class ElevatorMotor {

    private final SimulateElevator elevator;
    private Disposable disposable;

    public ElevatorMotor(SimulateElevator elevator) {
        this.elevator = elevator;
    }

    public void moveUp() {
        System.out.println("Elevator move up");
        simulateLightSignal();
    }

    public void moveDown() {
        System.out.println("Elevator move down");
        simulateLightSignal();
    }

    public void stop() {
        System.out.println("Elevator stop");
        if (disposable != null) {
            disposable.dispose();
        }
    }

    public void simulateLightSignal() {
        disposable = Observable.just(this).delay(elevator.getConfig().elevator_speed, TimeUnit.SECONDS).map(simulateElevator -> {
            elevator.getFloorSensor().sendFloorChangeSignal();
            return this;
        }).repeatUntil(() -> false).subscribe();
    }

}
