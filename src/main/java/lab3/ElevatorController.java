package lab3;

import lab3.bean.SimulatorConfig;
import lab3.exceptions.WrongOperationException;
import lab3.states.DoorClosedState;
import lab3.states.ElevatorState;

import java.util.LinkedList;
import java.util.List;

public class ElevatorController implements DoorSensorListener, FloorSensorListener, ControlPanelListener {
    private final ElevatorMotor elevatorMotor;
    private final DoorMotor doorMotor;
    private final SimulatorConfig config;
    private final List<Integer> targetFloors = new LinkedList<>();
    protected int currentFloor = 1;
    private ElevatorState state = new DoorClosedState(this);

    public int getCurrentFloor() {
        return currentFloor;
    }

    public List<Integer> getTargetFloors() {
        return targetFloors;
    }

    public ElevatorController(ElevatorMotor elevatorMotor, DoorMotor doorMotor, SimulatorConfig config) {
        this.elevatorMotor = elevatorMotor;
        this.doorMotor = doorMotor;
        this.config = config;
    }

    public SimulatorConfig getConfig() {
        return config;
    }

    public ElevatorMotor getElevatorMotor() {
        return elevatorMotor;
    }

    public DoorMotor getDoorMotor() {
        return doorMotor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public ElevatorState getState() {
        return state;
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }

    public void moveUp() {
        state.MoveUp();
    }

    public void moveDown() {
        state.MoveDown();
    }

    @Override
    public void doorBlocked() {
        state.doorBlocked();
    }

    @Override
    public void doorOpened() {
        state.doorOpened();
    }

    @Override
    public void doorClosed() {
        state.doorClosed();
    }

    @Override
    public void pressFloorButton(int floor) throws WrongOperationException {
        if (floor > config.max_floor || floor < 1) throw new WrongOperationException();
        state.pressFloorButton(floor);
    }

    @Override
    public void pressCloseDoorButton() {
        state.closeDoor();
    }

    @Override
    public void pressOpenDoorButton() {
        state.openDoor();
    }

    @Override
    public void floorChanged() {
        state.reachedFloor();
    }
}
