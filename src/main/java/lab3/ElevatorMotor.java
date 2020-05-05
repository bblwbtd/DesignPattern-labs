package lab3;

public class ElevatorMotor {

    private final SimulateElevator elevator;

    public ElevatorMotor(SimulateElevator elevator) {
        this.elevator = elevator;
    }

    public void moveUp() {
        System.out.println("Elevator move up");
    }

    public void moveDown() {
        System.out.println("Elevator move down");
    }

    public void stop() {
        System.out.println("Elevator stop");
    }
}
