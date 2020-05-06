package lab3;

import lab3.bean.SimulatorConfig;
import lab3.exceptions.WrongOperationException;
import lab3.states.*;

public class ElevatorController implements DoorSensorListener, FloorSensorListener, ControlPanelListener {
    private final ElevatorMotor elevatorMotor;
    private final DoorMotor doorMotor;
    private final SimulatorConfig config;
    private ElevatorState state = new ClosedDoorState(this);
    private int currentFloor = 1;
    private int targetFloor = 1;

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

    public void moveDown() throws WrongOperationException {
        state.MoveDown();
    }

    public boolean isMoving() {
        return (state instanceof MovingUpState) || (state instanceof MovingDownState);
    }

    @Override
    public void doorBlocked() {
        state.openDoor();
    }

    @Override
    public void doorOpened() {
        if (state instanceof OpeningDoorState) {
            setState(new OpenedDoorState(this));
            doorMotor.stop();
        }
    }

    @Override
    public void doorClosed() {
        if (state instanceof ClosingDoorState) {
            setState(new ClosedDoorState(this));
            doorMotor.stop();
        }
    }

    @Override
    public void pressFloorButton(int floor) throws WrongOperationException {
        if (floor > config.max_floor || floor < 1) throw new WrongOperationException();
        targetFloor = floor;
        if (currentFloor < floor) {
            moveUp();
        } else if (currentFloor > floor) {
            moveDown();
        } else {
            if ((state instanceof ClosingDoorState) || (state instanceof ClosedDoorState)) {
                setState(new OpeningDoorState(this, config.door_speed));
            }
        }
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
        if (state instanceof MovingUpState) {
            currentFloor++;
        } else if (state instanceof MovingDownState) {
            currentFloor--;
        }
        if (currentFloor == targetFloor) {
            state = new ClosedDoorState(this);
            state.openDoor();
        }
    }
}
