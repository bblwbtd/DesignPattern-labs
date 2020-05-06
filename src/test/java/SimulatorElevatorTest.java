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

    @Test
    void testMoveUp() throws WrongOperationException, InterruptedException {
        SimulatorConfig config = new SimulatorConfig();
        SimulateElevator simulateElevator = new SimulateElevator(config);
        ElevatorController controller = new ElevatorController(simulateElevator.getElevatorMotor(), simulateElevator.getDoorMotor(), config);
        simulateElevator.setController(controller);
        simulateElevator.pressFloorButton(3);
        Observable<SimulateElevator> observable = Observable.just(simulateElevator).map(s -> {
            assertTrue(s.getController().getState() instanceof MovingUpState);
            return simulateElevator;
        }).delay(7, TimeUnit.SECONDS).map(s -> {
            assertTrue(s.getController().getState() instanceof OpeningDoorState);
            return simulateElevator;
        }).delay(3, TimeUnit.SECONDS).map(s -> {
            assertTrue(s.getController().getState() instanceof OpenedDoorState);
            return simulateElevator;
        });
        observable.test().assertNoErrors().await();
    }

    @Test
    void testMoveDown() throws WrongOperationException, InterruptedException {
        SimulatorConfig config = new SimulatorConfig();
        SimulateElevator simulateElevator = new SimulateElevator(config);
        ElevatorController controller = new ElevatorController(simulateElevator.getElevatorMotor(), simulateElevator.getDoorMotor(), config);
        simulateElevator.setController(controller);
        simulateElevator.getController().setCurrentFloor(3);
        simulateElevator.pressFloorButton(1);
        Observable<SimulateElevator> observable = Observable.just(simulateElevator).map(s -> {
            assertTrue(s.getController().getState() instanceof MovingDownState);
            return simulateElevator;
        }).delay(7, TimeUnit.SECONDS).map(s -> {
            assertTrue(s.getController().getState() instanceof OpeningDoorState);
            return simulateElevator;
        }).delay(3, TimeUnit.SECONDS).map(s -> {
            assertTrue(s.getController().getState() instanceof OpenedDoorState);
            return simulateElevator;
        });
        observable.test().assertNoErrors().await();
    }

    @Test
    void testPressCloseDoorButton() throws InterruptedException {
        SimulatorConfig config = new SimulatorConfig();
        SimulateElevator simulateElevator = new SimulateElevator(config);
        ElevatorController controller = new ElevatorController(simulateElevator.getElevatorMotor(), simulateElevator.getDoorMotor(), config);
        simulateElevator.setController(controller);
        simulateElevator.getController().setState(new OpenedDoorState(simulateElevator.getController()));
        simulateElevator.pressCloseDoorButton();
        Observable<SimulateElevator> observable = Observable.just(simulateElevator).map(s -> {
            assertTrue(s.getController().getState() instanceof ClosingDoorState);
            return simulateElevator;
        }).delay(4, TimeUnit.SECONDS).map(s -> {
            assertTrue(s.getController().getState() instanceof ClosedDoorState);
            return simulateElevator;
        });
        observable.test().assertNoErrors().await();
    }

    @Test
    void testPressOpenDoorButton() throws InterruptedException {
        SimulatorConfig config = new SimulatorConfig();
        SimulateElevator simulateElevator = new SimulateElevator(config);
        ElevatorController controller = new ElevatorController(simulateElevator.getElevatorMotor(), simulateElevator.getDoorMotor(), config);
        simulateElevator.setController(controller);
        simulateElevator.getController().setState(new ClosedDoorState(simulateElevator.getController()));
        simulateElevator.pressOpenDoorButton();
        Observable<SimulateElevator> observable = Observable.just(simulateElevator).map(s -> {
            assertTrue(s.getController().getState() instanceof OpeningDoorState);
            return simulateElevator;
        }).delay(4, TimeUnit.SECONDS).map(s -> {
            assertTrue(s.getController().getState() instanceof OpenedDoorState);
            return simulateElevator;
        });
        observable.test().assertNoErrors().await();
    }

    @Test
    void testSimulateDoorBlocked() throws InterruptedException {
        SimulatorConfig config = new SimulatorConfig();
        SimulateElevator simulateElevator = new SimulateElevator(config);
        ElevatorController controller = new ElevatorController(simulateElevator.getElevatorMotor(), simulateElevator.getDoorMotor(), config);
        simulateElevator.setController(controller);
        simulateElevator.getController().setState(new OpenedDoorState(simulateElevator.getController()));
        simulateElevator.pressCloseDoorButton();
        Observable<SimulateElevator> observable = Observable.just(simulateElevator).delay(1, TimeUnit.SECONDS).map(s -> {
            s.simulateDoorBlocked();
            assertTrue(s.getController().getState() instanceof OpeningDoorState);
            return s;
        }).delay(2, TimeUnit.SECONDS).map(s -> {
            assertTrue(s.getController().getState() instanceof OpenedDoorState);
            return s;
        });
        observable.test().assertNoErrors().await();

    }
}
