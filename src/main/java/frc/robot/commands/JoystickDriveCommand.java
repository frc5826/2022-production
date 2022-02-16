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
        driveSubsystem.getDiffDrive().arcadeDrive(-Constants.joystick.getY(), Constants.joystick.getZ());
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.getDiffDrive().arcadeDrive(0,0);
    }
}
