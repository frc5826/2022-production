package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ClimbSubsystem;

public class RaisePneumatics extends CommandBase {

    private ClimbSubsystem climbSubsystem;
    private int delayCount = 0;

    public RaisePneumatics(ClimbSubsystem climbSubsystem){
        this.climbSubsystem = climbSubsystem;
        addRequirements(climbSubsystem);
    }

    @Override
    public void execute() {
        if(Constants.joystick.getRawButton(6)) {
            climbSubsystem.raise();
        }
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
