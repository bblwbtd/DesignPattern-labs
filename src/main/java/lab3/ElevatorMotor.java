package lab3;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import lab3.events.ElevatorStopEvent;
import lab3.events.MovingDownEvent;
import lab3.events.MovingUpEvent;
import lab3.events.ReachedFloorEvent;

import java.util.concurrent.TimeUnit;

public class ElevatorMotor {

    private final SimulateElevator simulateElevator;
    private Disposable disposable;

    public ElevatorMotor(SimulateElevator elevator) {
        this.simulateElevator = elevator;
    }

    public void moveUp() {
        System.out.println("Elevator move up");
        simulateLightSignal();
        simulateElevator.broadCastEvents(new MovingUpEvent());
    }

    public void moveDown() {
        System.out.println("Elevator move down");
        simulateLightSignal();
        simulateElevator.broadCastEvents(new MovingDownEvent());
    }

    public void stop() {
        System.out.println("Elevator stop");
        if (disposable != null) {
            disposable.dispose();
        }
        simulateElevator.broadCastEvents(new ElevatorStopEvent());
    }

    public void simulateLightSignal() {
        disposable = Observable.just(this).delay(simulateElevator.getConfig().elevator_speed, TimeUnit.SECONDS).map(simulateElevator -> {
            this.simulateElevator.broadCastEvents(new ReachedFloorEvent());
            this.simulateElevator.getFloorSensor().sendFloorChangeSignal();
            return this;
        }).repeatUntil(() -> false).subscribe();
    }
}
