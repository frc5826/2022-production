package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutonomousCommandGroup extends SequentialCommandGroup {

    public AutonomousCommandGroup(ShooterSubsystem shooter, ElevatorSubsystem elevator, DriveSubsystem drive, IntakeSubsystem intake){
        addCommands(
                new ElevatorControllerCommand(Constants.elevatorUpSpeed, elevator),
                new ShooterCommand(shooter, elevator),
                new ElevatorDownCommand(elevator),
                new IntakeInitializeCommand(intake),
                new DriveDistanceCommand(Constants.AUTO_DRIVE_DISTANCE_INCHES, drive)
        );
    }
}
