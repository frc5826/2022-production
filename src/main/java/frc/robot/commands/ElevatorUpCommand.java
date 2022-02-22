package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class ElevatorUpCommand extends ParallelCommandGroup {

    public ElevatorUpCommand(IntakeSubsystem intake, ElevatorSubsystem elevator){
        addCommands(
                new ElevatorControllerCommand(Constants.elevatorUpSpeed, elevator),
                new IntakeCloseCommand(intake)
        );
    }

}
