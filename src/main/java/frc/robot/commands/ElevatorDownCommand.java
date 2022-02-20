package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorDownCommand extends ElevatorControllerCommand {

    public ElevatorDownCommand(ElevatorSubsystem elevatorSubsystem) {
        super(Constants.elevatorDownSpeed, elevatorSubsystem);
    }

}
