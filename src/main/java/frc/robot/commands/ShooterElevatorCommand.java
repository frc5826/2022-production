package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterElevatorCommand extends SequentialCommandGroup {
    public ShooterElevatorCommand(ShooterCommand shooterCommand, ElevatorControllerCommand elevatorCommand){
        addCommands(
                shooterCommand,
                elevatorCommand
        );
    }
}
