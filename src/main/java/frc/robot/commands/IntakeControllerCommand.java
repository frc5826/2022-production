package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeControllerCommand extends CommandBase {
    private final IntakeSubsystem intakeSubsystem;

    public IntakeControllerCommand(IntakeSubsystem intakeSubsystem){
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void execute() {
        if(Constants.joystick.getRawButton(3)){
            intakeSubsystem.getTalon().set(TalonSRXControlMode.PercentOutput, -Constants.intakeSpeed);
        }
        else {
            intakeSubsystem.getTalon().set(TalonSRXControlMode.PercentOutput, Constants.intakeSpeed);
        }
    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.getTalon().set(TalonSRXControlMode.PercentOutput, 0);
    }
}
