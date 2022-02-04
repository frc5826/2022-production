package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import static frc.robot.Constants.*;

public class TurnAngleCommand extends CommandBase {

    private final double turnAngle;
    private final DriveSubsystem driveSubsystem;
    private double leftTargetDist;
    private double rightTargetDist;

    private int count = 0;

    public TurnAngleCommand(double turnAngle, DriveSubsystem driveSubsystem) {
        this.turnAngle = turnAngle;
        this.driveSubsystem = driveSubsystem;
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        driveSubsystem.setSpeedOutputRange(-TURN_PEAK_OUTPUT, TURN_PEAK_OUTPUT);
        leftTargetDist = driveSubsystem.getLeftPosition() + angleToDistance(turnAngle);
        rightTargetDist = driveSubsystem.getRightPosition() - angleToDistance(turnAngle);
    }

    @Override
    public void execute() {

        driveSubsystem.setLeftPosition(leftTargetDist);
        driveSubsystem.setRightPosition(rightTargetDist);

    }

    public double angleToDistance(double turnAngle){
        double circleFraction = turnAngle/360;
        double totalCircleInches = 2 * Math.PI * ROBOT_RADIUS;
        return circleFraction * totalCircleInches;
    }

    @Override
    public void end(boolean interrupted) {
        this.driveSubsystem.setVelocity(0);
        driveSubsystem.setSpeedOutputRange(-PEAK_OUTPUT, PEAK_OUTPUT);
        super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        boolean leftClose = Math.abs(driveSubsystem.getLeftPosition() - leftTargetDist) < 1;
        boolean rightClose = Math.abs(driveSubsystem.getRightPosition() - rightTargetDist) < 1;

        return leftClose || rightClose;
    }
}
