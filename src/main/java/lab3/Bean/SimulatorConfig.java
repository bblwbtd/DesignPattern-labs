package lab3.Bean;

public class SimulatorConfig {
    public int elevator_speed = 3;
    public int door_speed = 3;
    public int max_floor = 10;

    public int getElevator_speed() {
        return elevator_speed;
    }

    public void setElevator_speed(int elevator_speed) {
        this.elevator_speed = elevator_speed;
    }

    public int getDoor_speed() {
        return door_speed;
    }

    public void setDoor_speed(int door_speed) {
        this.door_speed = door_speed;
    }

    public int getMax_floor() {
        return max_floor;
    }

    public void setMax_floor(int max_floor) {
        this.max_floor = max_floor;
    }
}
