package lab3;

import io.reactivex.Observable;
import lab3.Bean.SimulatorConfig;
import lab3.exceptions.WrongOperationException;

import java.util.concurrent.TimeUnit;

public class SimulateElevator {
    private final DoorSensor doorSensor = new DoorSensor();
    private final FloorSensor floorSensor = new FloorSensor();
    private final ControlPanel controlPanel = new ControlPanel();
    private final ElevatorMotor elevatorMotor;
    private final DoorMotor doorMotor;
    private final ElevatorController controller;
    private final SimulatorConfig config;

    public SimulateElevator(SimulatorConfig config) {
        this.config = config;
        elevatorMotor = new ElevatorMotor(this);
        doorMotor = new DoorMotor(this);
        controller = new ElevatorController(elevatorMotor, doorMotor, config);
        doorSensor.addListener(controller);
        floorSensor.addListeners(controller);
        controlPanel.addListener(controller);
    }


    public void pressFloorButton(int floor) throws WrongOperationException {
        controlPanel.pressFloorButton(floor);
        simulateLightSignal();
    }

    public void pressCloseDoorButton() {
        controlPanel.pressCloseButton();
    }

    public void pressOpenDoorButton() {
        controlPanel.pressOpenButton();
    }

    public void simulateLightSignal() {
        Observable.just(this).delay(config.elevator_speed, TimeUnit.SECONDS).map(simulateElevator -> {
            if (controller.isMoving()) {
                simulateElevator.getFloorSensor().sendFloorChangeSignal();
            }
            return this;
        }).repeatUntil(() -> !this.getController().isMoving()).subscribe();
    }

    public void simulateDoorBlocked() {
        doorSensor.sendDoorBlockedSignal();
    }

    public DoorSensor getDoorSensor() {
        return doorSensor;
    }

    public FloorSensor getFloorSensor() {
        return floorSensor;
    }

    public ElevatorMotor getElevatorMotor() {
        return elevatorMotor;
    }

    public DoorMotor getDoorMotor() {
        return doorMotor;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public ElevatorController getController() {
        return controller;
    }

}
