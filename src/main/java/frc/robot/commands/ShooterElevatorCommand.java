package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterElevatorCommand extends SequentialCommandGroup {

    public ShooterElevatorCommand(ShooterSubsystem shooter, ElevatorSubsystem elevator){
        addCommands(
                new ShooterCommand(shooter),
                new ElevatorDownCommand(elevator)
        );
    }
}
