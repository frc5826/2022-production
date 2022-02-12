package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCommand extends CommandBase {

    private ShooterSubsystem shooterSubsystem;

    public ShooterCommand(ShooterSubsystem shooterSubsystem){
        this.shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
    }

    @Override
    public void execute() {
        shooterSubsystem.shoot();
    }

    @Override
    public void end(boolean interrupted) {
        shooterSubsystem.retract();
    }
}
