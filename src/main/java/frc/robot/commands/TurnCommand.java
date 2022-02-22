/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

/**
 * An example command that uses an example subsystem.
 */

public class TurnCommand extends CommandBase {

    private final DriveSubsystem driveSubsystem;
    private final double setpoint;
    private int endpoint;
    private double integral;
    private double previous_error;
    private double rcw;


    public TurnCommand(double turnAngle, DriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
        this.setpoint = -turnAngle;
        addRequirements(driveSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        endpoint = (int) (setpoint + Constants.gyro.getAngle()) % 360;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        PID();
        driveSubsystem.getDiffDrive().arcadeDrive(0, -rcw);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        driveSubsystem.getDiffDrive().arcadeDrive(0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return Math.abs(endpoint - (Constants.gyro.getAngle() % 360)) < 5;
    }

    public void PID() {
        final double error = setpoint - Constants.gyro.getAngle(); // Error = Target - Actual
        this.integral += (error * .02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
        final double derivative = (error - this.previous_error) / .02;
        this.rcw = Constants.GYRO_P * error + Constants.GYRO_I * this.integral + Constants.GYRO_D * derivative;
        this.previous_error = error;
    }

}
  