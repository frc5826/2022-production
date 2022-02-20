package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorControllerCommand extends CommandBase {

    private final ElevatorSubsystem elevatorSubsystem;
    private int elevatorInitCount = 0;
    private boolean elevatorInitDone = false;
    private double elevatorMotorPower;

    public ElevatorControllerCommand(double elevatorMotorPower, ElevatorSubsystem elevatorSubsystem){
        this.elevatorMotorPower = elevatorMotorPower;
        this.elevatorSubsystem = elevatorSubsystem;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void execute() {
        if(!elevatorInitDone){
            elevatorSubsystem.getElevatorTalon().set(TalonSRXControlMode.PercentOutput, elevatorMotorPower);
            elevatorInitCount++;
        }
        else if (elevatorSubsystem.getElevatorTalon().getMotorOutputVoltage() <= 0.01 && elevatorInitCount > Constants.MOTOR_INIT_COUNT){
            elevatorInitDone = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
        elevatorSubsystem.getElevatorTalon().set(TalonSRXControlMode.PercentOutput, 0);
        elevatorInitCount = 0;
        elevatorInitDone = false;
    }

    @Override
    public boolean isFinished(){
        return elevatorInitDone;
    }
}
