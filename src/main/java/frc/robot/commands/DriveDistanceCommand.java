package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DriveDistanceCommand extends CommandBase {

    private final double distanceInches;
    private final DriveSubsystem driveSubsystem;
    private double leftTargetDist;
    private double rightTargetDist;

    public DriveDistanceCommand(double distanceInches, DriveSubsystem driveSubsystem) {
        this.distanceInches = -distanceInches;
        this.driveSubsystem = driveSubsystem;
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        leftTargetDist = driveSubsystem.getLeftPosition() + distanceInches;
        rightTargetDist = driveSubsystem.getRightPosition() + distanceInches;
    }

    @Override
    public void execute() {
        driveSubsystem.setLeftPosition(leftTargetDist);
        driveSubsystem.setRightPosition(rightTargetDist);
    }

    @Override
    public void end(boolean interrupted) {
        this.driveSubsystem.setVelocity(0);
        super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        boolean leftClose = Math.abs(driveSubsystem.getLeftPosition() - leftTargetDist) < 1;
        boolean rightClose = Math.abs(driveSubsystem.getRightPosition() - rightTargetDist) < 1;

        return leftClose || rightClose;
    }
}
