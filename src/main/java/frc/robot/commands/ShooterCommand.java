package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCommand extends CommandBase {

    private ShooterSubsystem shooterSubsystem;
    private int delayCount;

    public ShooterCommand(ShooterSubsystem shooterSubsystem){
        delayCount = 0;
        this.shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
    }

    @Override
    public void execute() {
        shooterSubsystem.shoot();
        delayCount++;
    }

    @Override
    public boolean isFinished() {
        return delayCount > 20;
    }

    @Override
    public void end(boolean interrupted) {
        shooterSubsystem.retract();
        delayCount = 0;
    }
}
