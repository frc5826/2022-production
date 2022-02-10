// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final Joystick joystick = new Joystick(0);

    public static AHRS gyro = new AHRS(SPI.Port.kMXP);

    //TODO Get the correct IDs for the speed controllers
    public static final int leftID1 = 3;
    public static final int leftID2 = 4;
    public static final int rightID1 = 7;
    public static final int rightID2 = 8;
    public static final int leftTalonIntakeID = 5;
    public static final int rightTalonIntakeID = -1;
    public static final int ElevatorID = 0;

    public static final double maxSpeed = 0.3;
    public static final double intakeSpeed = 0.3;
    public static final double elevatorSpeed = 0.3;

    public static final double WHEEL_DIAMETER = 6;

    public static final double DRIVE_GEAR_RATIO = 10.71;
    //TODO internet says 12.75 but 12.5 works better

    public static final String cameraName = "5826gloworm";

    public static final double DRIVE_P = 0.9;
    public static final double DRIVE_I = 0.0;
    public static final double DRIVE_D = 0.0;

    public static final double GYRO_P = 0.042;
    public static final double GYRO_I = 0.0;
    public static final double GYRO_D = 0.01;

    public static final double PEAK_OUTPUT = 0.5;
    public static final double TURN_PEAK_OUTPUT = 0.2;

    public static final double kShooterPeakOutput = 0.90;

    public static final double kShooterP = 1;
    public static final double kShooterI = 0;
    public static final double kShooterD = 0;

    public static final double kRampRate = 0.3;

    public static final int kPIDLoopIdx = 0;

    public static final int kTimeoutMs = 30;
    
    public static final double ROBOT_RADIUS = 13.5;

    public static final int Compressor = 11;

    public static final int armFWD = 1;
    public static final int armREV = 0;
}
