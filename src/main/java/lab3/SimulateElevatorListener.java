package lab3;

import lab3.events.ElevatorEvent;

public interface SimulateElevatorListener {
    void Receive(ElevatorEvent elevatorEvent);
}
