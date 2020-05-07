import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import lab3.DoorMotor
import lab3.ElevatorController
import lab3.ElevatorMotor
import lab3.bean.SimulatorConfig

class SimulateControllerProxy(elevatorMotor: ElevatorMotor, doorMotor: DoorMotor, config: SimulatorConfig) : ElevatorController(elevatorMotor, doorMotor, config) {

    val channel = Channel<SimulateElevatorMessage>(10)

    override fun floorChanged() {
        GlobalScope.launch {
            super.floorChanged()
            channel.send(SimulateElevatorMessage.FLOOR_CHANGE)
        }
    }

    override fun moveDown() {
        GlobalScope.launch {
            super.moveDown()
            channel.send(SimulateElevatorMessage.MOVE_DOWN)
        }
    }

    override fun moveUp() {
        GlobalScope.launch {
            super.moveUp()
            channel.send(SimulateElevatorMessage.MOVE_UP)
        }
    }

    override fun doorBlocked() {
        GlobalScope.launch {
            super.doorBlocked()
            channel.send(SimulateElevatorMessage.DOOR_BLOCKED)
        }
    }

    override fun doorOpened() {
        GlobalScope.launch {
            super.doorOpened()
            channel.send(SimulateElevatorMessage.DOOR_OPENED)
        }
    }

    override fun doorClosed() {
        GlobalScope.launch {
            super.doorClosed()
            channel.send(SimulateElevatorMessage.DOOR_CLOSED)
        }
    }

}