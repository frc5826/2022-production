package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SensorSubsystem;
import frc.robot.subsystems.DriveSubsystem;

public class ChaseCommand extends CommandBase {
    private final SensorSubsystem sensorSubsystem;
    private final DriveSubsystem driveSubsystem;

    public boolean targetAcquired = false;

    public double forwardSpeed = 0;
    public double turnSpeed = 0;

    public ChaseCommand(SensorSubsystem sensorSubsystem, DriveSubsystem driveSubsystem){
        this.sensorSubsystem = sensorSubsystem;
        this.driveSubsystem = driveSubsystem;
        addRequirements(sensorSubsystem, driveSubsystem);
    }

    @Override
    public void execute(){

        if(sensorSubsystem.isBall()){

            forwardSpeed = sensorSubsystem.getBallDistance() / 150;

            //peak speed of .8 = greater than 10 ft
            if (forwardSpeed >= 0.8){
                forwardSpeed = 0.8;
            }
            //minimum speed of .15 = less than ~2 ft
            else if(forwardSpeed <= 0.15){
                forwardSpeed = 0.15;
            }

            turnSpeed = sensorSubsystem.getBallYaw() / 100 + .05;

            if (sensorSubsystem.getBallYaw() < 3 && sensorSubsystem.getBallYaw() > -3){
                turnSpeed = 0;
            }

            if (sensorSubsystem.getBallYaw() > 45){
                turnSpeed = 0.5;
            }
            else if (sensorSubsystem.getBallYaw() < -45){
                turnSpeed = -0.5;
            }

            targetAcquired = true;
            //simple chase vv
            driveSubsystem.getDiffDrive().arcadeDrive(forwardSpeed, turnSpeed);
        }
        else{
            System.out.print("i cant find my balls :( ");

            targetAcquired = false;
            driveSubsystem.getDiffDrive().arcadeDrive(0,0.4);
        }
    }
}

//TODO : if blocked slow down / stop
//TODO : occlusion detection
