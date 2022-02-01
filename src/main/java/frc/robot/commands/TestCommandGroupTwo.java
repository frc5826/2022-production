package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSubsystem;

public class TestCommandGroupTwo extends SequentialCommandGroup {
    public TestCommandGroupTwo(DriveSubsystem driveSubsystem){
        addCommands(
                new DriveDistanceCommand(-120, driveSubsystem),
                new TurnAngleCommand(180, driveSubsystem),
                new DriveDistanceCommand(-120, driveSubsystem)
        );
    }
}
