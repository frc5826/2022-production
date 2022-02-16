package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class TestEncoderCommandLimit extends CommandBase {

    private final IntakeSubsystem intakeSubsystem;
    private int count = 0;
    private double intakeMotorPower;

    private int leftTurnCount = 0;
    private int rightTurnCount = 0;

    private boolean leftTurnDone = false;
    private boolean rightTurnDone = false;

    public TestEncoderCommandLimit(double intakeMotorPower, IntakeSubsystem intakeSubsystem) {
        this.intakeMotorPower = intakeMotorPower;
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void execute() {
        if (leftTurnCount <= 50) {
            intakeSubsystem.getLeftTalon().set(TalonSRXControlMode.PercentOutput, intakeMotorPower);
            leftTurnCount++;
        }
        else if (intakeSubsystem.getLeftTalon().getMotorOutputVoltage() <= 0.01 && !leftTurnDone){
            leftTurnDone = true;
            intakeSubsystem.getLeftTalon().set(TalonSRXControlMode.PercentOutput, 0);
        }

        if (rightTurnCount <= 50) {
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

        count = 0;
        leftTurnCount = 0;
        rightTurnCount = 0;

        leftTurnDone = false;
        rightTurnDone = false;

//        intakeSubsystem.getLeftTalon().set(TalonSRXControlMode.Position, intakeSubsystem.getClosedValueLeftIntake());
//        intakeSubsystem.getRightTalon().set(TalonSRXControlMode.Position, intakeSubsystem.getClosedValueRightIntake());
    }

    @Override
    public boolean isFinished() {
        return leftTurnDone && rightTurnDone;
    }
}
