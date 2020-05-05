package lab3;

public class DoorMotor {

    private final SimulateElevator simulateElevator;

    public DoorMotor(SimulateElevator simulateElevator) {
        this.simulateElevator = simulateElevator;
    }

    public void open() {
        System.out.println("Opening door");
    }

    public void close() {
        System.out.println("Closing door");
    }

    public void stop() {
        System.out.println("Door motor stop");
    }
}
