package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSubsystem;

public class BarrySandersCommandGroupRight extends SequentialCommandGroup {
    public BarrySandersCommandGroupRight(DriveSubsystem driveSubsystem){
        addCommands(
                new TurnCommand2(driveSubsystem, 75),
                new DriveDistanceCommand(-40, driveSubsystem),
                new TurnCommand2(driveSubsystem, -75),
                new DriveDistanceCommand(60, driveSubsystem)
        );
    }
}