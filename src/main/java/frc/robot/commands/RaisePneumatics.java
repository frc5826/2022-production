package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ClimbSubsystem;

public class RaisePneumatics extends CommandBase {

    private ClimbSubsystem climbSubsystem;

    public RaisePneumatics(ClimbSubsystem climbSubsystem){
        this.climbSubsystem = climbSubsystem;
        addRequirements(climbSubsystem);
    }

    @Override
    public void execute() {
        climbSubsystem.raise();
    }
}
