package lab3;

import io.reactivex.Single;
import lab3.bean.SimulatorConfig;
import lab3.events.ElevatorEvent;
import lab3.exceptions.WrongOperationException;

import java.util.LinkedList;
import java.util.List;

public class SimulateElevator {
    private final DoorSensor doorSensor = new DoorSensor();
    private final FloorSensor floorSensor = new FloorSensor();
    private final ControlPanel controlPanel = new ControlPanel();
    private final ElevatorMotor elevatorMotor;
    private final DoorMotor doorMotor;
    private final ElevatorController controller;
    private final SimulatorConfig config;
    private final List<SimulateElevatorListener> listenerList = new LinkedList<>();


    public DoorSensor getDoorSensor() {
        return doorSensor;
    }

    public FloorSensor getFloorSensor() {
        return floorSensor;
    }

    public SimulatorConfig getConfig() {
        return config;
    }

    public SimulateElevator(SimulatorConfig config) {
        this.config = config;
        elevatorMotor = new ElevatorMotor(this);
        doorMotor = new DoorMotor(this);
        this.controller = new ElevatorController(elevatorMotor, doorMotor, config);
        doorSensor.addListener(controller);
        floorSensor.addListeners(controller);
        controlPanel.addListener(controller);
    }

    public ElevatorMotor getElevatorMotor() {
        return elevatorMotor;
    }

    public DoorMotor getDoorMotor() {
        return doorMotor;
    }


    public void pressFloorButton(int floor) throws WrongOperationException {
        controlPanel.pressFloorButton(floor);
    }

    public void pressCloseDoorButton() {
        controlPanel.pressCloseButton();
    }

    public void pressOpenDoorButton() {
        controlPanel.pressOpenButton();
    }


    public void simulateDoorBlocked() {
        doorSensor.sendDoorBlockedSignal();
    }

    public ElevatorController getController() {
        return controller;
    }

    public void broadCastEvents(ElevatorEvent event) {
        Single.just(event).doOnSuccess(e -> {
            listenerList.forEach(listener -> listener.Receive(e));
        }).subscribe();
    }

    public void addListener(SimulateElevatorListener listener) {
        listenerList.add(listener);
    }
}
