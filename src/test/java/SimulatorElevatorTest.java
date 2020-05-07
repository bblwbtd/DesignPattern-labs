import io.reactivex.Observable;
import lab3.ElevatorController;
import lab3.SimulateElevator;
import lab3.bean.SimulatorConfig;
import lab3.exceptions.WrongOperationException;
import lab3.states.*;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimulatorElevatorTest {


    private SimulateElevator initialElevator() {
        SimulatorConfig config = new SimulatorConfig();
        SimulateElevator simulateElevator = new SimulateElevator(config);
        ElevatorController controller = new ElevatorController(simulateElevator.getElevatorMotor(), simulateElevator.getDoorMotor(), config);
        simulateElevator.setController(controller);
        return simulateElevator;
    }

    @Test
    void testMoveUp() throws WrongOperationException, InterruptedException {
        SimulateElevator simulateElevator = initialElevator();
        simulateElevator.pressFloorButton(3);
        Observable.just(simulateElevator).map(s -> {
            assertTrue(s.getController().getState() instanceof MovingUpState);
            return simulateElevator;
        }).delay(7, TimeUnit.SECONDS).map(s -> {
            assertTrue(s.getController().getState() instanceof DoorOpeningState);
            System.out.println(s.getController().getState() instanceof DoorOpeningState);
            return simulateElevator;
        }).delay(3, TimeUnit.SECONDS).map(s -> {
            System.out.println(s.getController().getState());
            System.out.println(s.getController().getState() instanceof DoorOpenedState);
            assertTrue(s.getController().getState() instanceof DoorOpenedState);
            return simulateElevator;
        }).test().await().assertNoErrors();
    }

    @Test
    void testMoveDown() throws WrongOperationException, InterruptedException {
        SimulateElevator simulateElevator = initialElevator();
        simulateElevator.getController().setCurrentFloor(3);
        simulateElevator.pressFloorButton(1);
        Observable.just(simulateElevator).map(s -> {
            assertTrue(s.getController().getState() instanceof MovingDownState);
            return simulateElevator;
        }).delay(7, TimeUnit.SECONDS).map(s -> {
            assertTrue(s.getController().getState() instanceof DoorOpeningState);
            return simulateElevator;
        }).delay(4, TimeUnit.SECONDS).map(s -> {
            assertTrue(s.getController().getState() instanceof DoorOpenedState);
            return simulateElevator;
        }).test().await().assertNoErrors();
    }

    @Test
    void testPressCloseDoorButton() throws InterruptedException {
        SimulateElevator simulateElevator = initialElevator();
        simulateElevator.getController().setState(new DoorOpenedState(simulateElevator.getController()));
        simulateElevator.pressCloseDoorButton();
        Observable.just(simulateElevator).map(s -> {
            assertTrue(s.getController().getState() instanceof DoorClosingState);
            return simulateElevator;
        }).delay(4, TimeUnit.SECONDS).map(s -> {
            assertTrue(s.getController().getState() instanceof DoorClosedState);
            return simulateElevator;
        }).test().await().assertNoErrors();
    }

    @Test
    void testPressOpenDoorButton() throws InterruptedException {
        SimulateElevator simulateElevator = initialElevator();
        simulateElevator.getController().setState(new DoorClosedState(simulateElevator.getController()));
        simulateElevator.pressOpenDoorButton();
        Observable.just(simulateElevator).map(s -> {
            assertTrue(s.getController().getState() instanceof DoorOpeningState);
            return simulateElevator;
        }).delay(4, TimeUnit.SECONDS).map(s -> {
            assertTrue(s.getController().getState() instanceof DoorOpenedState);
            return simulateElevator;
        }).test().await().assertNoErrors();
    }

    @Test
    void testSimulateDoorBlocked() throws InterruptedException {
        SimulateElevator simulateElevator = initialElevator();
        simulateElevator.getController().setState(new DoorOpenedState(simulateElevator.getController()));
        simulateElevator.pressCloseDoorButton();
        Observable.just(simulateElevator).delay(1, TimeUnit.SECONDS).map(s -> {
            assertTrue(s.getController().getState() instanceof DoorClosingState);
            s.simulateDoorBlocked();
            assertTrue(s.getController().getState() instanceof DoorOpeningState);
            return s;
        }).delay(5, TimeUnit.SECONDS).map(s -> {
            assertTrue(s.getController().getState() instanceof DoorOpenedState);
            return s;
        }).test().await().assertNoErrors();

    }

    @Test
    void testAutoClose() throws InterruptedException {
        SimulateElevator simulateElevator = initialElevator();
        simulateElevator.getController().setState(new DoorOpenedState(simulateElevator.getController()));
        Observable.empty().delay(6, TimeUnit.SECONDS).doOnComplete(() -> {
            assertTrue(simulateElevator.getController().getState() instanceof DoorClosingState);
        }).test().await().assertNoErrors();
    }
}
