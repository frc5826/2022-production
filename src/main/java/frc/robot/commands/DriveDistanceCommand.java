package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DriveDistanceCommand extends CommandBase {

    private final double distanceInches;
    private final DriveSubsystem driveSubsystem;
    private double startDistance;
    private PIDController pid;

    private int count = 0;

    public DriveDistanceCommand(double distanceInches, DriveSubsystem driveSubsystem) {
        this.distanceInches = distanceInches;
        this.driveSubsystem = driveSubsystem;
        this.pid = new PIDController(.01, 0, 0);
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        this.startDistance = driveSubsystem.getDistance();
        this.pid.setSetpoint(driveSubsystem.getDistance() + this.distanceInches);
    }

    @Override
    public void execute() {
        double PIDSpeed = this.pid.calculate(driveSubsystem.getDistance());
        double speed = -1 * (Math.min(Math.max(PIDSpeed, .1), .3));
        this.driveSubsystem.getDiffDrive().curvatureDrive(speed, 0, true);


        System.out.println("Distance: " + driveSubsystem.getDistance());
        System.out.println("Target: " + this.distanceInches);
        System.out.println("PID Recommended Speed: " + PIDSpeed);
        System.out.println("Given Speed: " + speed);
        System.out.println("isFinished: " + Math.abs(this.driveSubsystem.getDistance() - (this.startDistance + this.distanceInches)));
    }

    @Override
    public void end(boolean interrupted) {
        this.driveSubsystem.getDiffDrive().arcadeDrive(0, 0);
        super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(this.driveSubsystem.getDistance() - (this.startDistance + this.distanceInches)) < 5;
    }
}
