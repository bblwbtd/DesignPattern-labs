package lab3;

import lab3.bean.SimulatorConfig;
import lab3.exceptions.WrongOperationException;

public class SimulateElevator {
    private final DoorSensor doorSensor = new DoorSensor();
    private final FloorSensor floorSensor = new FloorSensor();
    private final ControlPanel controlPanel = new ControlPanel();
    private final ElevatorMotor elevatorMotor;
    private final DoorMotor doorMotor;
    private ElevatorController controller;
    private final SimulatorConfig config;


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
    }

    public ElevatorMotor getElevatorMotor() {
        return elevatorMotor;
    }

    public DoorMotor getDoorMotor() {
        return doorMotor;
    }

    public void setController(ElevatorController controller) {
        this.controller = controller;
        doorSensor.addListener(controller);
        floorSensor.addListeners(controller);
        controlPanel.addListener(controller);
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

}
