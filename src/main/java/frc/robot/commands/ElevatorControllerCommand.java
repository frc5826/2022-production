package frc.robot.commands;

//import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorControllerCommand extends CommandBase {

    private final ElevatorSubsystem elevatorSubsystem;
    private int elevatorInitCount = 0;
    private boolean elevatorInitDone = false;
    private double elevatorMotorPower = 0;

    public ElevatorControllerCommand(double elevatorMotorPower, ElevatorSubsystem elevatorSubsystem){
        this.elevatorSubsystem = elevatorSubsystem;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void execute() {
        if (elevatorInitCount <= 50) {
            elevatorSubsystem.getElevatorTalon().set(TalonSRXControlMode.PercentOutput, elevatorMotorPower);
            elevatorInitCount++;
        }
        else if (elevatorSubsystem.getElevatorTalon().getMotorOutputVoltage() <= 0.01 && !elevatorInitDone){
            elevatorInitDone = true;
            elevatorSubsystem.getElevatorTalon().set(TalonSRXControlMode.PercentOutput, 0);
        }

    }

    @Override
    public void end(boolean interrupted) {
//        elevatorSubsystem.getTalon().set(TalonSRXControlMode.PercentOutput, 0);
    }
}
