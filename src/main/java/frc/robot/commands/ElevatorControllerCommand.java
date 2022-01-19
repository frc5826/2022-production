package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorControllerCommand extends CommandBase {

    private final ElevatorSubsystem elevatorSubsystem;

    public ElevatorControllerCommand(ElevatorSubsystem elevatorSubsystem){
        this.elevatorSubsystem = elevatorSubsystem;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void execute() {
        if(Constants.joystick.getRawButton(4) && Constants.joystick.getRawButton(6)){
            elevatorSubsystem.getTalon().set(TalonSRXControlMode.PercentOutput, 0);
        }
        else if(Constants.joystick.getRawButton(6)){
            elevatorSubsystem.getTalon().set(TalonSRXControlMode.PercentOutput, Constants.elevatorSpeed);
        }
        else if(Constants.joystick.getRawButton(4)){
            elevatorSubsystem.getTalon().set(TalonSRXControlMode.PercentOutput, -Constants.elevatorSpeed);
        }
    }

    @Override
    public void end(boolean interrupted) {
        elevatorSubsystem.getTalon().set(TalonSRXControlMode.PercentOutput, 0);
    }
}
