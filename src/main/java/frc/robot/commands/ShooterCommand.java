package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCommand extends CommandBase {

    private ElevatorSubsystem elevatorSubsystem;
    private ShooterSubsystem shooterSubsystem;
    private int delayCount;

    public ShooterCommand(ShooterSubsystem shooterSubsystem, ElevatorSubsystem elevatorSubsystem){
        delayCount = 0;
        this.shooterSubsystem = shooterSubsystem;
        this.elevatorSubsystem = elevatorSubsystem;
        addRequirements(shooterSubsystem, elevatorSubsystem);
    }

    @Override
    public void execute() {
        if(elevatorSubsystem.isDone()) {
            shooterSubsystem.shoot();
        }
        delayCount++;
    }

    @Override
    public boolean isFinished() {
        return delayCount > Constants.PNEUMATICS_INIT_COUNT;
    }

    @Override
    public void end(boolean interrupted) {
        shooterSubsystem.retract();
        delayCount = 0;
    }
}
