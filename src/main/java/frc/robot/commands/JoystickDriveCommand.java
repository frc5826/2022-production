package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class JoystickDriveCommand extends CommandBase {
    private final DriveSubsystem driveSubsystem;

    public JoystickDriveCommand(DriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {
        double y = -Constants.joystick.getY();
        //TODO - Could we also check if the elevator is up and limit speed?
        if(y < Constants.REVERSE_MAX_SPEED) {
            y = Constants.REVERSE_MAX_SPEED;
        }

        driveSubsystem.getDiffDrive().arcadeDrive(y, Constants.joystick.getZ());
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.setSpeedOutputRange(-Constants.PEAK_OUTPUT, Constants.PEAK_OUTPUT);
        driveSubsystem.getDiffDrive().arcadeDrive(0,0);
    }
}
