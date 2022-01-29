package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSubsystem;

public class TestCommandGroup extends SequentialCommandGroup {
    public TestCommandGroup(DriveSubsystem driveSubsystem){
        addCommands(
                new DriveDistanceCommand(-60, driveSubsystem),
                new DriveDistanceCommand(84, driveSubsystem)
        );
    }
}
