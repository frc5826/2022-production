package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ClimbSubsystem2;

public class RaisePneumatics2 extends CommandBase {

    private ClimbSubsystem2 climbSubsystem2;
    private int delayCount = 0;

    public RaisePneumatics2(ClimbSubsystem2 climbSubsystem2){
        this.climbSubsystem2 = climbSubsystem2;
        addRequirements(climbSubsystem2);
    }

    @Override
    public void execute() {
        climbSubsystem2.raise();
        delayCount++;
    }

    @Override
    public boolean isFinished() {
        return delayCount > Constants.PNEUMATICS_INIT_COUNT;
    }

    @Override
    public void end(boolean interrupted) {
        delayCount = 0;
    }

}
