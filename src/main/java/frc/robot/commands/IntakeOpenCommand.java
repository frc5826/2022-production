package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeOpenCommand extends CommandBase {

    private final IntakeSubsystem intakeSubsystem;
    private double intakeMotorPower;

    private int leftTurnCount = 0;
    private int rightTurnCount = 0;

    private boolean leftTurnDone = false;
    private boolean rightTurnDone = false;

    public IntakeOpenCommand(IntakeSubsystem intakeSubsystem) {
        this.intakeMotorPower = Constants.INTAKE_MOTOR_POWER;
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void execute() {
        if (leftTurnCount <= Constants.MOTOR_INIT_COUNT) {
            intakeSubsystem.getLeftTalon().set(TalonSRXControlMode.PercentOutput, intakeMotorPower);
            leftTurnCount++;
        }
        else if (intakeSubsystem.getLeftTalon().getMotorOutputVoltage() <= 0.01 && !leftTurnDone){
            leftTurnDone = true;
            intakeSubsystem.getLeftTalon().set(TalonSRXControlMode.PercentOutput, 0);
        }

        if (rightTurnCount <= Constants.MOTOR_INIT_COUNT) {
            intakeSubsystem.getRightTalon().set(TalonSRXControlMode.PercentOutput, intakeMotorPower);
            rightTurnCount++;
        }
        else if (intakeSubsystem.getRightTalon().getMotorOutputVoltage() <= 0.01 && !rightTurnDone){
            rightTurnDone = true;
            intakeSubsystem.getRightTalon().set(TalonSRXControlMode.PercentOutput, 0);
        }
    }

    @Override
    public void end(boolean interrupted) {
        leftTurnCount = 0;
        rightTurnCount = 0;

        leftTurnDone = false;
        rightTurnDone = false;

        intakeSubsystem.getRightTalon().set(TalonSRXControlMode.PercentOutput, 0);
        intakeSubsystem.getLeftTalon().set(TalonSRXControlMode.PercentOutput, 0);
    }

    @Override
    public boolean isFinished() {
        return leftTurnDone && rightTurnDone;
    }
}
