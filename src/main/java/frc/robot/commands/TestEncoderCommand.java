package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import org.w3c.dom.ls.LSOutput;

public class TestEncoderCommand extends CommandBase {

    private final IntakeSubsystem intakeSubsystem;
    private int count = 0;

    public TestEncoderCommand(IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void execute() {
        intakeSubsystem.getLeftTalon().set(TalonSRXControlMode.Position, intakeSubsystem.getClosedValueLeftIntake() - 3000);
        intakeSubsystem.getRightTalon().set(TalonSRXControlMode.Position, intakeSubsystem.getClosedValueRightIntake() - 3000);

    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.getLeftTalon().set(TalonSRXControlMode.Position, intakeSubsystem.getClosedValueLeftIntake());
        intakeSubsystem.getRightTalon().set(TalonSRXControlMode.Position, intakeSubsystem.getClosedValueRightIntake());
    }
}
