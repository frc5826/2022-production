package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;

import java.time.Duration;
import java.time.Instant;

public class ElevatorControllerCommand extends CommandBase {

    private final ElevatorSubsystem elevatorSubsystem;
    private int elevatorInitCount = 0;
    private boolean elevatorInitDone = false;
    private double elevatorMotorPower;
    private Instant start;

    public ElevatorControllerCommand(double elevatorMotorPower, ElevatorSubsystem elevatorSubsystem){
        this.elevatorMotorPower = elevatorMotorPower;
        this.elevatorSubsystem = elevatorSubsystem;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize() {
        start = Instant.now();
        elevatorInitCount = 0;
        elevatorInitDone = false;
        elevatorSubsystem.setDone(false);
    }

    @Override
    public void execute() {
        if(!elevatorInitDone){
            elevatorSubsystem.getElevatorTalon().set(TalonSRXControlMode.PercentOutput, elevatorMotorPower);
            elevatorInitCount++;
        }
        double voltage = elevatorSubsystem.getElevatorTalon().getMotorOutputVoltage();
        if (Math.abs(voltage) <= 0.01 && elevatorInitCount > Constants.MOTOR_INIT_COUNT){
            elevatorInitDone = true;
            elevatorSubsystem.setDone(true);
        }
    }

    @Override
    public void end(boolean interrupted) {
        elevatorSubsystem.getElevatorTalon().set(TalonSRXControlMode.PercentOutput, 0);
    }

    @Override
    public boolean isFinished(){
        if(Duration.between(Instant.now(), start).abs().toSeconds() > Constants.ELEVATOR_TIMEOUT){
            SmartDashboard.putString("ElevatorStatus", "Timed Out");
        }
        else {
            SmartDashboard.putString("ElevatorStatus", "Normal");
        }
        return elevatorInitDone || (Instant.now().getEpochSecond() - start.getEpochSecond() >= Constants.ELEVATOR_TIMEOUT);
    }
}
