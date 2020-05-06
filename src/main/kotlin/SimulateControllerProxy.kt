import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import lab3.DoorMotor
import lab3.ElevatorController
import lab3.ElevatorMotor
import lab3.bean.SimulatorConfig

class SimulateControllerProxy(elevatorMotor: ElevatorMotor, doorMotor: DoorMotor, config: SimulatorConfig) : ElevatorController(elevatorMotor, doorMotor, config) {

    val channel = Channel<SimulateElevatorMessage>()

    override fun floorChanged() {
        GlobalScope.launch {
            channel.send(SimulateElevatorMessage.FLOOR_CHANGE)
            super.floorChanged()
        }
    }

    override fun moveDown() {
        GlobalScope.launch {
            channel.send(SimulateElevatorMessage.MOVE_DOWN)
            super.moveDown()
        }
    }

    override fun moveUp() {
        GlobalScope.launch {
            channel.send(SimulateElevatorMessage.MOVE_UP)
            super.moveUp()
        }
    }

    override fun doorBlocked() {
        GlobalScope.launch {
            channel.send(SimulateElevatorMessage.DOOR_BLOCKED)
            super.doorBlocked()
        }
    }

    override fun doorOpened() {
        GlobalScope.launch {
            channel.send(SimulateElevatorMessage.DOOR_OPENED)
            super.doorOpened()
        }
    }

    override fun doorClosed() {
        GlobalScope.launch {
            channel.send(SimulateElevatorMessage.DOOR_CLOSED)
            super.doorClosed()
        }
    }

}