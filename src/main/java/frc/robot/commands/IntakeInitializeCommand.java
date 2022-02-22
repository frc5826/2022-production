package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeInitializeCommand extends CommandBase {

    private IntakeSubsystem subsystem;
    private boolean finished = false;

    public IntakeInitializeCommand(IntakeSubsystem subsystem){
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        subsystem.setReadyToInit();
        finished = true;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

}
