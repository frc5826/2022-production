package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SensorSubsystem;
import frc.robot.subsystems.DriveSubsystem;

public class ChaseCommand extends CommandBase {
    private final SensorSubsystem sensorSubsystem;
    private final DriveSubsystem driveSubsystem;

    public boolean targetAcquired = false;


    public ChaseCommand(SensorSubsystem sensorSubsystem, DriveSubsystem driveSubsystem){
        this.sensorSubsystem = sensorSubsystem;
        this.driveSubsystem = driveSubsystem;
        addRequirements(sensorSubsystem, driveSubsystem);
    }

    @Override
    public void execute(){
        if(sensorSubsystem.isBall()){
            targetAcquired = true;
            driveSubsystem.getDiffDrive().arcadeDrive(0.3, sensorSubsystem.getBallYaw() / 30);

        }
        //TODO : if blocked slow down / stop
        //TODO : occlusion detection
        else{
            System.out.print("i cant find my balls :( ");
        }
    }
}
