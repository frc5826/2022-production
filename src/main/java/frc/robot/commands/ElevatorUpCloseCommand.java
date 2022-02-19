package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ElevatorUpCloseCommand extends SequentialCommandGroup {
    public ElevatorUpCloseCommand(TestEncoderCommand closeCommand, ElevatorControllerCommand upCommand){
        addCommands(
                closeCommand,
                upCommand
        );
    }
}
