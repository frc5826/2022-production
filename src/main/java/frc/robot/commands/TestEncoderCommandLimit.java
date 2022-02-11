package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class TestEncoderCommandLimit extends CommandBase {

    private final IntakeSubsystem intakeSubsystem;
    private int count = 0;
    private double intakeMotorPower;

    private int leftInitCount = 0;
    private int rightInitCount = 0;

    private boolean leftInitDone = false;
    private boolean rightInitDone = false;

    public TestEncoderCommandLimit(double intakeMotorPower, IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void execute() {
        if (leftInitCount <= 50) {
            intakeSubsystem.getLeftTalon().set(TalonSRXControlMode.PercentOutput, intakeMotorPower);
            leftInitCount++;
        }
        else if (intakeSubsystem.getLeftTalon().getMotorOutputVoltage() <= 0.01 && !leftInitDone){
            leftInitDone = true;
            intakeSubsystem.getLeftTalon().set(TalonSRXControlMode.PercentOutput, 0);
        }

        if (rightInitCount <= 50) {
            intakeSubsystem.getRightTalon().set(TalonSRXControlMode.PercentOutput, intakeMotorPower);
            rightInitCount++;
        }
        else if (intakeSubsystem.getRightTalon().getMotorOutputVoltage() <= 0.01 && !rightInitDone){
            rightInitDone = true;
            intakeSubsystem.getRightTalon().set(TalonSRXControlMode.PercentOutput, 0);
        }
    }

    @Override
    public void end(boolean interrupted) {



//        intakeSubsystem.getLeftTalon().set(TalonSRXControlMode.Position, intakeSubsystem.getClosedValueLeftIntake());
//        intakeSubsystem.getRightTalon().set(TalonSRXControlMode.Position, intakeSubsystem.getClosedValueRightIntake());
    }
}
