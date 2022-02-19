package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSubsystem;

public class AutonomousCommandGroup extends SequentialCommandGroup {
    public AutonomousCommandGroup(DriveSubsystem driveSubsystem,
                                  ShooterCommand shooterCommand,
                                  ElevatorUpCloseCommand elevatorCommandUp,
                                  ElevatorControllerCommand elevatorCommandDown){
        addCommands(
                elevatorCommandUp,
                shooterCommand,
                elevatorCommandDown,
                new DriveDistanceCommand(-60, driveSubsystem)

                //new DriveDistanceCommand(84, driveSubsystem)
        );
    }
}
