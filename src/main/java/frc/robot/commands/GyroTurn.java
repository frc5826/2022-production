/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//Yes, I did just grab this from 2020
package frc.robot.commands;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.kauailabs.navx.frc.AHRS;
import frc.robot.Constants;

/**
 * This is a demo program showing the use of the DifferentialDrive class.
 * Runs the motors with arcade steering.
 */
public class GyroTurn {
    double P = Constants.GYRO_P;
    double I = Constants.GYRO_I;
    double D = Constants.GYRO_D;
    double previous_error, integral = 0;
    int setpoint = 0;
    DifferentialDrive robotDrive;
    AHRS gyro;
    private double rcw;

    private int count = 0;


    public GyroTurn(final AHRS gyro, final DifferentialDrive robotDrive) {
        this.gyro = gyro;
        this.robotDrive = robotDrive;
    }

    public void setSetpoint(final int setpoint) {
        this.setpoint = setpoint;
    }

    public void PID() {
        final double error = setpoint - gyro.getAngle(); // Error = Target - Actual
        this.integral += (error * .02); // Integral is increased by the error*time (which is .02 seconds using normal
        // IterativeRobot)
        final double derivative = (error - this.previous_error) / .02;
        this.rcw = P*error + I*this.integral + D*derivative;
        this.previous_error = error;

        if(count++ % 25 == 0){
            System.out.println("rcw - " + rcw);
            System.out.println("angle - " + gyro.getAngle());
            System.out.println("error - " + error);
            System.out.println("setpoint - " + setpoint);
        }
    }

    public void execute()
    {
        PID();

        //TODO Invert rcw if spinning out of control
        robotDrive.arcadeDrive(0, -rcw);
    }
}