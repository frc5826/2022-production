/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

/**
 * An example command that uses an example subsystem.
 */

public class TurnCommand2 extends CommandBase {

    private final DriveSubsystem m_subsystem;
    private GyroTurn gyroTurn;
    private int setpoint;

    private int count = 0;

    private int endpoint;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public TurnCommand2(DriveSubsystem subsystem, int setpoint) {
        m_subsystem = subsystem;
        this.setpoint = -setpoint;
        addRequirements(subsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        gyroTurn = new GyroTurn(Constants.gyro, m_subsystem.getDiffDrive());
        endpoint = (int) (setpoint + Constants.gyro.getAngle()) % 360;
        gyroTurn.setSetpoint(endpoint);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        gyroTurn.execute();
//        if(count++ % 25 == 0){
//            System.out.println("angle - " + Constants.gyro.getAngle());
//        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return Math.abs(endpoint - (Constants.gyro.getAngle() % 360)) < 5;
        //Joystick joystick = Constants.joystick;
        //return Math.abs(joystick.getX()) > .1 || Math.abs(joystick.getY()) > .1 || Math.abs(joystick.getZ()) > .1;
    }
}
  